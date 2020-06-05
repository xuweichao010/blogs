package com.cccloud.blogs.entity.user;


import com.cccloud.blogs.easybatis.anno.table.Id;
import com.cccloud.blogs.easybatis.anno.table.Table;
import com.cccloud.blogs.easybatis.enums.IdType;
import com.cccloud.blogs.entity.BaseEntity;
import lombok.Data;

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
    private  Integer  id;

    /**账号**/
    private  String  account;

    /**密码**/
    private  String  password;

    /**用户名**/
    private  String  name;

    /**邮箱**/
    private  String  email;

    /**性别 1-男性;2-女性**/
    private  Integer  gender;


}
