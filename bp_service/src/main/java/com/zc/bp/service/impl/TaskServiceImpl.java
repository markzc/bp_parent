package com.zc.bp.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.zc.bp.dao.BaseDao;
import com.zc.bp.domain.Task;
import com.zc.bp.domain.User;
import com.zc.bp.service.TaskService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.UtilFuns;




public class TaskServiceImpl implements TaskService {
	
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	private SimpleMailMessage mailMessage ;
	private JavaMailSenderImpl mailSender;
	

	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public List<Task> find(String hql, Class<Task> entityClass, Object[] params) {
		// TODO Auto-generated method stub
	
		return 	baseDao.find(hql, entityClass, params);
	}

	@Override
	public Task get(Class<Task> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<Task> findPage(String hql, Page<Task> page, Class<Task> entityClass, Object[] params) {
		
		return 	baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(final Task entity)  {
		
		if(UtilFuns.isEmpty(entity.getId())){
			entity.setState(0);
			entity.setDefaultState(0);//0为先添加的 点击查看后改变为1
			 User sendUser = baseDao.get(User.class, entity.getCreateBy());
			 User toUser = baseDao.get(User.class, entity.getUser().getId());
		}else{
			if(UtilFuns.isEmpty(entity.getUpdateBy())){
				//证明是创建人修改的任务信息
				final User creatUser=baseDao.get(User.class, entity.getCreateBy());
			}else{
				//是接收人修改的任务信息
				final User updateUser=baseDao.get(User.class, entity.getUpdateBy());
			}
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Task> entitys) {
		// TODO Auto-generated method stub
		for (Task dept : entitys) {
			dept.setState(0);
		}
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<Task> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<Task> entityClass, Serializable[] ids) {
		// TODO Auto-generated method stub
		baseDao.delete(entityClass, ids);
	}

	@Override
	/**
	 * 寻找当前模块下的所有有权限的 且 等级比当前登录人小的 人员信息
	 */
	public List<User> findUser(Task model,String moduleId, User user) {
		// TODO Auto-generated method stub
		return baseDao.findUser(model, moduleId, user);
	}
	
}
