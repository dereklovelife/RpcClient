package rpcclient.common.model;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/8/4.
 */
public class RpcRequest {


    // 调用的接口
    private String interfaceType;
    // 调用的方法
    private String methodName;
    // 传入的参数
    Object[] args;


    public String getInterfaceType() {
        return interfaceType;
    }


    public Object[] getArgs() {
        return args;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
