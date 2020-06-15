package com.cccloud.blogs.controller.user.menu;

import com.cccloud.blogs.entity.user.Menu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 作者：徐卫超
 * 时间：2020/6/9 15:41
 * 描述：
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuTreeDto extends MenuDto {
    @ApiModelProperty("子菜单信息")
    private List<MenuTreeDto> child;

    @ApiModelProperty("菜单按钮信息")
    private List<String> buttom;

    public static MenuTreeDto convert(Menu src) {
        MenuTreeDto tar = new MenuTreeDto();
        BeanUtils.copyProperties(src, tar);
        return tar;
    }
}
