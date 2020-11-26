package com.mashibing.tankbattle.version01;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/18 20:44
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        while (true){
            TimeUnit.MILLISECONDS.sleep(50);
            tankFrame.repaint();
        }
    }
}
