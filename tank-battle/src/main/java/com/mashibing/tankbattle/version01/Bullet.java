package com.mashibing.tankbattle.version01;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.mashibing.tankbattle.version01.Common.*;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/19 10:06
 */
public class Bullet {

    private int x, y;

    private Direction direction;

    private Rectangle rectangle;

    private Group group;

    private boolean alive;

    public Bullet(int x, int y, Direction direction, Group group) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
        this.alive = true;
        this.rectangle = new Rectangle(x, y , BULLET_WIDTH, BULLET_HEIGHT);
    }

    public void paint(Graphics graphics){
        graphics.drawImage(getBulletImage(), x, y, null);
        move();
    }

    public BufferedImage getBulletImage(){
        BufferedImage bufferedImage;
        switch (direction){
            case UP:
                bufferedImage = ResourceManagement.bulletU;
                break;
            case DOWN:
                bufferedImage = ResourceManagement.bulletD;
                break;
            case LEFT:
                bufferedImage = ResourceManagement.bulletL;
                break;
            case RIGHT:
                bufferedImage = ResourceManagement.bulletR;
                break;
            default:
                bufferedImage = ResourceManagement.bulletD;
        }
        return bufferedImage;
    }


    public void move(){
        if(direction.equals(Direction.DOWN)){
            y += BULLET_MOVE_SPEED;
        }

        if(direction.equals(Direction.UP)){
            y -= BULLET_MOVE_SPEED;
        }

        if(direction.equals(Direction.LEFT)){
            x -= BULLET_MOVE_SPEED;
        }

        if(direction.equals(Direction.RIGHT)){
            x += BULLET_MOVE_SPEED;
        }

        rectangle.setRect(x, y, BULLET_WIDTH, BULLET_HEIGHT);
    }

    public boolean isAlive() {
        if(!alive){
            return false;
        }

        if(x < 0 || y < 0 || x > GAME_WIDTH || y > GAME_HEIGHT){
            return false;
        }
        return true;
    }

    public void die(){
        alive = false;
    }

    public Group getGroup() {
        return group;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
