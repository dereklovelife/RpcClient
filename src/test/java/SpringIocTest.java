import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rpcclient.zk.ServiceQueryClient;

/**
 * Created by Administrator on 2018/8/4.
 */

public class SpringIocTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("nettyRpcClient.xml");
        ServiceQueryClient client = context.getBean(ServiceQueryClient.class);
        System.out.println(client);

    }
}
