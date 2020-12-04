package com.cccloud.blogs.controller.user.menu;

import com.cccloud.blogs.entity.user.Menu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 创建人：徐卫超
 * 创建时间：2019/5/6  14:45
 * 业务：
 * 功能：
 */
@Data
public class MenuDto {

    @ApiModelProperty("菜单ID")
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 代码
     */
    private String code;

    /**
     * 父节点ID
     */
    private Long parentId;

    /**
     * 路由
     */
    private String path;

    public static MenuDto convert(Menu src) {
        MenuDto tar = new MenuDto();
        BeanUtils.copyProperties(src, tar);
        return tar;
    }


}
