package com.tly01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * java nioʵ���ļ�����
 * @author Administrator
 *
 */
public class Demo0 {
	public static void main(String[] args) throws IOException {
		//ʹ��FileInputStream��һ���ļ�������
		FileInputStream fis=new FileInputStream("D:\\���߼����\\VMware-workstation-full-14.0.0.24051.exe");
		//ʹ��FileOutPutStream��һ���ļ������
		FileOutputStream fos=new FileOutputStream("D:\\ss\\a.exe");
		//�õ��ļ���������ͨ��
		FileChannel ifc=fis.getChannel();
		//�õ��ļ��������ͨ��
		FileChannel ofc=fos.getChannel();
		//����һ���ֽڻ�����,��СΪ1024
		ByteBuffer buffer=ByteBuffer.allocate(1024);
		while(true){
		//��ջ�����,ʹ�䴦�ڿɽ����ֽ�״̬
		buffer.clear();
		//���ļ�������ͨ�����ȡ����,��Сȡ���ڻ�������С,�Լ��ļ�ʣ���ֽڴ�С	
		int i=ifc.read(buffer);
		//�������ֵΪ-1��ʾ�Ѿ���ȡ���
		if(i==-1){
			break;
		}
		//��ת������,ʹ�䴦�ڿ�д��ͨ��״̬
		buffer.flip();
		//�ѻ���������д���ļ������ͨ��
		ofc.write(buffer);
		}
		fis.close();
		fos.close();
	}
}
