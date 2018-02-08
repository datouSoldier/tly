package com.tly02;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BeanFactory {
	/**
	 * pkg:package to scan;
	 */
	private String pkg;
	/**
	 * ��Componentע���value����ֵ��Ϊkey,��Ӧ������Ϊvalue,�����instanceMap��
	 */
	private Map<String, Object> instanceMap = new HashMap<>();

	/**
	 * ���ʹ���޲ι�����,֮����ǵ�ʹ��setPkg����
	 */
	public BeanFactory() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ���øù�������ص�ioc
	 * 
	 * @param pkg
	 */
	public BeanFactory(String pkg) {
		this.pkg = pkg;
		IoC();
	}

	private void IoC() {
		// ������ʽת��Ϊ�ļ�·����ʽ
		String pkgPath = pkg.replaceAll("\\.", "/");
		// ��ȡ��ǰproject�ľ���·��
		String projectPath = BeanFactory.class.getResource("/").toString();
		// �Ի�ȡ��·�����д���,ȥ����ͷ��file:/
		projectPath = projectPath.substring(projectPath.indexOf("/") + 1);
		// ��ȡָ��·���µ������ļ�
		// �������е��ļ����ļ���
		recursion(new File(projectPath + pkgPath));
		findResource();
	}

	// ��instanceMap�����е�ʵ����filed�ҳ�,�����Ƿ����@Resource,������ھͽ���ʵ����
	private void findResource() {
		// ��ȡָ��·��pkg�´���@Component�����ж���ʵ��
		Collection<Object> values = instanceMap.values();
		for (Object obj : values) {
			try {
				initResource(obj);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ��ʼ������@Resource��field
	 * 
	 * @param obj
	 *            field��ӵ����
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private void initResource(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Class<? extends Object> clz = obj.getClass();
		Field[] fields = clz.getDeclaredFields();
		for (Field f : fields) {
			Class<Resource> res = Resource.class;
			if (f.isAnnotationPresent(res)) {
				Resource r = f.getAnnotation(res);
				String resName = r.value();
				Object o = instanceMap.get(resName);
				f.setAccessible(true);
				f.set(obj, o);
			}
		}
	}

	/**
	 * �ݹ�Ѱ��ָ��·���µ��������ļ�
	 * 
	 * @param file
	 */
	private void recursion(File file) {
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isFile()) {
				fillMap(f);
			} else {
				recursion(f);
			}
		}
	}

	/**
	 * ���ҵ����ļ����д���,�������ļ�ͷ�ϵ�ע�����,����Ӧ��Ϣ��ŵ�map��
	 * 
	 * @param f
	 */
	private void fillMap(File f) {
		// ��ľ���·��
		String absolutePath = f.getAbsolutePath();
		// ��·��
		String pkgPath = absolutePath.replaceAll("\\\\", ".");
		// ��ȡ��·��,��ȥ���ļ���ʽ
		String objPath = pkgPath.substring(pkgPath.indexOf(pkg), pkgPath.length() - 6);
		try {
			// ͨ����·������ָ������
			Class<?> clz = Class.forName(objPath);

			Class<Component> annoClz = Component.class;

			Class<Resource> res = Resource.class;

			if (clz.isAnnotationPresent(annoClz)) {

				Component c = clz.getAnnotation(annoClz);

				Object instance = clz.newInstance();

				String instanceName = "";

				String annoValue = c.value();
				// ���ʹ�õ���Ĭ��ֵ,��������Ϊkey����map
				if ("".equals(annoValue)) {
					instanceName = instance.getClass().getSimpleName();
					// ��������һ����ĸ�ĳ�Сд
					instanceName = (instanceName.charAt(0) + "").toLowerCase() + instanceName.substring(1);
				} else {
					instanceName = annoValue;
				}

				// ��ŵ�map��,�������ֵ��Ϊ��,������Ѵ���ͬ��ʵ��.
				if (instanceMap.put(instanceName, instance) != null) {
					throw new Exception("����ͬ��ʵ��");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Map<String, Object> getInstances() {
		return instanceMap;
	}

	/**
	 * ����ʵ������ȡʵ��
	 * 
	 * @param instanceName
	 *            ʵ����
	 * @return ʵ��
	 */
	public <T> T getInstance(String instanceName) {
		return (T) instanceMap.get(instanceName);
	}

	public String getPkg() {
		return pkg;
	}

	/**
	 * ����set������ص�IoC
	 * 
	 * @param pkg
	 */
	public void setPkg(String pkg) {
		this.pkg = pkg;
		IoC();
	}

}