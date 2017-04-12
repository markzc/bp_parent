package com.zc.bp.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import com.zc.bp.utils.Encrypt;
import com.zc.bp.utils.SysConstant;

public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		UsernamePasswordToken upToken =(UsernamePasswordToken) token;
		String inputPwd = new String(upToken.getPassword());
		String inputPwdEntity = Encrypt.md5(inputPwd, SysConstant.SALT);
		
		String dbPwd = info.getCredentials().toString();
		return super.equals(inputPwdEntity, dbPwd);
	}
}
