package com.mashibing.multi.thread.disruptor.v2;

import com.lmax.disruptor.EventHandler;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/25 20:02
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println("value = " + longEvent.getValue() + ", sequence = " + l);
    }
}
