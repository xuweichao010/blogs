package com.cccloud.blogs.commons;


import com.cccloud.blogs.commons.exceptions.BusinessException;
import io.swagger.annotations.ApiModelProperty;

/**
 * 创建人：徐卫超
 * 时间: 2018/5/29
 * 功能：JSON 响应标准体
 * 描述：
 */
@SuppressWarnings("unused")
public class JsonMessage<T> {

    /**
     * 成功
     */
    public final static int SUCCESS = 200;
    /**
     * 业务逻辑错误
     */
    private final static int FAILED = 400;

    /**
     * 访问受限（无权限）
     */
    public final static int NOT_PRIVILEGE = 403;

    /**
     * 未认证
     */
    public final static int NOT_AUTHEN = 401;

    /**
     * 代码异常
     */
    private final static int ERROR = 500;


    @ApiModelProperty("操作提示")
    private String msg;
    @ApiModelProperty("操作状态: 200-成功 | 400-业务失败 | 401-未认证 | 403-访问受限 | 500-服务器内部错误")
    private int code;
    @ApiModelProperty("返回数据")
    private T data;
    @ApiModelProperty("500的错误描述(开发使用)")
    private String description;


    /**
     * 处理成功
     */
    public static <T> JsonMessage<T> succeed(T data) {
        JsonMessage<T> json = new JsonMessage<>();
        json.setData(data).setCode(SUCCESS).setMsg("操作成功");
        return json;
    }

    /**
     * 处理成功
     */
    public static <Void> JsonMessage<Void> succeed() {
        JsonMessage<Void> json = new JsonMessage<>();
        json.setCode(SUCCESS).setMsg("操作成功");
        return json;
    }

    /**
     * 处理失败
     */
    public static <Void> JsonMessage<Void> failed(String msg) {
        JsonMessage<Void> json = new JsonMessage<>();
        json.setCode(FAILED).setMsg(msg);
        return json;
    }

    /**
     * 处理失败
     */
    public static <Void> JsonMessage<Void> failed(String msg, String description) {
        JsonMessage<Void> json = new JsonMessage<>();
        json.setCode(FAILED).setMsg(msg);
        json.setDescription(description);
        return json;
    }

    /**
     * 处理失败
     */
    public static <Void> JsonMessage<Void> failed(String msg, Integer code, String description) {
        JsonMessage<Void> json = new JsonMessage<>();
        json.setCode(code).setMsg(msg);
        json.setDescription(description);
        return json;
    }

    /**
     * 解析数据
     *
     * @return
     */
    public T dispose() {
        if (this.code == SUCCESS) {
            return this.getData();
        } else {
            throw new BusinessException(this.getMsg(), this.code);
        }
    }

    public String getDescription() {
        return description;
    }

    public JsonMessage<T> setDescription(String description) {
        this.description = description;
        return this;
    }


    public String getMsg() {
        return msg;
    }

    public JsonMessage<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public JsonMessage<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public JsonMessage<T> setData(T data) {
        this.data = data;
        return this;
    }
}
