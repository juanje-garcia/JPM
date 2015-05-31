package com.jp.scheduler.model.impl;

import com.jp.scheduler.algorithms.ScheduleAlgorithm;
import com.jp.scheduler.model.Gateway;
import com.jp.scheduler.model.Message;

/**
 * 
 * @author Juan Jesus Garcia
 * 
 * This class implements the request process to the gateway with the criteria 
 *
 */
public class ScheduleTask extends Thread implements Comparable<ScheduleTask>{

	private Message message;	
	private Gateway gateway;
	private ScheduleAlgorithm algorithm;
	
	
	public ScheduleTask(Gateway gateway,Message message,ScheduleAlgorithm algorithmImpl) {
		super();
		this.gateway=gateway;
		this.message = message;	
		this.algorithm=algorithmImpl;
	}
	
	
	


	public Message getMessage() {
		return message;
	}




	public Gateway getGateway() {
		return gateway;
	}




	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();		
		if (gateway.validate(message)){
			//System.out.println("Sending message "+  message +  " to gateway");
			gateway.send(message);
		}else{
			System.err.println(message + " will not be sent because its group has been cancelled");
		}
		
	}
	

	
	

	@Override
	public int compareTo(ScheduleTask o) {
		return algorithm.compare(this, o);		
	}
	
}
