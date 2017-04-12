package com.zc.bp.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.zc.bp.dao.BaseDao;
import com.zc.bp.domain.Message;
import com.zc.bp.service.MessageService;
import com.zc.bp.utils.Page;




public class MessageServiceImpl implements MessageService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Message> find(String hql, Class<Message> entityClass, Object[] params) {
		return baseDao.find(hql, Message.class, params);
	}

	public Message get(Class<Message> entityClass, Serializable id) {
		return baseDao.get(Message.class, id);
	}

	public Page<Message> findPage(String hql, Page<Message> page, Class<Message> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, Message.class, params);
	}

	public void saveOrUpdate(Message entity) {
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<Message> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Message> entityClass, Serializable id) {
		baseDao.deleteById(Message.class, id);
	}

	public void delete(Class<Message> entityClass, Serializable[] ids) {
		baseDao.delete(Message.class, ids);
	}

}
