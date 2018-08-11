package rpcclient.proxy.impl;

import com.alibaba.fastjson.JSONObject;
import rpcclient.common.encode.EncodeExecutor;
import rpcclient.entry.EntryFactory;
import rpcclient.entry.RpcProcessEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rpcclient.common.model.RpcRequest;
import rpcclient.common.model.RpcResult;
import rpcclient.proxy.ProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理工厂
 * Created by Administrator on 2018/8/4.
 */

@Component
public class ProxyFactoryImpl implements ProxyFactory{

    @Autowired
    private EntryFactory factory;

    @Autowired
    private EncodeExecutor encodeExecutor;

    /**
     * 根据接口信息以及server地址生成一个代理类
     * @param interfaceType
     * @param host
     * @param port
     * @return
     */
    public Object getProxy(final Class interfaceType, final String host, final int port) {
        return Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class[]{interfaceType}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                // 每次都生成一个单独的entry,来完成通信
                // 每一次方法调用使用一个单独的entry. channel完成
                final RpcProcessEntry entry = factory.getRpcEntry(host, port);

                // 1. 编码，生成request
                RpcRequest rpcRequest = new RpcRequest();
                rpcRequest.setInterfaceType(interfaceType.getSimpleName());
                rpcRequest.setMethodName(method.getName());
                rpcRequest.setArgs(args);
                byte[] byteRequest = encodeExecutor.buildRequest(rpcRequest);

                // 2. 通过连接实体发送，获取响应
                byte[] byteResult = entry.process(byteRequest);

                // 3. 解析响应内容
                RpcResult rpcResult = encodeExecutor.toResult(byteResult);

                if(rpcResult.getSuccess().equals("F")){
                    throw new RuntimeException(interfaceType.getSimpleName() + "rpc call error: " + rpcResult.getErrorCode());
                }

                // 由于RpcResult中的类型是object，因此需要重新在解析一次
                if(rpcResult.getRetval() instanceof JSONObject){
                    JSONObject object = (JSONObject) rpcResult.getRetval();
                    Object model = object.toJavaObject(method.getReturnType());
                    rpcResult.setRetval(model);
                }
                return rpcResult.getRetval();
            }
        });
    }

    public void setFactory(EntryFactory factory) {
        this.factory = factory;
    }
}
