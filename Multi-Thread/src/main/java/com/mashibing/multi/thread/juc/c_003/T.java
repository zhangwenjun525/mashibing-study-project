/**
 * synchronized关键字
 * 对某个对象加锁
 * @author mashibing
 */

package com.mashibing.multi.thread.juc.c_003;

public class T {

	private int count = 10;

	//等同于在方法的代码执行时要synchronized(this)
	public synchronized void m() {
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}


}
