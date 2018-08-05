package rpcclient.proxy;

/**
 * Created by Administrator on 2018/8/4.
 */
public interface ProxyFactory {

    /**
     * 生成rpc代理
     * @param InterfaceType
     * @param host
     * @param port
     * @return
     */
    Object getProxy(Class InterfaceType, String host, int port);

}
