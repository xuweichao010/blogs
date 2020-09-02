package com.cccloud.blogs.open.baidu.commmons;

import com.cccloud.blogs.commons.exceptions.SystemException;
import lombok.Data;

/**
 * 作者：徐卫超
 * 时间：2020/6/3 20:11
 * 描述：
 */
@Data
public class BaiduJsonMessage<T> {
    private static final int SUCCESS = 0;
    Integer status;
    T content;
    String address;

    /**
     * 解析数据
     *
     * @return
     */
    public T dispose() {
        if (this.status == SUCCESS) {
            return this.getContent();
        } else {
            throw new SystemException("百度服务请求错误:" + this.status);
        }
    }
}
