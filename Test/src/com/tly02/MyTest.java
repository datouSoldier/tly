
package com.tly02;

import org.junit.Test;

public class MyTest {
	@Test
	public void test() {
		// pkgToScan:Ҫɨ��İ�·��,���ͬǰ׺�İ���ݹ鴦��
		String pkgToScan = "com.tly02";
		BeanFactory bf = new BeanFactory(pkgToScan);
		IStudentService iss = bf.getInstance("studentServiceImp");
		// AOP��
		iss = DynamicProxy.bind(iss);
		iss.save();

	}
}