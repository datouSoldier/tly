package com.tly01;
/**
 * ���������ڵ������ڲ���
 * @author Administrator
 *
 */
public class Dog1 {
	static abstract class Ball{
		abstract String getName();
	}
	
	void play(Ball b){
		System.out.println(b.getName());
	}
	public static void main(String[] args) {
		Dog1 dog=new Dog1();
		dog.play(new Ball(){

			@Override
			String getName() {
				return "qiu qiu";
			}
		});
	}
}
