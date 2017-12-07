/**
 * 
 */
package com.lvmama.xcl.vo;

import java.io.Serializable;

/**
 * @author yangbin
 * 
 */
public class ResultHandleT<T extends Object> extends ResultHandle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5743682697348051374L;

	private T returnContent;

	public T getReturnContent() {
		return returnContent;
	}

	public void setReturnContent(T returnContent) {
		this.returnContent = returnContent;
	}

	/**
	 * 判断返回值是否为空
	 * @return
	 */
	public boolean hasNull(){
		return returnContent==null;
	}
}
