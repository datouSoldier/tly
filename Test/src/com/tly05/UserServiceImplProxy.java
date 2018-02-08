package com.tly05;

public class UserServiceImplProxy implements UserService {
	private UserService userService;
	public UserServiceImplProxy(UserService userService){
		this.userService=userService;
	}

	@Override
	public void addUser(String userId, String userName) {
		try {
			System.out.println("��ʼִ��:add");
			userService.addUser(userId, userName);
			System.out.println("addִ�гɹ�");
		} catch (Exception e) {
			System.out.println("addִ��ʧ��");
		}
	}

	@Override
	public void delUser(String userId) {
		
	}

	@Override
	public void modifyUser(String userId, String userName) {
		
	}

	@Override
	public String findUser(String userId) {
		return null;
	}

}
