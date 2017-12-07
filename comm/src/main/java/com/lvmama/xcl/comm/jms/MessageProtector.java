package com.lvmama.xcl.comm.jms;

import java.util.HashMap;
import java.util.Map;


/**
 * 向一个 Queue 组发送JMS，达到 Topic 效果
 * 对消息服务器可能存在的丢消息的保护，发送之前记录，接收到之后先设置收到。 另外一个检测线程当发现某条消息长时间（5分钟）未被设置为收到后
 *
 * @author Alex Wang
 */
public class MessageProtector {
	private static MessageProtector instance;
	private final Map<String, String[]> topicMap = new HashMap<String, String[]>();

	/**
	 * MAP XML. <virtualDestinations> <compositeTopic name="ActiveMQ.ORDER">
	 * <forwardTo> <queue physicalName="ActiveMQ.ORDER.back" /> <!--queue
	 * physicalName="ActiveMQ.ORDER.cash" /--> <queue
	 * physicalName="ActiveMQ.ORDER.order" /> <!--queue
	 * physicalName="ActiveMQ.ORDER.passport" /--> </forwardTo>
	 * </compositeTopic> <compositeTopic name="ActiveMQ.PASSPORT"> <forwardTo>
	 * <queue physicalName="ActiveMQ.PASSPORT.back" /> <queue
	 * physicalName="ActiveMQ.PASSPORT.order" /> <queue
	 * physicalName="ActiveMQ.PASSPORT.passport" /> </forwardTo>
	 * </compositeTopic> <compositeTopic name="ActiveMQ.PRODUCT"> <forwardTo>
	 * <queue physicalName="ActiveMQ.PRODUCT.back" /> <!--queue
	 * physicalName="ActiveMQ.PRODUCT.order" /--> </forwardTo> </compositeTopic>
	 * <compositeTopic name="ActiveMQ.POLICY"> <forwardTo> <queue
	 * physicalName="ActiveMQ.POLICY.back" /> <!--queue
	 * physicalName="ActiveMQ.POLICY.order" /--> </forwardTo> </compositeTopic>
	 * <!--compositeTopic name="ActiveMQ.SMS"> <forwardTo> <queue
	 * physicalName="ActiveMQ.PASSPORT.back" /> <queue
	 * physicalName="ActiveMQ.PASSPORT.order" /> </forwardTo>
	 * </compositeTopic--> <compositeTopic name="ActiveMQ.SSO"> <forwardTo>
	 * <queue physicalName="ActiveMQ.SSO.sso" /> <queue
	 * physicalName="ActiveMQ.SSO.clutter" /> </forwardTo> </compositeTopic>
	 * </virtualDestinations>
	 */
	private void init() {
//		String[] activeMqOrder = new String[] { "ActiveMQ.ORDER.back", "ActiveMQ.ORDER.order", "ActiveMQ.ORDER.payment", "ActiveMQ.ORDER.clutter", "ActiveMQ.ORDER.passport" };
//		topicMap.put("ActiveMQ.ORDER", activeMqOrder);
//
//		String[] activeMqPassport = new String[] { "ActiveMQ.PASSPORT.back", "ActiveMQ.PASSPORT.order", "ActiveMQ.PASSPORT.passport", "ActiveMQ.PASSPORT.ebk_push" };
//		topicMap.put("ActiveMQ.PASSPORT", activeMqPassport);
//
//		String[] activeMqPassportChimelong = new String[] { "ActiveMQ.PASSPORT.CHIMELONG.passport" };
//		topicMap.put("ActiveMQ.PASSPORT.CHIMELONG", activeMqPassportChimelong);
//
//		String[] activeMqProduct = new String[] { "ActiveMQ.PRODUCT.back", "ActiveMQ.PRODUCT.clutter" };
//		topicMap.put("ActiveMQ.PRODUCT", activeMqProduct);
//
//		String[] activeMqPolicy = new String[] { "ActiveMQ.POLICY.back" };
//		topicMap.put("ActiveMQ.POLICY", activeMqPolicy);
//
//		String[] activeMqSso = new String[] { "ActiveMQ.SSO.sso" };
//		topicMap.put("ActiveMQ.SSO", activeMqSso);
//
//		String[] activeMqResource = new String[] { "ActiveMQ.RESOURCE.job", "ActiveMQ.RESOURCE.payment", "ActiveMQ.RESOURCE.search1", "ActiveMQ.RESOURCE.search2" };
//		topicMap.put("ActiveMQ.RESOURCE", activeMqResource);

		String[] activeMqVstOrder = new String[] {"ActiveMQ.VST_ORDER.prom","ActiveMQ.VST_ORDER.back","ActiveMQ.VST_ORDER.order","ActiveMQ.VST_ORDER.passport","ActiveMQ.VST_ORDER.tntMessage","ActiveMQ.VST_ORDER.visa","ActiveMQ.VST_ORDER.o2o"};
		topicMap.put("ActiveMQ.VST_ORDER", activeMqVstOrder);

		//增加搜索的消息
		String[] activeMqVstSearch = new String[] {"ActiveMQ.RESOURCE.vstSearch1","ActiveMQ.RESOURCE.vstSearch2","ActiveMQ.RESOURCE.vstSearch3","ActiveMQ.RESOURCE.vstSearch4","ActiveMQ.RESOURCE.vstSearch5","ActiveMQ.RESOURCE.vstSearch6"};
		topicMap.put("ActiveMQ.RESOURCE", activeMqVstSearch);

        String[] activeMqCodePassport = new String[] {"ActiveMQ.VST_CODE_PASSPORT.passport"};
        topicMap.put("ActiveMQ.VST_CODE_PASSPORT", activeMqCodePassport);

        String[] activeMqCodeForJobPassport = new String[] {"ActiveMQ.VST_CODE_FOR_JOB_PASSPORT.passport"};
        topicMap.put("ActiveMQ.VST_CODE_FOR_JOB_PASSPORT", activeMqCodeForJobPassport);
        
        String[] activeMqSmsPassport = new String[] {"ActiveMQ.VST_SMS_PASSPORT.passport","ActiveMQ.VST_SMS_PASSPORT.tntMessage"};
        topicMap.put("ActiveMQ.VST_SMS_PASSPORT", activeMqSmsPassport);
        
        String[] activeMqEbkSms = new String[] {"ActiveMQ.VST_EBK_SMS.back"};
        topicMap.put("ActiveMQ.VST_EBK_SMS", activeMqEbkSms);
        
        // 分单
        String[] activeMqVstAllocation = new String[] {"ActiveMQ.VST_ALLOCATION.allocation","ActiveMQ.VST_ALLOCATION.order"};
        topicMap.put("ActiveMQ.VST_ALLOCATION", activeMqVstAllocation);

        //预控
        String[] activeMqVstResPreControl = new String[] {"ActiveMQ.VST_CONTROL.back"};
        topicMap.put("ActiveMQ.VST_CONTROL", activeMqVstResPreControl);
       //订单发送分销
        String[] activeMqOrderPrice=new String[]{"ActiveMQ.VST_ORDER_PRICE.order","ActiveMQ.VST_ORDER_PRICE.tntMessage","ActiveMQ.VST_ORDER_PRICE.o2o"};
        topicMap.put("ActiveMQ.VST_ORDER_PRICE", activeMqOrderPrice);

        String[] o2oProductPrice = new String[]{"ActiveMQ.O2O_CAL_PRICE.back"};
        topicMap.put("ActiveMQ.O2O_CAL_PRICE", o2oProductPrice);
        
        //目的地bu酒店消息 add by ltwangwei
        String[] activeMqdestBuHotel = new String[] {"ActiveMQ.VST_DEST_HOTEL.dest-hotel-back"};
        topicMap.put("ActiveMQ.VST_DEST_HOTEL", activeMqdestBuHotel);
/*        //增加产品操作消息
        String[] activeMqSmsProduct = new String[] {"ActiveMQ.VST_PRODUCT.back"};
        topicMap.put("ActiveMQ.VST_PRODUCT", activeMqSmsProduct);
*/
		//sync_trigger
		String[] activeMqSyncTrigger = new String[]{"ActiveMQ.SYNC_TRIGGER.back"};
		topicMap.put("ActiveMQ.SYNC_TRIGGER", activeMqSyncTrigger);
		
		String[] activeMqOrderWorkflow = new String[]{"ActiveMQ.VST_ORDER_WORKFLOW.order"};
		topicMap.put("ActiveMQ.VST_ORDER_WORKFLOW", activeMqOrderWorkflow);
		
		String[] activeMqProdTagToDestMsg = new String[]{"ActiveMQ.PRODUCT_VST.DEST_MESSAGE.lvmm_dest_back"};
		topicMap.put("ActiveMQ.PRODUCT_VST.DEST_MESSAGE.lvmm_dest_back", activeMqProdTagToDestMsg);
		
		String[] activeMqOrderForDelWorkflowMsg = new String[]{"ActiveMQ.ORDER_FOR_DEL_WORKFLOW_MESSAGE.vst_workflow"};
		topicMap.put("ActiveMQ.ORDER_FOR_DEL_WORKFLOW_MESSAGE.vst_workflow", activeMqOrderForDelWorkflowMsg);
		
		
		String[] activeMqXclMsg = new String[]{"ActiveMQ.XCL.back"};
		topicMap.put("ActiveMQ.XCL", activeMqXclMsg);
	}

	public static MessageProtector getInstance() {
		if (instance == null) {
			MessageProtector protector = new MessageProtector();
			protector.init();
			instance = protector;
		}
		return instance;
	}

	public String[] getQueues(String topic) {
		return topicMap.get(topic);
	}

}
