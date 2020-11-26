package com.mashibing.multi.thread.disruptor.v1;

import com.lmax.disruptor.EventFactory;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/25 19:56
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
