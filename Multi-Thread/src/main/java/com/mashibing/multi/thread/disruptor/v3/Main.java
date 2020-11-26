package com.mashibing.multi.thread.disruptor.v3;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import java.util.concurrent.Executors;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/25 20:05
 */
public class Main {

    public static void main(String[] args) {

        int bufferSize = 16;

        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent::new, bufferSize, Executors.defaultThreadFactory());

        disruptor.handleEventsWith((event, sequence, endOfBatch) -> {
            System.out.println("value = " + event.getValue() + ", sequence = " + sequence);
        });

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        //ringBuffer.publishEvent((longEvent, l, value) -> longEvent.setValue(value), 1000L);
        ringBuffer.publishEvent((longEvent, l, v1, v2) -> longEvent.setValue(v1 + v2), 1000L, 2000L);

        disruptor.shutdown();

    }
}
