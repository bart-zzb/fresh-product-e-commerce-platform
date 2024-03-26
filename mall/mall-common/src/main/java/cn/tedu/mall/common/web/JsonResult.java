package cn.tedu.mall.common.web;

import lombok.Data;

/**
 * VO:(View Object/Value Object),在当前项目中我们借助VO封装视图层要呈现数据
 */
@Data
public class JsonResult {
	/**消息表示状态码*/
	private Integer code;
	/**状态码对应的具体信息*/
	private String message;
	/**数据(基于此属性封装业务层返回的数据)*/
	private Object data;

	/**
	 * 服务于成功
	 */
	public JsonResult(){
		this.code = 200;
		this.message = "请求成功";
	}

	/**
	 * 服务于失败,自定义错误信息
	 * @param message
	 */
	public JsonResult(String message){
		this.code=500;
		this.message=message;

	}
	/**
	 * 服务于失败 异常信息
 	 */
	public JsonResult(Throwable e){
		this.code=500;
		this.message=e.getMessage();
	}

	/**
	 * 服务于成功,携带数据
	 * @param message
	 * @param data
	 */
	public JsonResult (String message,Object data){
		this.code=200;
		this.data = data ;
		this.message = message;
	}

	/**
	 * 服务于失败,自定义码
	 * @param code
	 * @param message
	 */
	public JsonResult(Integer code, String message){
		this.code=code;
		this.message =message;
	}



	public static JsonResult success(){
		return new JsonResult();
	}

	public static JsonResult success(Object o){
		return new JsonResult("请求成功",o);
	}

	public static JsonResult failed(String message){
		return new JsonResult(message);
	}

	public static JsonResult failed(Integer code,String message){
		return new JsonResult(code,message);
	}
}
