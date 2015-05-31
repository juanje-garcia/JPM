package com.jp.scheduler.algorithms;

import com.jp.scheduler.model.Gateway;
import com.jp.scheduler.model.impl.ScheduleTask;

/**
 * Default algorithm priorized messages of groups that has started to process
 * @author Juan Jesus
 *
 */
public class DefaultAlgorithm extends ScheduleAlgorithm {

	public DefaultAlgorithm(Gateway gateway) {
		super(gateway);
	}

//	@Override
//	public int compare(ScheduleTask task1, ScheduleTask task2) {
//		Integer processingOrderGroup1=gateway.processingOrder(task1.getMessage().getGroupId());
//		Integer processingOrderGroup2=gateway.processingOrder(task2.getMessage().getGroupId());
//		
//		if (processingOrderGroup1<processingOrderGroup2)
//			return -1;
//		else if (processingOrderGroup1>processingOrderGroup2)
//			return 1;
//		else{
//			Integer lastGroup = gateway.getLastGroup();
//			Integer group1 = task1.getMessage().getGroupId();
//			Integer group2 = task2.getMessage().getGroupId();
//			if (group1.equals(lastGroup) && !group2.equals(lastGroup)){
//				return -1;
//			}else if (!group1.equals(lastGroup) && group2.equals(lastGroup)){
//				return 1;
//			}else{
//				Integer order1 = task1.getMessage().getRequestOrder();
//				Integer order2 = task2.getMessage().getRequestOrder();
//				if (order1<order2){
//					return -1;
//				}else{
//					return 1;
//				}
//			}
//		}
//	}

	@Override
	public int compare(ScheduleTask task1, ScheduleTask task2) {
		Integer processingOrderGroup1=gateway.processingOrder(task1.getMessage().getGroupId());
		Integer processingOrderGroup2=gateway.processingOrder(task2.getMessage().getGroupId());
		
		if (processingOrderGroup1<processingOrderGroup2)
			return -1;
		else if (processingOrderGroup1>processingOrderGroup2)
			return 1;
		else{
			Integer lastGroup = gateway.getLastGroup();
			Integer group1 = task1.getMessage().getGroupId();
			Integer group2 = task2.getMessage().getGroupId();
			if (group1.equals(lastGroup) && !group2.equals(lastGroup)){
				return -1;
			}else if (!group1.equals(lastGroup) && group2.equals(lastGroup)){
				return 1;
			}else{
				Integer order1 = task1.getMessage().getRequestOrder();
				Integer order2 = task2.getMessage().getRequestOrder();
				if (order1<order2){
					return -1;
				}else{
					return 1;
				}
			}
		}
	}
}
