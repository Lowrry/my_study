package com.github.my_study.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.Date;

/**
 * User: luxiaochun<p/>
 * Date: 2017/11/29<p/>
 * Time: 19:49<p/>
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();     //todo NioEventLoopGroup 这个需要后续看看源码才能理解Group里面都干嘛呢
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBoot = new ServerBootstrap();

            serverBoot.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024) //todo SO_BACKLOG, 这个需要把Socket协议看一看了
                    .childHandler(new ChildHandler());

            System.out.println("===> Server start success <===");

            ChannelFuture future = serverBoot.bind(8080).sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }

    private static class ChildHandler extends ChannelInitializer {

        @Override
        protected void initChannel(Channel ch) throws Exception {
            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {  // 这里是new出一个ChannelHandler, 装配到pipeline中.
                @Override
                public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
                    super.channelRegistered(ctx);
                }

                @Override
                public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
                    super.channelUnregistered(ctx);
                }

                @Override
                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                    super.channelActive(ctx);
                    System.out.println("channelActive ... ");
                }

                @Override
                public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                    super.channelInactive(ctx);
                    System.out.println("channelInactive ... ");
                }

                @Override
                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                                super.channelRead(ctx, msg);

                    // 这里的msg是ByteBuf对象
                    ByteBuf byteBuf = (ByteBuf) msg;
                    byte[] bytes = new byte[byteBuf.readableBytes()];
                    byteBuf.readBytes(bytes);

                    System.out.println("channelRead bytes = " + new String(bytes));

                    ByteBuf resp = Unpooled.copiedBuffer(new Date().toString().getBytes());
                    // 这里ctx.write对象也必须是ByteBuf!
                    ctx.write(resp);
                }

                @Override
                public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//                                super.channelReadComplete(ctx);
                    System.out.println("channelReadComplete read complete");
                    ctx.flush();
                }

                @Override
                public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                    super.userEventTriggered(ctx, evt);
                }

                @Override
                public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
                    super.channelWritabilityChanged(ctx);
                }

                @Override
                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//                                super.exceptionCaught(ctx, cause);
                    cause.printStackTrace();
                }

                @Override
                public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                    super.handlerAdded(ctx);
                    System.out.println("handlerAdded ... ");
                }

                @Override
                public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
                    super.handlerRemoved(ctx);
                    System.out.println("handlerRemoved ... ");
                }
            });
        }
    }


}
