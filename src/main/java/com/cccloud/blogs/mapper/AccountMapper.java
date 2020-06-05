package com.cccloud.blogs.mapper;

import com.cccloud.blogs.easybatis.anno.SelectSql;
import com.cccloud.blogs.easybatis.interfaces.BaseMapper;
import com.cccloud.blogs.entity.user.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends BaseMapper<Account, Long> {

    @SelectSql(dynamic = true)
    Account findBy(String name, String account, String email);

}
