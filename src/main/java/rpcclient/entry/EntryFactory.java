package rpcclient.entry;

import io.netty.channel.Channel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import rpcclient.netty.NettyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 连接实体entry工程
 *
 * Created by Administrator on 2018/8/4.
 */

@Component
public class EntryFactory {

    @Autowired
    private NettyClient client;

    @Autowired
    private RpcProcessEntryHolder holder;

    public RpcProcessEntry getRpcEntry(String host, int port){
        final Channel channel = client.startUp(host, port);
        RpcProcessEntry entry = new RpcProcessEntry();
        entry.setChannel(channel);

        holder.putChannelEntryPair(channel, entry);

        channel.closeFuture().addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) throws Exception {
                holder.deleteByChannel(channel);
            }
        });

        return entry;
    }


    public void setClient(NettyClient client) {
        this.client = client;
    }

    public NettyClient getClient() {
        return client;
    }

    public RpcProcessEntryHolder getHolder() {
        return holder;
    }

    public void setHolder(RpcProcessEntryHolder holder) {
        this.holder = holder;
    }
}
