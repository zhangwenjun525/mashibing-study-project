package com.mashibing.multi.thread.disruptor.v2;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/25 20:05
 */
public class Main {

    public static void main(String[] args) {
        LongEventFactory factory = new LongEventFactory();

        int bufferSize = 16;

        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, Executors.defaultThreadFactory());

        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer buffer = ByteBuffer.allocate(8);

        for(long l = 0; l < 100; ++l){
            buffer.putLong(0, l);
            producer.onData(buffer);

            try{
                TimeUnit.MILLISECONDS.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        disruptor.shutdown();

    }
}
