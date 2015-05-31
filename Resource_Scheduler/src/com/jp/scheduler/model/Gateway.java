package com.jp.scheduler.model;

public interface Gateway {
	/**
	 * Sends a message
	 */
	public void send(Message msg);
	/**
	 * Gets the processing order of a group
	 */
	public Integer processingOrder(Integer group);
	
	/**
	 * Allows to cancel group messages of being processed
	 * @param group
	 */
	public void cancelGroup(Integer group);
	
	/**
	 * Validates message before send
	 * @param group
	 * @return
	 */
	public boolean validate(Message msg);
	
	/**
	 * Last group processed
	 */
	public int getLastGroup();
	
}
