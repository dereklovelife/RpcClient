package rpcclient.entry;


import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2018/8/4.
 */
public class RpcProcessEntry {


    private Channel channel;

    private volatile byte[] result;

    private CountDownLatch latch = null;

    public byte[] process(byte[] requst){
        channel.writeAndFlush(Unpooled.copiedBuffer(new String(requst), CharsetUtil.UTF_8));
        latch = new CountDownLatch(1);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void countDown(){
        if(latch == null)
            return ;
        latch.countDown();
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public byte[] getResult() {
        return result;
    }

    public void setResult(byte[] result) {
        this.result = result;
    }
}
