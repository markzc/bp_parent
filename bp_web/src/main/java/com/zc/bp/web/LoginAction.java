package com.zc.bp.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.zc.bp.domain.User;
import com.zc.bp.utils.SysConstant;
import com.zc.bp.utils.UtilFuns;

/**
 * @Description:
 * @Author:		张冲
 * @Company:	http://markzc.github.io
 * @CreateDate:	2016/12/31
 */
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;



	//SSH传统登录方式
	public String login() throws Exception {
		//1.与Shiro交互
		Subject subject = SecurityUtils.getSubject();
		
		if(subject.isAuthenticated()){
			return SUCCESS;
		}
		
		if(UtilFuns.isEmpty(username)){
			return "login";
		}
		
		try {

			//2.调用subject中的方法，来实现登录
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);//将用户在界面输入的用户名密码进行封装
			subject.login(token);//当login执行时，就会自动跳入authRealm域中的认证方法
			
			//3.从Shiro中取出用户登录结果信息
			User user = (User) subject.getPrincipal();
			
			//4.将用户信息保存到session中
			session.put(SysConstant.CURRENT_USER_INFO, user);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			request.put("errorInfo", "登录失败，用户名或密码错误！");
			return "login";
		}
	}
	
	
	//退出
	public String logout(){
		session.remove(SysConstant.CURRENT_USER_INFO);		//删除session
		SecurityUtils.getSubject().logout(); //删除JESSIONID
		return "logout";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

