package com.lvmama.xcl.comm.jms;

import java.util.Date;


/**
 * 消息工厂
 *
 */
public class MessageFactory {
	/*public static Message newCertSmsSendMessage(Long orderId, String mobile) {
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), "");
		message.setAddition(mobile);
		return message;
	}

	public static Message newOrderCreateMessage(final Long orderId){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_CREATE_MSG.name());
		return message;
	}
	
	public static Message newOrderWaitPaymentTimeChangeMessage(final Long orderId){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_WAITPAYMENT_TIME_CHANGE_MSG.name());
		return message;
	}

	public static Message newOrderResourceStatusMessage(final Long orderId, final String resourceStatus) {
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_RESOURCE_MSG.name());
		message.setAddition(resourceStatus);
		return message;
	}

	public static Message newOrderInformationStatusMessage(final Long orderId, final String infoStatus) {
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_INFOPASS_MSG.name());
		message.setAddition(infoStatus);
		return message;
	}
	public static Message newSupplierOrderCancelMessage(final Long orderId, String addition){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.SUPPLIER_ORDER_CANCEL_APPLY_MSG.name());
		message.setAddition(addition);
		return message;
	}
	public static Message newOrderCancelMessage(final Long orderId, String addition){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_CANCEL_MSG.name());
		message.setAddition(addition);
		return message;
	}
	public static Message newOrderTwiceCancelMessage(final Long orderId, String addition){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_TWICE_CANCEL_MSG.name());
		message.setAddition(addition);
		return message;
	}

	public static Message newOrderPaymentMessage(final Long orderId, String addition){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_PAYMENT_MSG.name());
		message.setAddition(addition);
		return message;
	}
	
	public static Message newOrderDepositPaymentMessage(final Long orderId, String addition){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.STAMP_DEPOSIT_PAYMENT_MSG.name());
		message.setAddition(addition);
		return message;
	}
	
	public static Message newOrdSettlementPriceChangeMessage(final Long orderItemId, String addition){
		Message message = new Message(orderItemId, Constant.JMS_TYPE.ORD_ORDER_ITEM.name(), Constant.EVENT_TYPE.ORDER_MODIFY_SETTLEMENT_PRICE_MSG.name());
		message.setAddition(addition);
		return message;
	}

	public static Message newOrderModifyMessage(final Long orderId, String addition){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_MODIFY_MSG.name());
		message.setAddition(addition);
		return message;
	}
	
	public static Message newOrderModifyPersonMessage(final Long orderId, String addition){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_MODIFY_PERSON_MSG.name());
		message.setAddition(addition);
		return message;
	}
	
	public static Message newOrderModifyOrdChangePersonMessage(final Long orderId, String addition){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_CHANGE_PERSON_MSG.name());
		message.setAddition(addition);
		return message;
	}
	*//**
	 * 定金支付消息通知分销
	 * @param orderId
	 * @param addition
	 * @return
	 *//*
	public static Message newOrdOrderDownpayMessage(final Long orderId, String addition){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDED_PAYMENT_SETTING_MSG.name());
		message.setAddition(addition);
		return message;
	}
	
	
	public static Message newOrderRefundedSuccessMessage(final Long refundmentId){
		Message message = new Message(refundmentId, Constant.JMS_TYPE.ORD_REFUNDMENT.name(), Constant.EVENT_TYPE.ORDER_REFUNDED_MSG.name());
		return message;
	}
	public static Message newOrdItemPriceConfirmChangeMessage(final  Long ordItemId,String addition){
		Message message = new Message(ordItemId, Constant.JMS_TYPE.ORD_ORDER_ITEM.name(), Constant.EVENT_TYPE.ORDITEM_PRICE_STATUS_CHANGE_MSG.name());
		message.setAddition(addition);
		return message;
	}
    *//**
     * 生成通关码事件(销毁码，更新联系人等事件)
     * @param codeId
     * @return
     *//*
    public static Message newPasscodeEventMessage(Long codeId) {
        return new Message(codeId, "PASS_EVENT", Constant.EVENT_TYPE.PASSCODE_EVENT.name());
    }
    public static Message newPasscodeEventMessageNew(Long codeId, String operatorName) {
        Message message = new Message(codeId, "PASS_EVENT", Constant.EVENT_TYPE.PASSCODE_EVENT.name());
        message.setOperatorName(operatorName);
        return message;
    }

    public static Message newPasscodeApplyMessage(Long codeId) {
        return new Message(codeId, "PASS_CODE", Constant.EVENT_TYPE.PASSCODE_APPLY.name());
    }
    public static Message newPasscodeApplyMessageNew(Long codeId, String operatorName) {
        Message message = new Message(codeId, "PASS_CODE", Constant.EVENT_TYPE.PASSCODE_APPLY.name());
        message.setOperatorName(operatorName);
        return message;
    }
    
    public static Message newPasscodeApplyNotifyMessage(Long codeId) {
        return new Message(codeId, Constant.JMS_TYPE.ORD_ORDER_ITEM.name(), Constant.EVENT_TYPE.PASSCODE_APPLY_NOTIFY.name());
    }

    public static Message newPasscodeApplySuccessMessage(Long codeId,String addtion) {
      Message message=new Message(codeId, "PASS_CODE", Constant.EVENT_TYPE.PASSCODE_APPLY_SUCCESS.name());
      message.setAddition(addtion);
      return message;
    }
        
	public static Message newPasscodeDestroyMessage(Long codeId, String addtion) {
		Message message = new Message(codeId, "PASS_CODE",
				Constant.EVENT_TYPE.PASSCODE_DESTORY_EVENT.name());
		message.setAddition(addtion);
		return message;
	}

	public static Message newPassportUsedMessage(Long codeId, String addtion) {
		Message message = new Message(codeId, "PASS_CODE",
				Constant.EVENT_TYPE.PASSPORT_USED_EVENT.name());
		message.setAddition(addtion);
		return message;
	}

	public static Message newPasscodeApplyFailedMessage(Long codeId) {
		return new Message(codeId, "PASS_CODE",
				Constant.EVENT_TYPE.PASSCODE_APPLY_FAILED.name());
	}

	public static Message newEmailMessage(Long emailId) {
		return new Message(emailId, null,
				Constant.EVENT_TYPE.EMAIL_CREATE.name());
	}

	public static Message newOrderModifyMessage(final Long orderId,
			String addition, String eventType) {
		Message message = new Message(orderId,
				Constant.JMS_TYPE.ORD_ORDER.name(), eventType);
		message.setAddition(addition);
		return message;
	}
	
	
	
	*//**
	 * 人工发送凭证
	 * @param orderItemId
	 * @param addition
	 * @return
	 *//*
	public static Message newOrderMemoMessage(final Long orderItemId, String addition){
		Message message = new Message(orderItemId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_MEMO_UPDATE.name());
		message.setAddition(addition);
		return message;
	}
	
	*//**
	 * 人工发送结算单
	 * @param orderItemId
	 * @param addition
	 * @return
	 *//*
	public static Message newOrderItemSettleMessage(final Long orderItemId, String addition){
		Message message = new Message(orderItemId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_ITEM_SETTLE_MSG.name());
		message.setAddition(addition);
		return message;
	}
	
	*//**
	 * 履行成功
	 * @param orderItemId
	 * @param addition
	 * @return
	 *//*
	public static Message newOrderItemPerform(final Long orderItemId, String addition){
		Message message = new Message(orderItemId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_ITEM_PERFORM_MSG.name());
		message.setAddition(addition);
		return message;
	}

	*//**
	 * 门票子订单履行成功
	 * @param orderItemId
	 * @param addition
	 * @return
	 *//*
	public static Message newTicketOrderItemPerform(final Long orderItemId, String addition){
		Message message = new Message(orderItemId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_ITEM_PERFORM_TICKET_MSG.name());
		message.setAddition(addition);
		return message;
	}

	*//**
	 * 推送履行成功至结算
	 * @param orderItemId
	 * @param addition
	 * @return
	 *//*
	public static Message newItemPerformSettle(final Long orderItemId, String addition){
		Message message = new Message(orderItemId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ITEM_PERFROM_SETTLE_MSG.name());
		message.setAddition(addition);
		return message;
	}
	
	*//**
	 * 产品规格 商品是否有效和时间价格表修改操作
	 * @param orderId
	 * @param mobile
	 * @return
	 *//*
	public static Message newProductMessage(final Long objectId, String addition) {
		Message message = new Message(objectId, Constant.OBJECT_TYPE.PROD_PRODUCT.name(), Constant.EVENT_TYPE.PRODUCT_CANCLE_UPDATE.name());
		message.setAddition(addition);
		return message;
	}
	
	public static Message newProductBranchMessage(final Long objectId, String addition) {
		Message message = new Message(objectId, Constant.OBJECT_TYPE.PROD_PRODUCT_BRANCH.name(), Constant.EVENT_TYPE.PRODUCT_BRANCH_CANLCE_UPDATE.name());
		message.setAddition(addition);
		return message;
	}
	
	public static Message newSuppGoodsMessage(final Long objectId, String addition) {
		Message message = new Message(objectId, Constant.OBJECT_TYPE.SUPP_GOODS.name(), Constant.EVENT_TYPE.SUPP_GOODS_CANCLE_UPDATE.name());
		message.setAddition(addition);
		return message;
	}
	
	public static Message newSuppGoodsTimePriceMessage(final Long objectId, String addition) {
		Message message = new Message(objectId, Constant.OBJECT_TYPE.SUPP_GOODS_TIME_PRICE.name(), Constant.EVENT_TYPE.SUPP_GOODS_TIME_PRICE.name());
		message.setAddition(addition);
		return message;
	}
	
	*//**
	 * 发送分单日志记录消息
	 * @param codeId
	 * @param logTime 日志记录时间（精确到毫秒，排序用）
	 * @param addtion
	 * @return
	 *//*
	public static Message newAllocationLogMessage(Long codeId,Long createTime, String addition) {
		Message message = new Message(codeId, "VST_ALLOCATION",
				Constant.EVENT_TYPE.ALLOCATION_LOG_MSG.name());
		message.setAddition(addition);
		message.setCreateMilTime(createTime);// 用来存毫秒数
		return message;
	}
	
	*//**
	 * 异步分单消息
	 * @param objectId
	 * @return
	 *//*
	public static Message newOrderAllocationMessage(final Long objectId, String addition) {
		Message message = new Message(objectId, Constant.JMS_TYPE.ORD_ORDER.name(),
				Constant.EVENT_TYPE.ORDER_ALLOCATION__MSG.name());
		message.setAddition(addition);
		return message;
	}
	
	public static Message newCommonMessage(Long objectId, String objectType, String eventType, String addition) {
		Message message = new Message(objectId, objectType, eventType);
		message.setAddition(addition);
		
		return message;
	}

	
	public static Message newEbkSmsSendMessage(Long ebkCertifId,String addition) {
		Message message = new Message(ebkCertifId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.EBK_SMS_MSG.name());
		message.setAddition(addition);
		return message;
	}

    public static Message newSendResPreControlEmailMessage(Long policyId,String objectType,String addtion) {
        Message message=new Message(policyId, objectType, Constant.EVENT_TYPE.RES_SEND_EMAIL.name());
        message.setAddition(addtion);
        return message;
    }
    
    *//**
     * 退款申请创建的消息
     * @param orderId
     * @return
     *//*
    public static Message newOrderRefundApplyMessage(final Long orderId){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_REFUNDMENT.name(), Constant.EVENT_TYPE.REFUND_APPLY_MSG.name());
		return message;
	}
    *//**
     * 退款申请创建的消息
     * @param orderId
     * @return
     *//*
    public static Message newOrderOnlineRefundApplyMessage(final Long orderId){
    	Message message = new Message(orderId, Constant.JMS_TYPE.ORD_REFUNDMENT.name(), Constant.EVENT_TYPE.ORDER_ONLINE_REFUNDED_MSG.name());
    	return message;
    }
    
    *//**
     * 取消订单申请创建的消息
     * @param orderId
     * @return
     *//*
    public static Message newCancelOrderApplyMessage(final Long orderId){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_REFUNDMENT.name(), Constant.EVENT_TYPE.ORDER_CANCEL_APPLY_MSG.name());
		return message;
	}

	public static Message sendOrderPrice(Long orderId, String content) {
		Message message = new Message(orderId, "VST_ORDER_PRICE",Constant.EVENT_TYPE.ORDER_PRICE_MSG.name());
		message.setAddition(content);
		message.setCreateMilTime(new Date().getTime());// 用来存毫秒数
		return message;
	}
	//满房取消订单消息
	public static Message newOrderCancleOfCloseHouse(final Long orderId,String addition){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_CANCEL_CLOSEHOUSE_MSG.name());
		message.setAddition(addition);
		return message;
	}
	
	*//**
	 * 创建新订凭证消息
	 * @param orderItemId
	 * @return
	 *//*
	public static Message newOrderCertifCreateMessage(final Long orderItemId) {
		Message message = new Message(orderItemId, Constant.JMS_TYPE.ORD_ORDER_ITEM.name(), Constant.EVENT_TYPE.CERTIF_CREATE_MSG.name());
		return message;
	}
	
	
	*//**
     * o2o订单 修改游完日期(迪士尼改期)
     * @param orderId
     * @return
     *//*
    public static Message newVistTimeChangeMessage(final Long orderId, String addition){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_CHANGE_VISTTIME_MSG.name());
		return message;
	}
    
    *//**
     * o2o签证材料状态变更
     * @param orderId
     * @return
     *//*
    public static Message newVisaApprovalMessage(final Long orderId, String addition){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_VISA_APPROVAL_MSG.name());
		return message;
	}
    
    *//**
     * o2o 部分支付
     * @param orderId
     * @param addition
     * @return
     *//*
    public static Message newOrderPartPaymentMessage(final Long orderId, String addition){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_PART_PAYMENT_MSG.name());
		message.setAddition(addition);
		return message;
	}
    
    *//**
     * 门票过期退意向单
     * 
     * @param orderId Long
     * @param addition String
     * @return Message
     *//*
    public static Message newExpiredRefundMessage(final Long orderId, String addition){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_EXPIRED_REFUND_MSG.name());
		message.setAddition(addition);
		return message;
	}
    
    *//**
     * 
     * @Description: 排除意外险子单
     * @author Wangsizhi
     * @date 2016-12-21 下午7:52:02
     *//*
    public static Message sendInsAccOrderItemMessage(final Long orderId, String addition){
        Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.SEND_INSACC_MSG.name());
        message.setAddition(addition);
        return message;
    }
    
    *//**
     * 
     * @Description: 推送意外险子单 
     * @author Wangsizhi
     * @date 2016-12-21 下午7:59:00
     *//*
    public static Message excludedInsAccOrderItemMessage(final Long orderId, String addition){
        Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.EXCLUDED_INSACC_MSG.name());
        message.setAddition(addition);
        return message;
    }
    
    *//**
     * 
     * @Description: 意外险后置，过等待补充游玩人时间一半，提醒消息 
     * @author Wangsizhi
     * @date 2016-2-06 下午10:05:00
     *//*
    public static Message sendInsAccOrderDelayRemindMessage(final Long orderId, String addition){
        Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_ACCINS_REMIND_MSG.name());
        message.setAddition(addition);
        return message;
    }
    
    *//**
     * 
     * @Description: 意外险后置，过等待补充游玩人时间取消意外险 或 主动取消意外险，发送消息 
     * @author Wangsizhi
     * @date 2016-2-06 下午10:11:00
     *//*
    public static Message cancelInsAccDelayOrderItemMessage(final Long orderId, String addition){
        Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_CANCEL_DELAY_ACCINS_MSG.name());
        message.setAddition(addition);
        return message;
    }
    
    public static Message newOrderAuditReceiveTaskMessage(final Long orderId){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_AUDIT_RECEIVE_MSG.name());
		return message;
	}

    public static Message newOrderAscProcessMessage(final Long orderId){
		Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_ASC_PROCESS_MSG.name());
		return message;
	}
    
    *//**
     * 订单分摊完成后发送消息
     * @param orderId
     * @return
     *//*
    public static Message newOrderApportionSuccessMessage(final Long orderId){
    	Message message = new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_APPORTION_SUCCESS_MSG.name());
    	return message;
    }
    
    *//**
     * 订单明细拆分退款发送消息
     * @param orderId
     * @return
     *//*
    public static Message newOrderRefumentOkMessage(final Long refundmentId){
		Message message = new Message(refundmentId, Constant.JMS_TYPE.ORD_REFUNDMENT.name(), Constant.EVENT_TYPE.ORDER_REFUNDED_MSG_OK.name());
		return message;
	}
    
    public static Message newProdTagCommonMessage(String eventType, Long reId, String objectType, String objectContent) {
		
		Message message = new Message(reId, objectType, eventType);
		message.setAddition(objectContent);
		return message;
	}

	public static Message newOrderForDelWorkflowMessage(Long orderId) {
		return new Message(orderId, Constant.JMS_TYPE.ORD_ORDER.name(), Constant.EVENT_TYPE.ORDER_FOR_DEL_WORKFLOW.name());
	}
	
	public static Message newExpiredOrderItemRefundedMsgForEbk(Long orderItemId) {
		return new Message(orderItemId, Constant.JMS_TYPE.ORD_ORDER_ITEM.name(),
				Constant.EVENT_TYPE.EXPIRED_ORDER_ITEM_REFUNDED_MSG_FOR_EBK.name());
	}*/
	public static Message newXclMsgForBack(Long orderItemId,String objectType, String objectContent) {
		return new Message(orderItemId, objectType,
				objectContent);
	}
}
