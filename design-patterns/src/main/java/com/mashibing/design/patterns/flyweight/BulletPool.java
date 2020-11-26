package com.mashibing.design.patterns.flyweight;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 享元模式
 *
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/26 9:10
 */
public class BulletPool {

    class Bullet {
        public UUID id = UUID.randomUUID();
        boolean living = true;

        @Override
        public String toString() {
            return "Bullet{" +
                    "id=" + id +
                    ", living=" + living +
                    '}';
        }
    }

    List<Bullet> bullets = new ArrayList<>();

    {
        for(int i = 0; i < 5; ++i){
            bullets.add(new Bullet());
        }
    }

    public Bullet getBullet(){
        for(int i=0; i<bullets.size(); i++) {
            Bullet b = bullets.get(i);
            if(!b.living) {
                return b;
            }
        }

        return new Bullet();
    }

    public static void main(String[] args) {
        BulletPool bp = new BulletPool();

        for(int i=0; i<10; i++) {
            Bullet b = bp.getBullet();
            System.out.println(b);
        }
    }



}
