package com.dynamicDataSource.dynamicDataSource.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 2020/9/20 10:56
 * Modified By:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_sys_user")
public class SysUserDO {

    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机
     */
    private String phone;

    /**
     * 企业号UserId
     */
    @Column(name = "qy_user_id")
    private String qyUserId;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 登录次数
     */
    @Column(name = "login_count")
    private Integer loginCount;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 删除标识
     */
    @Column(name = "remove_flag")
    private Boolean removeFlag;

    /**
     * 创建人
     */
    @Column(name = "create_at")
    private Integer createAt;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改人
     */
    @Column(name = "update_at")
    private Integer updateAt;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;
}
