package org.Father.COMMON.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Father.COMMON.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.RestfulTool;

/*
 * 全局异常统一处理类
 * 模块编号：pcitc_wm_common_class_GlobalExceptionHandler
 * 作    者：pcitc
 * 创建时间：2017/10/11
 * 修改编号：1
 * 描    述：全局异常统一处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * 默认异常处理方法
	 *
	 * @author pcitc 2018-5-1
	 * @param request   请求Request
	 * @param response  请求Reponse
	 * @param exception 异常信息
	 * @throws Exception
	 */
	@ExceptionHandler(value = MyException.class)
	public void defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception exception)
			throws Exception {
		CommonResult commonResult = new CommonResult();
		// 判断异常信息类型
		if (exception.getClass().getTypeName() == "java.lang.NullPointerException") {
			commonResult.setMessage("对象为null");
		} else {
			commonResult.setMessage(exception.getMessage());
		}
		// 输出异常的堆栈信息
		exception.printStackTrace();
		// 构造错误返回值
		String collecionResult = RestfulTool.buildCollection(new ErrorInfo("", "00", commonResult.getMessage()),
				request.getRequestURI());

		// 使用response返回
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()); // 设置状态码
		response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
		response.setCharacterEncoding("UTF-8"); // 避免乱码
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.getWriter().write(collecionResult);
	}
}
