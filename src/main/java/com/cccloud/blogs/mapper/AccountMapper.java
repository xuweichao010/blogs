package com.cccloud.blogs.mapper;

import com.cccloud.blogs.easybatis.anno.SelectSql;
import com.cccloud.blogs.easybatis.anno.condition.Count;
import com.cccloud.blogs.easybatis.interfaces.BaseMapper;
import com.cccloud.blogs.entity.user.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper extends BaseMapper<Account, Integer> {

    @SelectSql(dynamic = true)
    List<Account> listby(String name, String account, String email);

    @SelectSql(dynamic = true)
    Account findBy(String name, String account, String email);

    @SelectSql(dynamic = true)
    @Count
    int countBy(String name, String account, String email);
}
