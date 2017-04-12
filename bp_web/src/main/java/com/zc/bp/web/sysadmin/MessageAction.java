package com.zc.bp.web.sysadmin;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.zc.bp.domain.Message;
import com.zc.bp.domain.User;
import com.zc.bp.service.MessageService;
import com.zc.bp.service.UserService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.SysConstant;
import com.zc.bp.web.BaseAction;

/**
 * @Description: MessageService接口
 * @Author: 杨小白
 * @CreateDate: 2016-12-17 17:53:52
 */

public class MessageAction extends BaseAction implements ModelDriven<Message> {
	// 注入service
	private MessageService messageService;

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	// 注入用户service
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	// model驱动
	private Message model = new Message();

	@Override
	public Message getModel() {
		return model;
	}

	// 作为属性驱动，接收并封装页面参数
	private Page page = new Page(); // 封装页面的参数，主要当前页参数

	public void setPage(Page page) {
		this.page = page;
	}

	public Page getPage() {
		return page;
	}

	// 列表展示
	public String list() {

		// 从session中获得当前用户
		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);

		String id = user.getId(); // 获得用户id

		String hql = "from Message where recipientId=?"; // 查询当前登录用户的所有留言板信息

		// 给页面提供分页数据
		messageService.findPage(hql, page, Message.class, new String[] { id });

		page.setUrl("messageAction_list"); // 相对定位

		super.put("page", page);

		// 查询所有用户名称
		List<User> userList = userService.find("from User", User.class, null);

		super.put("userList", userList);

		// 页面跳转
		return "plist";
	}

	// 转向新增页面
	public String tocreate() {
		// 查询数据库所有的用户
		List<User> userList = userService.find("from User", User.class, null);

		// 压栈
		super.put("userList", userList);

		return "pcreate";
	}

	// 新增保存
	public String insert() {
		// 获得当前登录的用户
		User obj = (User) session.get(SysConstant.CURRENT_USER_INFO);

		// 封装未创建的参数
		model.setSenderId(obj.getId()); //封装发送者ID
		model.setState(0d); // 状态为0
		model.setRecipientId(userId); // 属性驱动,获得接收人的id并设置

		messageService.saveOrUpdate(model);

		return "alist"; // 返回列表，重定向action_list
	}

	// 转向修改页面
	public String toreply() {
		// 跳转前查询当前勾选信息的发件人
		Message message = messageService.get(Message.class, model.getId());
		// 压栈
		String ms = message.getMessage();
		super.put("ms", ms);
		// 通过信息对象获得发送人id,查询用户名
		User user = userService.get(User.class, message.getSenderId());
		// 压栈
		super.push(user);

		return "toreply";
	}

	// 修改保存
	public String reply() {
		// 获得当前发送人
		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
		Message ms = new Message(); // 新建信息对象 用来储存信息的信息

		// 修改值
		ms.setMessage(model.getMessage());
		ms.setRecipientId(model.getId()); // 接收人的id
		ms.setSenderId(user.getId()); // 发送人的id,为登录者
		ms.setState(0d);
		ms.setTitle("留言回复");

		// 保存新回复
		messageService.saveOrUpdate(ms);

		return "alist";
	}

	// 删除一条
	public String deleteById() {
		messageService.deleteById(Message.class, model.getId());

		return "alist";
	}

	// 删除多条
	public String delete() {
		messageService.delete(Message.class, model.getId().split(", "));

		return "alist";
	}

	// 查看
	public String toview() {
		/*
		 * Integer msSize = (Integer) application.get("msSize");
		 * application.put("msSize", msSize-1);
		 */
		// 根据id查询实体
		Message obj = messageService.get(Message.class, model.getId());

		// 查出这条信息的发件人
		String senderId = obj.getSenderId();

		// 根据id查询该用户的姓名
		User user = userService.get(User.class, senderId);
		String name = user.getUserInfo().getName(); // 获得该用户的姓名

		// 修改成已读状态
		obj.setState(1d);
		messageService.saveOrUpdate(obj);

		// 压栈
		super.push(obj);
		super.put("name", name);

		return "pview";// 转向查看页面
	}

	
	

	// 属性驱动,用户创建部门时,下拉列表得到接收者的id
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
