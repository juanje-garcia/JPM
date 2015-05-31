package com.jp.scheduler.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.jp.scheduler.algorithms.DefaultAlgorithm;
import com.jp.scheduler.algorithms.ScheduleAlgorithm;
import com.jp.scheduler.constants.Constants;
import com.jp.scheduler.model.Gateway;
import com.jp.scheduler.model.Message;

public class Scheduler {
	
	private ThreadPoolExecutor executor;
	private Gateway gateway = new GatewayImpl();
	private ScheduleAlgorithm criteria;
	
	private List<Message> messagesCompleted=new ArrayList<Message>();
	private List<Message> messagesSent=new ArrayList<Message>();
	//Regiter the request message Order
	private Map<Message,Integer> requestMessagesOrder=new HashMap<Message,Integer>();
	
	private int count=0;
	
	
	//PriorityQueue for incoming messages
	final PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<>(Constants.queueMaxSize);
	
	public Scheduler(int size,String algorithmImpl) {			
		this.criteria=getAlgorithmImpl(algorithmImpl);
		executor=new ThreadPoolExecutor(size, size, Constants.minutesQueued, TimeUnit.MINUTES, queue);
	}	
	
	public Scheduler(int size) {
		this(size,Constants.Algorithm_Default);
	}	
	
	public void process(Message message){
		synchronized(this){
			requestMessagesOrder.put(message, count);
			count++;
		}
		message.setScheduler(this);		
		executor.execute(new ScheduleTask(gateway,message,criteria));		
	}
	
	
	
	/**
	 * Allows to cancel messages of one group 
	 * @param groupId
	 */
	public void cancelGroup(int groupId){
		gateway.cancelGroup(groupId);
	}
	
	
	/**
	 * Instances an object of the selected algorithm implementation class
	 * @param  implementation class
	 * @return
	 */
	private ScheduleAlgorithm getAlgorithmImpl(String criteriaClass) {
		// TODO Auto-generated method stub
		Class clazz=null;
		ScheduleAlgorithm criteriaImpl;
		try {
			clazz=Class.forName(criteriaClass);
			criteriaImpl=(ScheduleAlgorithm) clazz.getDeclaredConstructor(Gateway.class).newInstance(gateway);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			criteriaImpl=new DefaultAlgorithm(gateway);
		}
		return criteriaImpl;
	}
	
	public void messageResponded(Message msg){
		messagesCompleted.add(msg);
		//System.out.println("Message " + msg + " responded");
	}
	
	public void messageSent(Message msg){
		messagesSent.add(msg);
		//System.out.println("Message " + msg + " sent");
	}
	
	public long getCompletedCount(){
		return executor.getCompletedTaskCount();
	}

	public List<Message> getMessagesCompleted() {
		return messagesCompleted;
	}

	public List<Message> getMessagesSent() {
		return messagesSent;
	}
	
	public Integer getMessageRequestOrder(Message message){
		return requestMessagesOrder.get(message);
	}

}
