package com.mashibing.multi.thread.disruptor.v1;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/25 20:21
 */
public class LongEventProducer {

    private RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer buffer){
        long sequence = ringBuffer.next();
        try{
            LongEvent event = ringBuffer.get(sequence);
            event.setValue(buffer.getLong(0));
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
