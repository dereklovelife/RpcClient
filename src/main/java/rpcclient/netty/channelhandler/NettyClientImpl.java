package rpcclient.netty.channelhandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import rpcclient.netty.NettyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * Created by Administrator on 2018/8/4.
 */

@Component
public class NettyClientImpl implements NettyClient{

    @Autowired
    private EventLoopGroup group;

    @Autowired
    private RpcClientHandler rpcClientHandler;

    public Channel startUp(String host, int port) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host,port))
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(rpcClientHandler);
                    }
                });
        ChannelFuture f = null;
        try {
            f = bootstrap.connect().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final Channel channel = f.channel();

        // 异步执行
        new Thread(new Runnable() {
            public void run() {
                try {
                    channel.closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return channel;
    }

}
