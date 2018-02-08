package com.tly01;
/**
 * ��̬�ڲ���
 * @author Administrator
 *
 */
public class Singleton {
	private static class LazyHolder{
		private static final Singleton INSTANCE=new Singleton();
	}
	private Singleton(){
		
	}
		public static final Singleton getInstance(){
			return LazyHolder.INSTANCE;
		}
	

}
