package com.xz.greendao_xz.utils;

/**
 * @ClassName: UserUtils
 * @Description: 此类用于处理和用户相关的工具类
 * @author ghy
 * @date 2016-6-17 下午3:35:16
 */
public class UserUtils {
	private static String userName = "";// 默认为空

	/**
	 * 判断用户名和密码是不是为空或者不符合规范
	 *
	 * @param name
	 *            用户名
	 * @param password
	 *            用户密码
	 * @return true 表示符合，false 表示不符合
	 */
	public static boolean isRightUserInfo(String name, String password) {
		if (name == null || name.equals("")) {
			return false;
		}
		if (password == null || password.equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * @Title: saveUser
	 * @Description: 保存当前登录的用户信息
	 * @param @param name 设定文件
	 * @return void 返回类型
	 */
	public static void saveUser(String name) {
		userName = name;
	}

	/**
	 * @Title: clearUser
	 * @Description:清除当前登录信息
	 * @param 设定文件
	 * @return void 返回类型
	 */
	public static void clearUser() {
		userName = "";
	}

	/**
	 * @Title: getCurrentUser
	 * @Description: 获取当前登录的用户
	 * @param @return 设定文件
	 * @return String 返回类型
	 */
	public static String getCurrentUser() {
		return userName;
	}

}
