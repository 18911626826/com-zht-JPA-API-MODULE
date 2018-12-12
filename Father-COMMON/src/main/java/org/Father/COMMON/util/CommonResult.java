package org.Father.COMMON.util;

/*
 * 返回结果信息类
 * 模块编号：pcitc_wm_common_class_CommonResult
 * 作       者：pcitc
 * 创建时间：2017/09/10
 * 修改编号：1
 * 描       述：返回结果信息类
 */
public class CommonResult {

    /**
     * 返回编码
     */
    private Object WmResultId;

    /**
     * 返回的结果
     */
    private Object Result;

    /**
     * 是否执行成功
     */
    private Boolean IsSuccess;

    /**
     * 结果描述
     */
    private String Message;

    /**
     * 资源文件码
     */
    private String ResourceCode;

    /**
     * 构造函数
     */
    public CommonResult() {
        setIsSuccess(true);
    }

    public Object getWmResultId() {
        return WmResultId;
    }

    public void setWmResultId(Object dISCResultId) {
        WmResultId = dISCResultId;
    }

    public Object getResult() {
        return Result;
    }

    public void setResult(Object result) {
        Result = result;
    }

    public Boolean getIsSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        IsSuccess = isSuccess;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getResourceCode() {
        return ResourceCode;
    }

    public void setResourceCode(String resourceCode) {
        ResourceCode = resourceCode;
    }

}
