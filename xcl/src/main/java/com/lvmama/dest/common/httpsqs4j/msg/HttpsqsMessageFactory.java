package com.lvmama.dest.common.httpsqs4j.msg;

/** 
 * @Title: HttpsqsMessageFactory.java 
 * @Package com.lvmama.com.lvtu.common.httpsqs4j.msg 
 * @Description: TODO 
 * @author dengcheng 
 * @date 2015-5-12 下午6:25:25 
 * @version V1.0.0 
 */
public class HttpsqsMessageFactory {

	public static enum EVENT_TYPE {
		DEST_EVENT,
		DEST_LOG_EVENT,
		DEST_EXCEPTION_LOG_EVENT,
		DEST_SUPP_HOTE_ORDER_RETRY
	}
	
	public static enum OBJECT_TYPE {
		DEST_BU_LOG,
		HOTEL_ORDER
	}
	
	public static HttpsqsMessage createDestLogicLogMsg(String tag,String msg){
		
		HttpsqsMessage message = new HttpsqsMessage(null, OBJECT_TYPE.DEST_BU_LOG.name(), EVENT_TYPE.DEST_LOG_EVENT.name(),tag, msg);
		return message;		
	}
	
	public static HttpsqsMessage createExceptionLogMsg(String msg){
		
		HttpsqsMessage message = new HttpsqsMessage(null, OBJECT_TYPE.DEST_BU_LOG.name(), EVENT_TYPE.DEST_EXCEPTION_LOG_EVENT.name(),"Exception", msg);
		return message;		
	}
	
	/**
	 * 创建一个订单补偿消息
	 * @param msg
	 * @return
	 */
	public static HttpsqsMessage createCompensateOrderMsg(String msg){
		HttpsqsMessage message = new HttpsqsMessage(null, OBJECT_TYPE.HOTEL_ORDER.name(), EVENT_TYPE.DEST_SUPP_HOTE_ORDER_RETRY.name(),"ORDER_COMPENSATE", msg);
		return message;		
	}
	
}
