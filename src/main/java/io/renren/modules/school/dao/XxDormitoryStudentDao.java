package io.renren.modules.school.dao;

import io.renren.modules.school.entity.XxDormitoryStudentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;

/**
 * ????ѧ???
 * 
 * @author wufusheng
 * @email ghcghdc@mail.com
 * @date 2020-04-14 09:44:21
 */
@Mapper
public interface XxDormitoryStudentDao extends BaseMapper<XxDormitoryStudentEntity> {
	
	//插入中间表数据，宿舍id，学校id
	void insertSAndD(XxDormitoryStudentEntity xxDormitoryStudentEntity);
	//查询全部数据
	List<XxDormitoryStudentEntity> queryAll();
}
