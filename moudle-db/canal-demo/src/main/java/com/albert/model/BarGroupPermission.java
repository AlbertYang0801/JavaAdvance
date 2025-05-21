package com.albert.model;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author yangjunwei
 * @date 2025/3/14 14:08
 */
@Table(name = "bar_group_permission")
public class BarGroupPermission {

    @Column(name = "passport_id")
    private Integer passportId;

    @Column(name = "passport_name")
    private String passportName;

    @Column(name = "passport_type")
    private Integer passportType;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "group_name")
    private String groupName;




}
