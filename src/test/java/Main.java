import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.rpctest.service.StringService;
import rpcclient.registry.RpcRegistry;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2018/8/4.
 */
public class Main {


    public static void main(String[] args) throws InterruptedException {

        ApplicationContext context = new ClassPathXmlApplicationContext("nettyRpcClient.xml");
        final RpcRegistry registry = context.getBean(RpcRegistry.class);
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        StringService stringService = (StringService) registry.registAndGet(StringService.class);
                        System.out.println(stringService.buildModel("lks", "fool"));
                        System.out.println(stringService.reverseString("lks"));
                        System.out.println("call over.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            }).start();
        }


        countDownLatch.await();

        System.out.println("Check innerMap");
    }
}
