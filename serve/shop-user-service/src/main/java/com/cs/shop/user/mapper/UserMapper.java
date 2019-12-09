package com.cs.shop.user.mapper;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author cs
 * @create 2019-08-23-15:37
 */
public interface UserMapper extends Mapper<UmsMember>{
    List<UmsMember> selectAllUser();
}
