import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rpcclient.integration.rpctest.HashService;
import rpcclient.integration.rpctest.service.StringService;
import rpcclient.registry.RpcRegistry;

/**
 * Created by Administrator on 2018/8/4.
 */
public class Main {


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("nettyRpcClient.xml");
        RpcRegistry registry = context.getBean(RpcRegistry.class);
        StringService stringService = (StringService) registry.registAndGet(StringService.class);
        System.out.println("reverse");
        System.out.println("buildModel");
        System.out.println(stringService.buildModel("lks", "fool"));
        System.out.println("over");
    }
}
