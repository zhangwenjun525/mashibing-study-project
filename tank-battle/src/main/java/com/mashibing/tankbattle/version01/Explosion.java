package com.mashibing.tankbattle.version01;

import java.awt.*;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/19 16:14
 */
public class Explosion {

    private int x, y;

    private boolean end;

    private int step = 0;

    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics graphics){
        if(step > Common.EXPLOSION_IMAGE_COUNT - 1){
            end = true;
            return;
        }
        graphics.drawImage(ResourceManagement.explosionImages[step++], x, y, null);
    }

    public boolean isEnd() {
        return end;
    }
}
