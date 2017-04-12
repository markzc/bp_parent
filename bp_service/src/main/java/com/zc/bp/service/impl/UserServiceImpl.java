package com.zc.bp.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.zc.bp.dao.BaseDao;
import com.zc.bp.domain.User;
import com.zc.bp.service.UserService;
import com.zc.bp.utils.Encrypt;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.SysConstant;
import com.zc.bp.utils.UtilFuns;

public class UserServiceImpl implements UserService{
	
	private SimpleMailMessage mailMessage;
	private JavaMailSender mailSender;
	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<User> find(String hql, Class<User> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public User get(Class<User> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<User> findPage(String hql, Page<User> page, Class<User> entityClass, Object[] params) {
		baseDao.findPage(hql, page, entityClass, params);
		return page;
	}

	@Override
	public void saveOrUpdate(final User entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			//判断id是否有值
			String id = UUID.randomUUID().toString();
			entity.setId(id);
			entity.getUserInfo().setId(id);
			//说明id没有值，说明是保存
			entity.setState(1);//1代表可用
			entity.setPassword(Encrypt.md5(SysConstant.DEFAULT_PASS,SysConstant.SALT));
			
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						mailMessage.setTo(entity.getUserInfo().getEmail());
						mailMessage.setSubject("新员工入职信息");
						mailMessage.setText("欢迎"+entity.getUserInfo().getName()+"加入xxx,您在公司的账号："+entity.getUsername()+",密码："+SysConstant.DEFAULT_PASS);
						
						mailSender.send(mailMessage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			thread.start();
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<User> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	/**
	 * 
	 */
	public void deleteById(Class<User> entityClass, Serializable id) {
		
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<User> entityClass, Serializable[] ids) {
		baseDao.delete(entityClass, ids);
	}

}
