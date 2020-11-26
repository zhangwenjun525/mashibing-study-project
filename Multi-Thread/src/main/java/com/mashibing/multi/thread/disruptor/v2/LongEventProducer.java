package com.mashibing.multi.thread.disruptor.v2;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import org.omg.CORBA.TRANSACTION_MODE;

import java.nio.ByteBuffer;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/25 20:21
 */
public class LongEventProducer {

    private RingBuffer<LongEvent> ringBuffer;

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer>  TRANSLATOR_ONE_ARG = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
        @Override
        public void translateTo(LongEvent longEvent, long l, ByteBuffer buffer) {
            longEvent.setValue(buffer.getLong(0));
        }
    };

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer buffer){
        ringBuffer.publishEvent(TRANSLATOR_ONE_ARG, buffer);
    }
}
