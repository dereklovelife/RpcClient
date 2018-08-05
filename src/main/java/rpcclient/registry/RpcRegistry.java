package rpcclient.registry;

/**
 * Created by Administrator on 2018/8/4.
 */
public interface RpcRegistry {

    /**
     * 从registry中获取一个代理类
     * @param interfaceType
     * @return
     */
    Object registAndGet(Class interfaceType);

}
