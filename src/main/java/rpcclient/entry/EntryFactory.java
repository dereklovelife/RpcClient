package rpcclient.entry;

import io.netty.channel.Channel;
import rpcclient.netty.NettyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/8/4.
 */

@Component
public class EntryFactory {

    @Autowired
    private NettyClient client;

    @Autowired
    private RpcProcessEntryHolder holder;

    public RpcProcessEntry getRpcEntry(String host, int port){
        Channel channel = client.startUp(host, port);
        RpcProcessEntry entry = new RpcProcessEntry();
        entry.setChannel(channel);
        holder.putChannelEntryPair(channel, entry);
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
