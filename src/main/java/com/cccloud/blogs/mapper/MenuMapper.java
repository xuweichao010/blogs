package com.cccloud.blogs.mapper;

import com.cccloud.blogs.easybatis.interfaces.BaseMapper;
import com.cccloud.blogs.entity.user.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 作者：徐卫超
 * 时间：2020/6/9 15:44
 * 描述：
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu,Long> {

    @Select(" SELECT DISTINCT m.* FROM t_account_role ar " +
            " INNER JOIN t_role r ON ar.role_id = r.id " +
            " INNER JOIN t_role_menu rm ON r.id = rm.role_id " +
            " INNER JOIN t_menu m ON rm.menu_id = m.id WHERE ar.account_id = #{accountId}")
    List<Menu> listMenuByUser(String accountId);

}
