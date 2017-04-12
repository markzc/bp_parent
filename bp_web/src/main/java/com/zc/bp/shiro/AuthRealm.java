package com.zc.bp.shiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.zc.bp.domain.Module;
import com.zc.bp.domain.Role;
import com.zc.bp.domain.User;
import com.zc.bp.service.UserService;
import com.zc.bp.utils.SysConstant;

public class AuthRealm extends AuthorizingRealm {
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	//授权
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		//1.得到用户信息
		User user = (User) arg0.fromRealm(SysConstant.SALT).iterator().next();
		
		//2.通过对象导航,得到用户的角色列表
		Set<Role> roles = user.getRoles();
		
		List<String> permissions = new ArrayList<String>();
		//3.遍历角色列表，得到用户的每个角色
		for(Role role :roles){
			//得到每个角色 ，并通过对象导航，进一步加载这个角色下的模块列表
			Set<Module> modules = role.getModules();
			
			//遍历模块的集合，得到每个模块信息
			for(Module module :modules){
				permissions.add(module.getName());
			}
		}
		
		//声明AuthorizationInfo的一个子类对象
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissions);
		
		return info;
	}

	@Override
	//认证
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		//1.将token转化为子类对象
		UsernamePasswordToken upToken = (UsernamePasswordToken) arg0;
		//2.从token中获取用户在界面输入的用户名
		String username = upToken.getUsername();
		//3.调用业务逻辑，根据用户名查询用户对象
		List<User> userList = userService.find("from User where username=?" , User.class, new String[]{username});
		
		if(userList!=null && userList.size()>0){
			//查询到了用户对象，说明用户名是正确的
			User user = userList.get(0);
			
			//principal 代表用户信息             credentials 代表用户的密码              第三个参数：只要是一个字符串就可以
			AuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),SysConstant.SALT);
			return info;
		}
		//4.组织返回的结果
		return null;
	}

}
