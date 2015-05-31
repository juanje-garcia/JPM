package com.jp.scheduler.model.impl;

import java.util.Random;

import com.jp.scheduler.constants.Constants;
import com.jp.scheduler.model.Message;


public class MessageImpl implements Message{
		
	private static Random random=new Random();
	private int groupId;
	private String msg;
	private Scheduler scheduler;
	
	public MessageImpl(String msg) {
		this(msg,random.nextInt(Constants.maxGroups));		
		//System.out.println(msg + " created with groupId = " + groupId);
	}

	public MessageImpl(String msg, int groupId) {
		this.msg=msg;
		this.groupId = groupId;
	}
	
	
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}



	public int getGroupId() {
		return groupId;
	}



	public String getMsg() {
		return msg;
	}



	@Override
	public void completed() {		
		this.scheduler.messageResponded(this);
	}

	@Override
	public String toString() {		
		return msg.toString()+"("+ groupId +")";
	}



	@Override
	public void sent() {
		this.scheduler.messageSent(this);
		
	}

	public int getRequestOrder() {
		return scheduler.getMessageRequestOrder(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.toString().equals(obj.toString());
	}

	
}
