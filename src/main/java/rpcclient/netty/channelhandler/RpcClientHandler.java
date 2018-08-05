package rpcclient.netty.channelhandler;

import rpcclient.entry.RpcProcessEntry;
import rpcclient.entry.RpcProcessEntryHolder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by Administrator on 2018/8/4.
 */

@Component
@ChannelHandler.Sharable
public class RpcClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

    // 通过channel找到对应的entry
    @Autowired
    private RpcProcessEntryHolder holder;

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuffer) throws Exception {
        Channel channel = channelHandlerContext.channel();
        RpcProcessEntry entry = holder.getEntryByChannel(channel);
        if(byteBuffer == null)
            return ;
        byte[] resultByte = new byte[byteBuffer.readableBytes()];
        byteBuffer.readBytes(resultByte);
        entry.setResult(resultByte);
        // 通知线程已经得到了返回的结果
        entry.countDown();
    }


    public void setHolder(RpcProcessEntryHolder holder) {
        this.holder = holder;
    }
}
