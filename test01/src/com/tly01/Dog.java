package com.tly01;
/**
 * �����ڲ���
 * @author Administrator
 *
 */
public class Dog {
	public interface Pet{
		public void beFriendly();
		public void play();
	}
	public static void main(String[] args) {
		Pet dog=new Pet(){

			@Override
			public void beFriendly() {
				System.out.println("�����");
			}

			@Override
			public void play() {
			 System.out.println("һ�������");
			}
			
		};
		dog.beFriendly();
		dog.play();
	}

}
