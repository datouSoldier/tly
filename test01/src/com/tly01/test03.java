package com.tly01;

public class test03 {
	public static void main(String[] args) {
		Singleton s1=Singleton.getInstance();
		Singleton s2=Singleton.getInstance();
		if(s1==s2){
			System.out.println("����ʵ����һ����!");
		}else{
			System.out.println("����ʵ���ǲ�һ����!");
		}
		
	}

}
