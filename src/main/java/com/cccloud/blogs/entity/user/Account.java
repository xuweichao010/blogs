package com.cccloud.blogs.entity.user;


import com.cccloud.blogs.controller.user.account.AccountAddDto;
import com.cccloud.blogs.controller.user.account.AccountDto;
import com.cccloud.blogs.easybatis.anno.table.Id;
import com.cccloud.blogs.easybatis.anno.table.Table;
import com.cccloud.blogs.easybatis.enums.IdType;
import com.cccloud.blogs.entity.BaseEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * 账号信息
 */
@Data
@Table("t_account")
public class Account extends BaseEntity implements Serializable {
    /**
     * 用户ID
     **/
    @Id(type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     **/
    private String account;

    /**
     * 密码
     **/
    private String password;

    /**
     * 用户名
     **/
    private String name;

    /**
     * 邮箱
     **/
    private String email;

    /**
     *  用户状态 1-正常;2-禁用
     */
    private Integer state;

    /**
     * 性别 1-男性;2-女性
     **/
    private Integer gender;

    /**
     * AccountAddDto 转换成  Account
     *
     * @param src 数据源
     * @return
     */
    public static Account convert(AccountAddDto src) {
        Account tar = new Account();
        BeanUtils.copyProperties(src, tar);
        return tar;
    }

    /**
     * AccountDto 转换成  Account
     *
     * @param src 数据源
     * @return
     */
    public static Account convert(AccountDto src) {
        Account tar = new Account();
        BeanUtils.copyProperties(src, tar);
        return tar;
    }

    /**
     * AccountDto 复制到  Account
     *
     * @param src 数据源
     * @return
     */
    public void copy(Account src) {
        BeanUtils.copyProperties(src, this, "id", "account", "password", "state");
    }
}
