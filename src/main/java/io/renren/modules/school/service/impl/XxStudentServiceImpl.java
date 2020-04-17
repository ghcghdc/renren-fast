package io.renren.modules.school.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.school.dao.XxStudentDao;
import io.renren.modules.school.entity.XxStudentEntity;
import io.renren.modules.school.service.XxStudentService;

@Transactional
@Service("xxStudentService")
public class XxStudentServiceImpl extends ServiceImpl<XxStudentDao, XxStudentEntity> implements XxStudentService {

	@Autowired
	private XxStudentDao xxStudentDao;
	  @Override
	  public PageUtils queryPage(Map<String, Object> params) {
	    	QueryWrapper<XxStudentEntity> queryWrapper = new QueryWrapper<XxStudentEntity>();
	    	if( params.get("key").toString() != "" ) {
	    		queryWrapper.like("name", params.get("key").toString());
	    	}
	        IPage<XxStudentEntity> page = this.page(new Query<XxStudentEntity>().getPage(params), queryWrapper);
	        return new PageUtils(page);
	    }
	  public boolean findcode(Integer code) {
	    	int codeOk=xxStudentDao.findCode(code);
	    	if(codeOk==1) {
	    		return false;
	    	}else {
	    		return true;
	    	}

	    }
	  //批量导入数据
	public void insertStudent(XxStudentEntity xxStudentEntity) {
		xxStudentDao.insert(xxStudentEntity);
		xxStudentDao.autoId();
	}
	//多表联查
	@Override
	public List<XxStudentEntity> findAll() {
		
		return xxStudentDao.findAll();
	}

}