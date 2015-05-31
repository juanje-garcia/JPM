package com.jp.scheduler.model;

import com.jp.scheduler.model.impl.Scheduler;

public interface Message {
	
	
	/**
	 * Notifies completed event
	 */
	public void completed();
	
	/**
	 * @return message group
	 */
	public int getGroupId();
	
	/**
	 * Sets a reference to the scheduler that is processing the message
	 * @param scheduler
	 */
	public void setScheduler(Scheduler scheduler);
	
	/**
	 * Notifies the sent event
	 */
	public void sent();
	
	/**
	 * 
	 * @return message request order in its scheduler
	 */
	public int getRequestOrder();
}
