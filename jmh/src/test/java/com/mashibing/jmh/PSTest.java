package com.mashibing.jmh;

import org.openjdk.jmh.annotations.Benchmark;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/25 17:54
 */
public class PSTest {

    @Benchmark
    public void testForEach(){
        PS.foreach();
    }

}
