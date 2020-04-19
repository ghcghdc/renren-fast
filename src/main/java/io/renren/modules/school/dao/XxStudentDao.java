package io.renren.modules.school.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import io.renren.modules.school.entity.XxStudentEntity;

/**
 * ѧ???
 * 
 * @author wufusheng
 * @email ghcghdc@mail.com
 * @date 2020-04-14 09:44:22
 */
@Mapper
public interface XxStudentDao extends BaseMapper<XxStudentEntity> {
	
	int findCode(Integer code);
	//查询最大的id
	int findMaxId();
}
