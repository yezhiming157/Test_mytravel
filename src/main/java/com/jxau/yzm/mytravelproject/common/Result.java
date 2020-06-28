package com.jxau.yzm.mytravelproject.common;

import java.io.Serializable;


/**
 * 将其作为对象返回值 携带信息返回给前台
 * @author yezhiming
 *2019年8月19日
 *@version
 */
public class Result implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static Result AjaxResult = null;
	
	private boolean success;
	private String message;
	private Object data;
    /**
     *  虽然spring对象是单例的，但类里面方法对每个线程来说都是独立运行的，不存在多线程问题，
     *  只有成员变量有多线程问题，所以方法里面如果有用到成员变量就要考虑用安全的数据结构
     * 	单例
     * 	高并发时不推荐使用，影响效率
     *  同步代码快加锁，安全高效
     * @return
     */
    public static Result getAjaxResult(){
        if(AjaxResult==null)
            synchronized (Result.class) {
                if(AjaxResult==null)
                	AjaxResult = new Result();
            }
        
        return AjaxResult;
        
    }

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result{" +
				"success=" + success +
				", message='" + message + '\'' +
				", data=" + data +
				'}';
	}
}
