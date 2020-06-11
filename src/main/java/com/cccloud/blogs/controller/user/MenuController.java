package com.cccloud.blogs.controller.user;import com.cccloud.blogs.commons.JsonMessage;import com.cccloud.blogs.controller.user.menu.MenuDto;import com.cccloud.blogs.controller.user.menu.MenuTreeDto;import com.cccloud.blogs.entity.user.Menu;import com.cccloud.blogs.service.user.MenuService;import io.swagger.annotations.Api;import io.swagger.annotations.ApiOperation;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RestController;import java.util.List;import java.util.stream.Collectors;/** * 创建人：徐卫超 * 时间：2019/7/12 14:43 * 功能： * 备注： */@RestController@RequestMapping("/menu")@Api(tags = "USER_菜单管理")public class MenuController {    @Autowired    private MenuService menuService;    @GetMapping("/menu/{accountId}")    @ApiOperation("获取用户菜单列表")    public JsonMessage<List<MenuDto>> listBy(@PathVariable("accountId") String accountId) {        List<Menu> menus = menuService.listMenuByUser(accountId);        return JsonMessage.succeed(menus.stream().map(MenuDto::convert).collect(Collectors.toList()));    }    @GetMapping("/menu/tree/{accountId}")    @ApiOperation("获取用户菜单树")    public JsonMessage<MenuTreeDto> treeBy(@PathVariable("accountId") String accountId) {        List<Menu> menus = menuService.listMenuByUser(accountId);        MenuTreeDto menuDto = new MenuTreeDto();        menuDto.setId(0L);        return JsonMessage.succeed(convertTree(menuDto, menus));    }    private MenuTreeDto convertTree(MenuTreeDto root, List<Menu> childs) {        root.setChild(childs.stream().filter(menu -> menu.getType() == 1 && menu.getParentId().equals(root.getId())).map(MenuTreeDto::convert).collect(Collectors.toList()));        if (root.getChild().isEmpty()) {            root.setButtom(childs.stream().filter(menu -> menu.getType() == 2 && menu.getParentId().equals(root.getId())).map(Menu::getCode).collect(Collectors.toList()));        } else {            root.getChild().forEach(menu -> convertTree(menu, childs));        }        return root;    }}