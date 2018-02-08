package com.tly03;


import java.util.Random;

public class Demo0 {
    private static ThreadLocal<Integer> threadData=new ThreadLocal<Integer>();
	public static void main(String[] args) {
		for (int i = 0; i < 8; i++) {
			// ����һ���߳�
			new Thread(new Runnable() {
				@Override
				public void run() {
					// ÿ�ζ����ȡһ���µ������,������ÿ�ζ���ȥ���ݿ��ȡһ���µĲ���
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName() + "has put data:" + data);
					threadData.set(data);
					new A().get();
					new B().get();
				}
			}).start();
		}

	}

	static class A {
		public void get() {
			int data=threadData.get();
			System.out.println("A from " + Thread.currentThread().getName() + " get data :" + data);
		}
	}

	static class B {
		public void get() {
			int data=threadData.get();
			System.out.println("B from " + Thread.currentThread().getName() + " get data :" + data);
		}
	}
}
