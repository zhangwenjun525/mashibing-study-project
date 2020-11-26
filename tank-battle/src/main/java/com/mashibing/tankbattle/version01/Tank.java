package com.mashibing.tankbattle.version01;

import cn.hutool.core.collection.CollUtil;

import java.awt.*;
import static com.mashibing.tankbattle.version01.Common.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/19 9:05
 */
public class Tank {

    private int x ;

    private int y;

    private List<Bullet> bulletList = new ArrayList<>();

    private boolean moving;

    private boolean living = true;

    private Group group;

    private Direction direction = Direction.DOWN;

    private Rectangle rectangle;

    private Random random = new Random();

    public Tank(int x, int y, Direction direction, Group group) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
        rectangle = new Rectangle(x, y, TANK_WIDTH, TANK_HEIGHT);
    }

    public void paint(Graphics graphics){
        // 如果以及死亡，不需要再画
        if(!living){
            return;
        }

        graphics.drawImage(getTankImage(), x, y, null);


        if(Group.ENEMY.equals(this.group) && random.nextInt(100) > 90){
            randomDirect();
            shoot();
        }

        if(moving || Group.ENEMY.equals(this.group)){
            move();
        }

        if(CollUtil.isNotEmpty(bulletList)){
            bulletList = bulletList.stream().filter(bullet -> bullet.isAlive()).collect(Collectors.toList());
            bulletList.stream().forEach(bullet -> bullet.paint(graphics));
        }

        boundCheck();
    }

    private void randomDirect(){
        this.direction = Direction.values()[random.nextInt(4)];
    }

    private void boundCheck(){
        if(this.x < 2){
            this.x = 2;
        }

        if(this.y < 2){
            this.y = 2;
        }

        if(this.x > (GAME_WIDTH - TANK_WIDTH - 2)){
            this.x = GAME_WIDTH - TANK_WIDTH - 2;
        }

        if(this.y > (GAME_HEIGHT - TANK_HEIGHT - 2)){
            this.y = GAME_HEIGHT - TANK_HEIGHT - 2;
        }
    }

    public BufferedImage getTankImage(){
        BufferedImage bufferedImage;
        switch (direction){
            case UP:
                bufferedImage = ResourceManagement.tankU;
                break;
            case DOWN:
                bufferedImage = ResourceManagement.tankD;
                break;
            case LEFT:
                bufferedImage = ResourceManagement.tankL;
                break;
            case RIGHT:
                bufferedImage = ResourceManagement.tankR;
                break;
            default:
                bufferedImage = ResourceManagement.tankD;
        }
        return bufferedImage;
    }

    public void move(){
        if(direction.equals(Direction.DOWN)){
            y += TANK_MOVE_SPEED;
        }

        if(direction.equals(Direction.UP)){
            y -= TANK_MOVE_SPEED;
        }

        if(direction.equals(Direction.LEFT)){
            x -= TANK_MOVE_SPEED;
        }

        if(direction.equals(Direction.RIGHT)){
            x += TANK_MOVE_SPEED;
        }

        rectangle.setRect(x, y, TANK_WIDTH, TANK_HEIGHT);
    }

    public void shoot(){
       int bulletX = x + TANK_WIDTH / 2 - BULLET_WIDTH / 2;
       int bulletY = y + TANK_HEIGHT / 2 - BULLET_HEIGHT / 2;
       Bullet bullet =  new Bullet(bulletX, bulletY, direction, group);
       bulletList.add(bullet);
    }

   public void die(){
        living = false;
   }

    public boolean isMoving() {
        return moving;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public Group getGroup() {
        return group;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
