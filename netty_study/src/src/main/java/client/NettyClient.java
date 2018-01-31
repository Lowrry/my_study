package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * User: luxiaochun<p/>
 * Date: 2017/11/29<p/>
 * Time: 19:49<p/>
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            Bootstrap clientBoot = new Bootstrap();
            clientBoot.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                                 @Override
                                 protected void initChannel(SocketChannel ch) throws Exception {
                                     ch.pipeline().addLast(new ClientHandler());
                                 }
                             }
                    );

            ChannelFuture future = clientBoot.connect("127.0.0.1", 8080).sync();
            future.channel().closeFuture().sync();

        } finally {
            workGroup.shutdownGracefully();
        }
    }

    private static class ClientHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {     // 连接激活了,client先向server发送一个请求
            byte[] ask = "Whats the time".getBytes();
//            ByteBuf byteBuf = Unpooled.buffer(ask.length);
//            byteBuf.writeBytes(ask);

            ByteBuf byteBuf = Unpooled.copiedBuffer(ask);

            ctx.writeAndFlush(byteBuf);

            System.out.println("1. Client send success !");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {   //读取服务端返回的数据
            ByteBuf resp = (ByteBuf) msg;
            byte[] bytes = new byte[resp.readableBytes()];
            resp.readBytes(bytes);
            System.out.println("2. Get from server : " + new String(bytes, "UTF-8"));
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            super.channelReadComplete(ctx);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//            super.exceptionCaught(ctx, cause);
            System.out.println("Client send exceptionCaught !");
            cause.printStackTrace();
            ctx.close();
        }

    }


}