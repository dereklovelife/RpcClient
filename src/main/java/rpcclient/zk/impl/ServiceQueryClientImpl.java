package rpcclient.zk.impl;

import org.springframework.stereotype.Component;
import rpcclient.zk.ServiceQueryClient;

/**
 * Created by Administrator on 2018/8/4.
 */

@Component
public class ServiceQueryClientImpl implements ServiceQueryClient{

    public String QueryAddressByInterfaceType(Class interfaceType) {
        StringBuilder sb = new StringBuilder();
        sb.append("127.0.0.1");
        sb.append(",");
        sb.append("9090");
        return sb.toString();
    }

}
