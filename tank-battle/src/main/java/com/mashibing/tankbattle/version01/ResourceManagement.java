package com.mashibing.tankbattle.version01;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/19 14:01
 */
public class ResourceManagement {

    public static BufferedImage tankL, tankU, tankR, tankD, bulletL, bulletU, bulletR, bulletD;

    public static BufferedImage[] explosionImages = new BufferedImage[16];

    static {
        try {
            tankL= ImageIO.read(ResourceManagement.class.getClassLoader().getResource("images/tankL.gif"));
            tankU= ImageIO.read(ResourceManagement.class.getClassLoader().getResource("images/tankU.gif"));
            tankR= ImageIO.read(ResourceManagement.class.getClassLoader().getResource("images/tankR.gif"));
            tankD= ImageIO.read(ResourceManagement.class.getClassLoader().getResource("images/tankD.gif"));
            bulletL= ImageIO.read(ResourceManagement.class.getClassLoader().getResource("images/bulletL.gif"));
            bulletU= ImageIO.read(ResourceManagement.class.getClassLoader().getResource("images/bulletU.gif"));
            bulletR= ImageIO.read(ResourceManagement.class.getClassLoader().getResource("images/bulletR.gif"));
            bulletD= ImageIO.read(ResourceManagement.class.getClassLoader().getResource("images/bulletD.gif"));

            for(int i = 0; i < explosionImages.length; ++i){
                explosionImages[i] = ImageIO.read(ResourceManagement.class.getClassLoader().getResource("images/e" + (i + 1) + ".gif"));
            }

        }catch (IOException e){
            e.printStackTrace();
        }



    }
}
