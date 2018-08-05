package rpcclient.netty;

import io.netty.channel.Channel;

/**
 * Created by Administrator on 2018/8/4.
 */
public interface NettyClient {

    /**
     * 生成一个Channel，根据host和port连接到Server端
     * @param host
     * @param port
     * @return
     */
    Channel startUp(String host, int port);
}
