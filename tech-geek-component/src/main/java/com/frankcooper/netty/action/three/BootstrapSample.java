package com.frankcooper.netty.action.three;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.channel.socket.oio.OioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by FrankCooper
 * Date 2019/2/10 10:34
 * Description
 */
public class BootstrapSample {


    private static void testOne() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap(); //1
        bootstrap.group(group) //2
                .channel(NioSocketChannel.class) //3
                .handler(new SimpleChannelInboundHandler<ByteBuf>() { //4
                    @Override
                    protected void channelRead0(
                            ChannelHandlerContext channelHandlerContext,
                            ByteBuf byteBuf) throws Exception {
                        System.out.println("Received data");
                        byteBuf.clear();
                    }
                });
        ChannelFuture future = bootstrap.connect(
                new InetSocketAddress("www.manning.com", 80)); //5
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture)
                    throws Exception {
                if (channelFuture.isSuccess()) {
                    System.out.println("Connection established");
                } else {
                    System.err.println("Connection attempt failed");
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }

    private static void testTwo() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap(); //1
        bootstrap.group(group) //2
                .channel(OioSocketChannel.class) //3
                .handler(new SimpleChannelInboundHandler<ByteBuf>() { //4
                    @Override
                    protected void channelRead0(
                            ChannelHandlerContext channelHandlerContext,
                            ByteBuf byteBuf) throws Exception {
                        System.out.println("Reveived data");
                        byteBuf.clear();
                    }
                });
        ChannelFuture future = bootstrap.connect(
                new InetSocketAddress("www.manning.com", 80)); //5
        future.syncUninterruptibly();
    }

    private static void testThree() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap(); //1
        bootstrap.group(group) //2
                .channel(NioServerSocketChannel.class) //3
                .childHandler(new SimpleChannelInboundHandler<ByteBuf>() { //4
                                  @Override
                                  protected void channelRead0(ChannelHandlerContext ctx,
                                                              ByteBuf byteBuf) throws Exception {
                                      System.out.println("Reveived data");
                                      byteBuf.clear();
                                  }
                              }
                );
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(8080)); //5
        future.addListener(new ChannelFutureListener() {
                               @Override
                               public void operationComplete(ChannelFuture channelFuture)
                                       throws Exception {
                                   if (channelFuture.isSuccess()) {
                                       System.out.println("Server bound");
                                   } else {
                                       System.err.println("Bound attempt failed");
                                       channelFuture.cause().printStackTrace();
                                   }
                               }
                           }
        );
    }

}
