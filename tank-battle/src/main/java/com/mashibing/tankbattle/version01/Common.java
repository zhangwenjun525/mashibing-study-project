package com.mashibing.tankbattle.version01;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/19 11:12
 */
public class Common {

    public static final int GAME_WIDTH = 800;

    public static final int GAME_HEIGHT = 600;

    public static final int TANK_WIDTH = ResourceManagement.tankD.getWidth();

    public static final int TANK_HEIGHT = ResourceManagement.tankD.getHeight();

    public static final int BULLET_WIDTH = ResourceManagement.bulletD.getWidth();

    public static final int BULLET_HEIGHT = ResourceManagement.bulletD.getHeight();

    public static final int TANK_MOVE_SPEED = 5;

    public static final int BULLET_MOVE_SPEED = 10;

    public static final int ENEMY_COUNT = 5;

    public static final int EXPLOSION_WIDTH = ResourceManagement.explosionImages[0].getWidth();

    public static final int EXPLOSION_HEIGHT = ResourceManagement.explosionImages[0].getHeight();

    public static final int EXPLOSION_IMAGE_COUNT = ResourceManagement.explosionImages.length;

}
