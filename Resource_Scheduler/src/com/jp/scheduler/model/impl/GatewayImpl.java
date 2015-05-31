package com.jp.scheduler.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.jp.scheduler.constants.Constants;
import com.jp.scheduler.model.Gateway;
import com.jp.scheduler.model.Message;

public class GatewayImpl implements Gateway{
	
	
	//Map that stores the order of groups processing <idGroup,n_order>
	Map<Integer,Integer> processingGroupsOrder = new HashMap<Integer,Integer>();
	
	//List of cancelled groups
	List<Integer> cancelledGroups = new ArrayList<Integer>();
	
	//Stores the order for the new incoming groups 
	private int currentOrderPosition=0; 
	
	private int lastGroupProcessed;
	

	public GatewayImpl() {
		
	}
	
	
	
	@Override
	public void send(Message msg) {
		lastGroupProcessed=msg.getGroupId();
		msg.sent();
		try {
			int secondsOfDuration = getExecutionDuration();
			//We mark the group as being processed
			groupProcessing(msg);
			// It simulates to send it to the external resource
			//The message gets completed after N seconds
			Thread.sleep(secondsOfDuration*1000);			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msg.completed();
	}


	/**
	 * Mark the group of the message as being processed (if not yet).
	 * @param msg Incoming message
	 */
	private void groupProcessing(Message msg) {
		if (!processingGroupsOrder.containsKey(msg)){
			processingGroupsOrder.put(msg.getGroupId(), currentOrderPosition);
			//Increment the group order position for the next group
			currentOrderPosition++;
		}
		
	}



	@Override
	public Integer processingOrder(Integer group) {		
		Integer order = processingGroupsOrder.get(group);
		if (order==null)
			order=Integer.MAX_VALUE;
		return order;
	}

	/**
	 * Create a random number between Constants.minProcessingDuration and Constants.maxProcessingDuration
	 * @return seconds of duration
	 */
	private int getExecutionDuration(){		
		return Constants.processingDuration;
	}



	@Override
	public void cancelGroup(Integer group) {
		// TODO Auto-generated method stub
		if (!cancelledGroups.contains(group))
			cancelledGroups.add(group);
	}



	@Override
	public boolean validate(Message msg) {
		if (cancelledGroups.contains(msg.getGroupId()))
				return false;
		return true;
	}



	@Override
	public int getLastGroup() {
		// TODO Auto-generated method stub
		return lastGroupProcessed;
	}
	

}
