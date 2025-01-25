package com.albert.spring.entity.req;

import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 请求中的统一性信息
 * </p>
 *
 * @author pengbo
 * @since 2024-07-02
 */
@Data
public class BaseRequest implements Serializable {

    /**
     * 用户类型
     */
    //@NotNull(message = "passportType,不能为空")
    private Integer passportType;

    /**
     * 用户id
     */
    //@NotNull(message = "userId,不能为空")
    private Long userId;

    /**
     * 用户名称
     */
    //@NotNull(message = "userName,不能为空")
    private String userName;

    /**
     * 登录账号
     */
    //@NotNull(message = "userAccount,不能为空")
    private String userAccount;

    /**
     * 用户手机号
     */
    //@NotNull(message = "userPhone,不能为空")
    private String userPhone;

    /**
     * 组织id
     */
    @NotNull(message = "orgId,不能为空")
    private Integer orgId;

    /**
     * 组织名称
     */
    //@NotNull(message = "orgName,不能为空")
    private String orgName;

    /**
     * 角色id
     */
    //@NotNull(message = "roleId,不能为空")
    private String roleId;

    /**
     * 角色名称
     */
    //@NotNull(message = "roleName,不能为空")
    private String roleName;

    /**
     * 组织创建者标记
     * 1:组织创建者
     */
    private Integer managerFlag;

    /**
     * 组织用户id
     */
    private Long orgUserId;




}
