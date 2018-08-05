package rpcclient.registry.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rpcclient.proxy.ProxyFactory;
import rpcclient.registry.RpcRegistry;
import rpcclient.zk.ServiceQueryClient;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2018/8/4.
 */

@Component
public class RpcRegistyImpl implements RpcRegistry{

    // 缓存服务与地址的映射
    private ConcurrentHashMap<Class, String> ServiceAddressMap = new ConcurrentHashMap<Class, String>(5);

    // 注册中心client
    @Autowired
    private ServiceQueryClient serviceQueryClient;

    // 代理工厂
    @Autowired
    private ProxyFactory proxyFactory;

    public Object registAndGet(Class interfaceType) {

        // 1. 获取服务地址
        String addressStr = ServiceAddressMap.get(interfaceType);
        if(addressStr == null){
            addressStr = serviceQueryClient.QueryAddressByInterfaceType(interfaceType);
            if(addressStr == null){
                throw new RuntimeException("register error. No address info found.");
            }
            ServiceAddressMap.put(interfaceType, addressStr);
        }

        String[] addressInfo = addressStr.split(",");

        if(addressInfo.length != 2){
            throw new RuntimeException("Address info is not in correct pattern.");
        }

        String ip = addressInfo[0];

        //todo: 增加负载均很机制

        int port = Integer.parseInt(addressInfo[1]);
        // 2. 返回代理
        return proxyFactory.getProxy(interfaceType, ip, port);

    }

    public void setServiceQueryClient(ServiceQueryClient serviceQueryClient) {
        this.serviceQueryClient = serviceQueryClient;
    }

    public void setProxyFactory(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
    }
}
