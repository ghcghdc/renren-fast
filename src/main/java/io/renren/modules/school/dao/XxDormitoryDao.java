package io.renren.modules.school.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import io.renren.modules.school.entity.XxDormitoryEntity;

/**
 * 
 * @author wufusheng
 * @email ghcghdc@mail.com
 * @date 2020-04-14 09:44:21
 */
@Mapper
public interface XxDormitoryDao extends BaseMapper<XxDormitoryEntity> {
	
	int findCode(Integer code);
	int findIdByCode(Integer code);
	String findName(int id);
	List<XxDormitoryEntity> findNameAndIdBySId(int sid);
	int findSidById(int id);
}
