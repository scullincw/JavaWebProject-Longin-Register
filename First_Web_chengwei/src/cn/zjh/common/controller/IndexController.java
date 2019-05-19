package cn.zjh.common.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;

import cn.zjh.common.kit.ResultCodeEnum;
import cn.zjh.common.kit.BaseResponse;
import cn.zjh.common.service.IndexService;

public class IndexController extends Controller {
	public static IndexService indexService = new IndexService();
	BaseResponse baseResponse = new BaseResponse();

	public void login() {
		String account = getPara("account");
		String password = getPara("password");
		if (!StrKit.isBlank(account) && !StrKit.isBlank(password)) {
			baseResponse = indexService.login(account, password);
			if (baseResponse.getResultCode().equals("4000")) {
				setSessionAttr(account, "ready");
			} else {
				setSessionAttr(account, "unready");
			}
			System.out.println("用户" + account + "的登录状态是：" + getSessionAttr(account).toString());
		} else {
			baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
		}
		renderJson(baseResponse);
	}

	public void logout() {
		baseResponse = new BaseResponse();
		String account = getPara("account");// 前端传来用户账号
		if (getSessionAttr(account) != null && getSessionAttr(account).equals("ready")) {// 如果session中没有以用户为属性名的属性，说明用户未登录
			removeSessionAttr(account);
			System.out.println("用户" + account + "已退出！");
			baseResponse.setResult(ResultCodeEnum.LOGOUT_SUCCESS);
		} else {
			System.out.println("该用户未登录！");
			baseResponse.setResult(ResultCodeEnum.NO_LOGIN_USER);
		}
		renderJson(baseResponse);
	}

	public void register() {
		String account = getPara("account");
		String password = getPara("password");
		String tel = getPara("tel");
		String email = getPara("email");
//		System.out.println(account);

		if (!StrKit.isBlank(account) && !StrKit.isBlank(password) && !StrKit.isBlank(tel) && !StrKit.isBlank(email)) {
			baseResponse = indexService.register(account, password, tel, email);
			if (baseResponse.getResultCode().equals("2002")) {
				setSessionAttr(account, "ready");
			} else {
				setSessionAttr(account, "unready");
			}
			System.out.println("用户" + account + "的注册状态是：" + getSessionAttr(account).toString());
		} else {
			baseResponse.setResult(ResultCodeEnum.PARA_FORMAT_ERROR);
		}
		renderJson(baseResponse);
	}
}
