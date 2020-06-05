package com.cccloud.blogs.entity.system;

import com.cccloud.blogs.config.feign.baidu.dto.IpLocationDto;
import com.cccloud.blogs.easybatis.anno.table.Id;
import com.cccloud.blogs.easybatis.anno.table.Table;
import com.cccloud.blogs.easybatis.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 作者：徐卫超
 * 时间：2020/6/4 8:38
 * 描述：
 */
@Data
@Table("t_access")
public class Access implements Serializable {

    /**
     * 记录ID
     **/
    @Id(type = IdType.AUTO)
    private Integer id;

    /**
     * IP地址
     **/
    private String ip;

    /**
     * 省份
     **/
    private String province;

    /**
     * 城市
     **/
    private String city;

    /**
     * 百度城市编码
     **/
    private String cityCode;

    public static Access convert(IpLocationDto location, String ip) {
        Access access = new Access();
        IpLocationDto.AddressDetailDto addressDetail = location.getAddressDetail();
        if (addressDetail != null) {
            access.setCity(addressDetail.getCity());
            access.setProvince(addressDetail.getProvince());
            access.setCityCode(addressDetail.getCityCode()+"");
        }
        access.setIp(ip);
        return access;
    }
}
