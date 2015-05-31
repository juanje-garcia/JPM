package com.jp;

import org.junit.Assert;
import org.junit.Test;

import com.jp.scheduler.constants.Constants;
import com.jp.scheduler.model.impl.Scheduler;


public class ScheduleTest {

	
	/**
	 * Test with a single resource
	 * Request Order:   	 Message1(1), Message2(1), Message3(1), Message4(2), Message5(2), Message6(3), Message7(3), Message8(2), Message9(2), Message10(1)
	 * Expected Sent Order:  Message1(1), Message2(1), Message3(1), Message10(1), Message4(2), Message5(2), Message8(2), Message9(2), Message6(3), Message7(3)
	 */
	@Test
	public void testSingleResource() {		
		Scheduler scheduler  = new Scheduler(1,Constants.Algorithm_Default);
		TestUtils.executeScheduler(scheduler,TestUtils.getTenMessages());		
		org.junit.Assert.assertArrayEquals(scheduler.getMessagesSent().toArray(),TestUtils.getTestDefaultAlgorithmExpectedArray());
	}
	
	/**
	 * Test with 2 resources
	 * Messages:
	 * Request Order:   Message1(1), Message2(1), Message3(1), Message4(2), Message5(2), Message6(3), Message7(3), Message8(2), Message9(2), Message10(1)
	 * Expected: Two last sent messages should be of group 3
	 * 
	 */
	@Test
	public void testSeveralResources() {
		Scheduler scheduler  = new Scheduler(2,Constants.Algorithm_Default);
		TestUtils.executeScheduler(scheduler,TestUtils.getTenMessages());
		//The two las sent messages are Message 6 and 7 ( group 3)
		Assert.assertEquals(scheduler.getMessagesSent().get(9).getGroupId(),3);
		Assert.assertEquals(scheduler.getMessagesSent().get(8).getGroupId(),3);
		
	}
	
	/**
	 * Messages:
	 * Message1(1), Message2(1), Message3(1), Message4(2), Message5(2), Message6(3), Message7(3), Message8(2), Message9(2), Message10(1)
	 * Expected: Messages of group 2 should not be sent
	 */
	@Test
	public void testCancellation() {
		Scheduler scheduler  = new Scheduler(2,Constants.Algorithm_Default);
		TestUtils.executeSchedulerWithCancellation(scheduler, TestUtils.getTenMessages(), 2);	
		org.junit.Assert.assertEquals(Boolean.FALSE, scheduler.getMessagesSent().contains(TestUtils.getTenMessages()[3]));
		org.junit.Assert.assertEquals(Boolean.FALSE, scheduler.getMessagesSent().contains(TestUtils.getTenMessages()[4]));
		org.junit.Assert.assertEquals(Boolean.FALSE, scheduler.getMessagesSent().contains(TestUtils.getTenMessages()[7]));
		org.junit.Assert.assertEquals(Boolean.FALSE, scheduler.getMessagesSent().contains(TestUtils.getTenMessages()[8]));
	}
	
	/**
	 * Messages:
	 * Request Order:  Message1(1), Message2(1), Message3(1), Message4(1), Message5(1), Message6(2), Message7(3), Message8(4), Message9(5), Message10(6)
	 * Expected sent order: Message 1(1), Message 6(2), Message 7(3), Message 8(4), Message 9(5), Message 10(6), Message 2(1), Message 3(1), Message 4(1), Message 5(1) 
	 */
	@Test
	public void testAlternativeAlgorithm() {
		Scheduler scheduler  = new Scheduler(1,Constants.Algorithm_Alternative);
		TestUtils.executeScheduler(scheduler, TestUtils.getTenMessagesAlternative());	
		org.junit.Assert.assertArrayEquals(scheduler.getMessagesSent().toArray(),TestUtils.getTestAlternativeAlgorithmExpectedArray());


	}

	/**
	 * Test with 10 resources and 100 messages (10 messages processed per resource)
	 */
	@Test
	public void testStressResources() {
		Scheduler scheduler  = new Scheduler(10,Constants.Algorithm_Default);
		TestUtils.executeScheduler(scheduler,TestUtils.get_N_Messages(100));				
	}
	
}
