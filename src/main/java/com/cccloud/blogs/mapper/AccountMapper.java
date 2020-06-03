package com.cccloud.blogs.mapper;

import com.cccloud.blogs.easybatis.anno.SelectSql;
import com.cccloud.blogs.easybatis.interfaces.BaseMapper;
import com.cccloud.blogs.entity.user.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper extends BaseMapper<Account,Integer> {

    @SelectSql
    List<Account> listby(String name);
}
