package rpcclient.zk;

/**
 * 注册中心client
 * Created by Administrator on 2018/8/4.
 */
public interface ServiceQueryClient {


    String QueryAddressByInterfaceType(Class interfaceType);
}
