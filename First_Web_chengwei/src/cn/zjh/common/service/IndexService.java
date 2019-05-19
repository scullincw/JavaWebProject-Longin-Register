package cn.zjh.common.service;

import java.util.ArrayList;

import com.jfinal.kit.StrKit;

import cn.zjh.common.kit.BaseResponse;
import cn.zjh.common.kit.ResultCodeEnum;

public class IndexService {
	/**
	 * 用户登录
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public BaseResponse login(String account, String password) {
//		将登录信息存储为一个新用户
		UserInfo userInfo = new UserInfo();
		userInfo.setAccount(account);
		userInfo.setPassword(password);

		BaseResponse baseResponse = new BaseResponse();
		Database database = new Database();

		ArrayList<UserInfo> userInfos = new ArrayList<UserInfo>();
		userInfos = database.getAccounts();

		if (StrKit.isBlank(account) || StrKit.isBlank(password)) {
			baseResponse.setResult(ResultCodeEnum.NO_ENOUGH_MES);// 登录失败_账号或密码为空
		} else if (userInfos.contains(userInfo)) { // 若用户信息中有此用户，允许登录
			baseResponse.setResult(ResultCodeEnum.LOGIN_SUCCESS);
			
			System.out.println("userInfo:account:" + userInfo.getAccount() + "  password:" + userInfo.getPassword()
			+ "  tel:" + userInfo.getTel()+ "  email:" + userInfo.getEmail());
			
			for(UserInfo target : userInfos) {
				
				System.out.println("account:" + target.getAccount() + "  password:" + target.getPassword()
				+ "  tel:" + target.getTel()+ "  email:" + target.getEmail());
				
				if(target.getAccount().equals(userInfo.getAccount())) {
					baseResponse.setData(target);
					
					System.out.println("target:account:" + target.getAccount() + "  password:" + target.getPassword()
					+ "  tel:" + target.getTel()+ "  email:" + target.getEmail());
				}
			}
		} else { // 登陆失败
			baseResponse.setResult(ResultCodeEnum.LOGIN_ERROR);
		}
		
		return baseResponse;
	}

	public BaseResponse register(String account, String password, String tel, String email) {
		UserInfo userInfo = new UserInfo();
		userInfo.setAccount(account);
		userInfo.setPassword(password);
		userInfo.setTel(tel);
		userInfo.setEmail(email);

		BaseResponse baseResponse = new BaseResponse();
		Database database = new Database();

		ArrayList<UserInfo> userInfos = new ArrayList<UserInfo>();
		userInfos = database.getAccounts();

		if (StrKit.isBlank(account) || StrKit.isBlank(password)) {
			baseResponse.setResult(ResultCodeEnum.NO_ENOUGH_MES);// 注册失败_账号或密码为空
		} else if (userInfos.contains(userInfo)) { // 账号存在-拒绝注册
			baseResponse.setResult(ResultCodeEnum.DB_UPDATE_ERROR);
		} else if (!userInfos.contains(userInfo)) { // 注册成功
			database.addAccount(userInfo);
			baseResponse.setResult(ResultCodeEnum.DB_UPDATE_SUCCESS);
		} else { // 注册失败
			baseResponse.setResult(ResultCodeEnum.LOGIN_ERROR);
		}
		
		return baseResponse;
	}
}
