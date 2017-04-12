package com.zc.bp.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.zc.bp.domain.Message;
import com.zc.bp.utils.Page;



public interface MessageService {

	public List<Message> find(String hql, Class<Message> entityClass, Object[] params);
	public Message get(Class<Message> entityClass, Serializable id);
	public Page<Message> findPage(String hql, Page<Message> page, Class<Message> entityClass, Object[] params);
	
	public void saveOrUpdate(Message entity);
	public void saveOrUpdateAll(Collection<Message> entitys);
	
	public void deleteById(Class<Message> entityClass, Serializable id);
	public void delete(Class<Message> entityClass, Serializable[] ids);
}
