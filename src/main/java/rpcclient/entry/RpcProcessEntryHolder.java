package rpcclient.entry;

import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Channel,RpcProcessEntry map
 * Created by Administrator on 2018/8/4.
 */

@Component
public class RpcProcessEntryHolder {

    private ConcurrentHashMap<Channel, RpcProcessEntry> innerMap = new ConcurrentHashMap<Channel, RpcProcessEntry>(5);

    public RpcProcessEntry getEntryByChannel(Channel channel){
        return innerMap.get(channel);
    }

    public void putChannelEntryPair(Channel channel, RpcProcessEntry entry){
        innerMap.put(channel, entry);
    }

    public void deleteByChannel(Channel channel){
        innerMap.remove(channel);
    }
}
