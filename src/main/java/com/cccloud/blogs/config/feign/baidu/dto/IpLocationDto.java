package com.cccloud.blogs.config.feign.baidu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 作者：徐卫超
 * 时间：2020/6/3 20:14
 * 描述：
 */
@Data
@ApiModel(description = "IP位置解析信息")
public class IpLocationDto {
    @ApiModelProperty("简要地址信息")
    private String address;
    @ApiModelProperty("结构化地址信息")
    private AddressDetailDto addressDetail;
    @ApiModelProperty("当前城市中心点")
    private PointDto point;


    @Data
    public class AddressDetailDto {
        @ApiModelProperty("城市")
        private String city;
        @ApiModelProperty("百度城市代码")
        private Integer cityCode;
        @ApiModelProperty("省份")
        private String province;
    }

    @Data
    public class PointDto {
        @ApiModelProperty("当前城市中心点经度")
        String x;
        @ApiModelProperty("当前城市中心点纬度")
        String y;
    }
}

/**
 * 正常相应
 * {
 * address: "CN|北京|北京|None|CHINANET|1|None",    #详细地址信息
 * content:    #结构信息
 * {
 * address: "北京市",    #简要地址信息
 * address_detail:    #结构化地址信息
 * {
 * city: "北京市",    #城市
 * city_code: 131,    #百度城市代码
 * province: "北京市",    #省份
 * },
 * point:    #当前城市中心点
 * {
 * x: "116.39564504",    #当前城市中心点经度
 * y: "39.92998578"    #当前城市中心点纬度
 * }
 * },
 * status: 0    #结果状态返回码
 * }
 */
