package io.renren.modules.school.dao;

import io.renren.modules.school.entity.XxDormitoryStudentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * ????ѧ???
 * 
 * @author wufusheng
 * @email ghcghdc@mail.com
 * @date 2020-04-14 09:44:21
 */
@Mapper
public interface XxDormitoryStudentDao extends BaseMapper<XxDormitoryStudentEntity> {
	
	//查询全部数据
	List<XxDormitoryStudentEntity> queryAll();
	
	//根据学生id查宿舍id
	int findDidBySid(int sid);
	
	// 根据学生id查id
	int findIdBySid(int sid);
}
