package com.mashibing.underlying.knowledge.cpu.disorder;

/**
 * CPU乱序执行示例
 *
 * @author zhangwj
 * @version 1.0
 * @date 2020/10/22 10:08
 */
public class T01_Cpu_Dis_Order {

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws Exception {
        int i = 0;

        for(;;){
            i++;
            x = 0; y = 0;
            a = 0; b = 0;

            Thread one = new Thread(() -> {
                a = 1;
                x = b;
            });

            Thread two = new Thread(() -> {
                b = 1;
                y = a;
            });

            one.start();
            two.start();

            one.join();
            two.join();
            String result = "第" + i + "次(" + x + "," + y + ")";
            if(x == 0  && y == 0){
                System.out.println(result);
            }
        }
    }
}
