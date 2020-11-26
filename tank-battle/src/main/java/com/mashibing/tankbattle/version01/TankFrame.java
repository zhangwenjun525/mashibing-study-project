package com.mashibing.tankbattle.version01;

import cn.hutool.core.collection.CollUtil;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mashibing.tankbattle.version01.Common.*;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/18 20:30
 */
public class TankFrame  extends Frame{

    private int x = 350;

    private int y = 550;

    // 自己
    private Tank tank;


    // 敌人
    private List<Tank> enemyTankList = new ArrayList<>();

    private List<Explosion> explosionList = new ArrayList<>();


    private MyKeyListener keyListener = new MyKeyListener();

    public TankFrame() throws HeadlessException {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setResizable(false);
        this.setTitle("tank war");
        this.setVisible(true);

        tank = new Tank(x, y, Direction.UP, Group.OWN);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        for(int i = 0; i < ENEMY_COUNT; ++i){
            Tank enemyTank = new Tank(135 + 120 * i, 50, Direction.DOWN, Group.ENEMY);
            enemyTankList.add(enemyTank);
        }

        this.addKeyListener(keyListener);
    }

    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(null == offScreenImage){
            offScreenImage = createImage(GAME_WIDTH, GAME_HEIGHT);
        }

        Graphics graphics = offScreenImage.getGraphics();
        Color c = graphics.getColor();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        graphics.setColor(c);
        paint(graphics);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        if(tank.isLiving()){
            for(int i = 0, len = enemyTankList.size(); i < len; ++i){
                enemyTankList.get(i).paint(g);
            }
            tank.paint(g);

            doCollision();


            if(CollUtil.isNotEmpty(enemyTankList)){
                enemyTankList = enemyTankList.stream().filter(enemy -> enemy.isLiving()).collect(Collectors.toList());
            }

            if(CollUtil.isNotEmpty(explosionList)){
                for(int i = 0; i < explosionList.size(); ++i){
                    explosionList.get(i).paint(g);
                }

                explosionList = explosionList.stream().filter(explosion -> !explosion.isEnd()).collect(Collectors.toList());
            }
        }else{
            Color color = g.getColor();
            g.setColor(Color.yellow);
            g.drawString("Game Over!", 400, 300);
            g.setColor(color);
        }
    }


    public void doCollision(){
        for(Tank enemyTank : enemyTankList){
             // 判断敌方的子弹是否打中
            List<Bullet> bulletList = enemyTank.getBulletList();
            if(CollUtil.isEmpty(bulletList)){
                break;
            }

            for(Bullet bullet : bulletList){
                collide(tank, bullet);
            }
        }

        // 判断自己的坦克有没有打中敌方
        List<Bullet> ownBulletList = tank.getBulletList();

        for(Tank enemyTank : enemyTankList){
            if(CollUtil.isEmpty(ownBulletList)){
                break;
            }

            for(Bullet bullet : ownBulletList){
                collide(enemyTank, bullet);
            }

        }
    }

    public void collide(Tank tank, Bullet bullet){
        if(!tank.isLiving() || !bullet.isAlive()){
            return;
        }


        if(tank.getGroup().equals(bullet.getGroup())){
            return;
        }

        if(tank.getRectangle().intersects(bullet.getRectangle())){
            tank.die();
            bullet.die();
            createExplosion(tank);
        }
    }

    public void createExplosion(Tank tank){
        int x = tank.getX() + TANK_WIDTH / 2 - EXPLOSION_WIDTH / 2;
        int y = tank.getY() + TANK_HEIGHT / 2 - EXPLOSION_HEIGHT / 2;
        Explosion explosion = new Explosion(x, y);
        explosionList.add(explosion);
    }




    class MyKeyListener extends KeyAdapter{

        boolean bL = false;

        boolean bR = false;

        boolean bU = false;

        boolean bD = false;

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    tank.shoot();
                    return;
                default:
                    break;
            }
            setTankDir();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setTankDir();
        }

        private void setTankDir(){
            if(!bL && !bR && !bU && !bD){
                tank.setMoving(false);
                return;
            }

            if(bL){
                tank.setDirection(Direction.LEFT);
            }

            if(bR){
                tank.setDirection(Direction.RIGHT);
            }

            if(bU){
                tank.setDirection(Direction.UP);
            }

            if(bD){
                tank.setDirection(Direction.DOWN);
            }
            tank.setMoving(true);
        }
    }
}
