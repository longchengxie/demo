package com.lvmama.xcl.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResultMessage implements Serializable {

	/**
	 * 序列
	 */
	private static final long serialVersionUID = -7563387945976477789L;

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";
	
	public static final String SYS_ERROR = "sysError";

	public static final ResultMessage ADD_SUCCESS_RESULT = new ResultMessage(SUCCESS, "新增成功");
	public static final ResultMessage ADD_FAIL_RESULT = new ResultMessage(ERROR, "新增失败");
	
	public static final ResultMessage UPDATE_CATEGORY_ERROR_RESULT = new ResultMessage(ERROR, "改品类已存在");
	public static final ResultMessage REPEAT_NAME_ERROR_RESULT = new ResultMessage(ERROR, "存在重复名称");

	public static final ResultMessage UPDATE_SUCCESS_RESULT = new ResultMessage(SUCCESS, "修改成功");
	public static final ResultMessage UPDATE_FAIL_RESULT = new ResultMessage(ERROR, "修改失败");

	public static final ResultMessage SET_SUCCESS_RESULT = new ResultMessage(SUCCESS, "设置成功");
	public static final ResultMessage SET_FAIL_RESULT_NOPARM = new ResultMessage(ERROR, "取消失败，无参数传入");
	public static final ResultMessage SET_FAIL_RESULT_NORELATION = new ResultMessage(ERROR, "取消失败,该产品无关联关系存在");
	public static final ResultMessage SET_FAIL_RESULT = new ResultMessage(ERROR, "设置失败");
	public static final ResultMessage CHECK_SUCCESS_RESULT = new ResultMessage(SUCCESS, "审核通过");
	public static final ResultMessage CHECK_FAIL_RESULT = new ResultMessage(SUCCESS, "审核失败");
	public static final ResultMessage REJECT_SUCCESS_RESULT = new ResultMessage(SUCCESS, "驳回成功");
	public static final ResultMessage REJECT_FAIL_RESULT = new ResultMessage(SUCCESS, "驳回失败");
	
	public static final ResultMessage DELETE_SUCCESS_RESULT = new ResultMessage(SUCCESS, "删除成功");
	public static final ResultMessage DELETE_FAIL_RESULT = new ResultMessage(ERROR, "删除失败");
	
	public static final ResultMessage SMS_SEND_SUCCESS_RESULT = new ResultMessage(SUCCESS, "短信发送成功");
	public static final ResultMessage SMS_SEND_FAIL_RESULT = new ResultMessage(ERROR, "短信发送失败");

	public static final ResultMessage ID_UNEXITE_RESULT = new ResultMessage(ERROR, "编号ID不存在");
	public static final ResultMessage ID_HAVED_DATA_RESULT = new ResultMessage(ERROR, "该编号ID下已经存在该友情链接地址");
	
	public static final ResultMessage JOB_START_SUCCESS = new ResultMessage(SUCCESS, "JOB启动成功");
	public static final ResultMessage JOB_START_FAIL = new ResultMessage(ERROR, "JOB启动失败");
	public static final ResultMessage JOB_PARAM_ERROR = new ResultMessage(ERROR, "JOB启动参数错误,请检查该JOB对应的参数值！");
	public static final ResultMessage JOB_STOP_SUCCESS = new ResultMessage(SUCCESS, "JOB关闭成功");
	public static final ResultMessage JOB_STOP_FAIL = new ResultMessage(ERROR, "JOB关闭失败");

	public static final ResultMessage ID_EXITE_RESULT = new ResultMessage(ERROR, "编号ID已存在");

	public static final ResultMessage GROSS_EXITE_RESULT = new ResultMessage(ERROR, "品类，所属BU，所属公司 唯一性冲突");

    public static final ResultMessage FIND_BUDGET_SUCCESS_RESULT = new ResultMessage(SUCCESS, "查询成功");
    public static final ResultMessage FIND_BUDGET_FAIL_RESULT = new ResultMessage(ERROR, "该商品不是买断商品或预控周期不在游玩时间内");
    public static final ResultMessage SET_FAIL_RELATED_RESULT = new ResultMessage(ERROR, "该附加商品被引用，不能设为无效");
    public static final ResultMessage SET_EXP_RELATED_RESULT = new ResultMessage(ERROR, "设置有效失败，原因：关联的酒店商品现状态为无效，请先设置酒店有效");
    
    public static final ResultMessage SET_SHOW_FIRST_DUP = new ResultMessage(SUCCESS, "同一个产品只能设置一个优先显示的酒店");
	
	private String code;

	private String message;

	private Map<String, Object> attributes;

	public ResultMessage(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public ResultMessage(Map<String, Object> attributes, String code, String message) {
		this.code = code;
		this.message = message;
		this.attributes = attributes;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return SUCCESS.equalsIgnoreCase(code);
	}

	@Override
	public String toString() {
		return "ResultMessage [code=" + code + ", message=" + message + "]";
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public void addObject(String key, Object value) {
		if (attributes == null) {
			attributes = new HashMap<String, Object>();
		}
		attributes.put(key, value);
	}

	/**
	 * 调用当前方法把状态改为异常
	 * 
	 * @param msg
	 */
	public void raise(String msg) {
		code = ERROR;
		message = msg;
	}

	public void raise(ResultHandle handle) {
		if (handle.isFail()) {
			raise(handle.getMsg());
		}
	}
	
	public static ResultMessage createResultMessage(){
		ResultMessage msg = new ResultMessage(SUCCESS, "操作成功");
		return msg;
	}
}
