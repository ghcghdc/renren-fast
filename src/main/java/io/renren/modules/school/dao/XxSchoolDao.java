package io.renren.modules.school.dao;

import io.renren.modules.school.entity.XxSchoolEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * ѧУ?
 * 
 * @author wufusheng
 * @email ghcghdc@mail.com
 * @date 2020-04-14 09:44:22
 */
@Mapper
public interface XxSchoolDao extends BaseMapper<XxSchoolEntity> {
	
	int findCode(Integer code);
	
	int findIdByCode(Integer code);
	
	String findName(int sid);
	
	List<XxSchoolEntity> findNameAndID();
}
