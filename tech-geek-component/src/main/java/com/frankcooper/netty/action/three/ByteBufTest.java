package com.frankcooper.netty.action.three;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelPipeline;

/**
 * Created by FrankCooper
 * Date 2019/2/8 15:33
 * Description
 */
public class ByteBufTest {

    public void testOne() {
        ByteBuf heapBuf = null;


        if (heapBuf.hasArray()) {                //1
            byte[] array = heapBuf.array();        //2
            int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();                //3
            int length = heapBuf.readableBytes();//4
//            handleArray(array, offset, length); //5
        }
    }

    private void testTwo() {
//        ChannelPipeline pipeline = null; // get reference to pipeline;
//        FirstHandler firstHandler = new FirstHandler(); //1
//        pipeline.addLast("handler1", firstHandler); //2
//        pipeline.addFirst("handler2", new SecondHandler()); //3
//        pipeline.addLast("handler3", new ThirdHandler()); //4
//
//        pipeline.remove("handler3"); //5
//        pipeline.remove(firstHandler); //6
//
//        pipeline.replace("handler2", "handler4", new ForthHandler()); //6
    }
}
