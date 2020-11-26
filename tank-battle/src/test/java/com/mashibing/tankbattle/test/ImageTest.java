package com.mashibing.tankbattle.test;

import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/19 11:41
 */
public class ImageTest {

    @Test
    public void test(){
        try {
            InputStream inputStream = ImageTest.class.getClassLoader().getResourceAsStream("images/0.gif");
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            Assert.assertNotNull(bufferedImage);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
