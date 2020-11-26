package com.mashibing.multi.thread.juc.C_026_01_ThreadPool;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/25 16:29
 */
public class T12_ForkJoinPool {

    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        for (int i = 0; i < nums.length; ++i){
            nums[i] = r.nextInt(100);
        }
    }

    static class AddTask extends RecursiveAction{

        private int start, end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if(end - start <= MAX_NUM){
                long sum = 0L;
                for(int i = start; i < end; ++i){
                    sum += nums[i];
                }
                System.out.println("from:" + start + " to:" + end + " = " + sum);
            }else{
                int middle = start +  (end - start) / 2;
                AddTask addTask1 = new AddTask(start, middle);
                AddTask addTask2 = new AddTask(middle, end);
                addTask1.fork();
                addTask2.fork();
            }
        }
    }

    static class AddTaskRet extends RecursiveTask<Long>{

        private int start, end;

        public AddTaskRet(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if(end - start <= MAX_NUM){
                long sum = 0L;
                for(int i = start; i < end; ++i){
                    sum += nums[i];
                }
                return sum;
            }else{
                int middle = start +  (end - start) / 2;
                AddTaskRet addTask1 = new AddTaskRet(start, middle);
                AddTaskRet addTask2 = new AddTaskRet(middle, end);
                addTask1.fork();
                addTask2.fork();

                return addTask1.join() + addTask2.join();
            }
        }
    }



    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        System.out.println(Arrays.stream(nums).sum());
        System.out.println(System.currentTimeMillis() - start);

        System.out.println("------------------------------------------");
        start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
       /* AddTask task = new AddTask(0, nums.length);
        forkJoinPool.execute(task);*/

        AddTaskRet task = new AddTaskRet(0, nums.length);
        forkJoinPool.execute(task);
        System.out.println(task.join());
        System.out.println(System.currentTimeMillis() - start);

        //System.in.read();
    }
}
