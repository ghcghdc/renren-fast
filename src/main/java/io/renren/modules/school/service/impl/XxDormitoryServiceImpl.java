package io.renren.modules.school.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.school.dao.XxDormitoryDao;
import io.renren.modules.school.entity.XxDormitoryEntity;
import io.renren.modules.school.service.XxDormitoryService;


@Service("xxDormitoryService")
public class XxDormitoryServiceImpl extends ServiceImpl<XxDormitoryDao, XxDormitoryEntity> implements XxDormitoryService {
	
	  @Override
	  public PageUtils queryPage(Map<String, Object> params) {
	    	QueryWrapper<XxDormitoryEntity> queryWrapper = new QueryWrapper<XxDormitoryEntity>();
	    	if( params.get("key").toString() != "" ) {
	    		queryWrapper.like("name", params.get("key").toString());
	    	}
	        IPage<XxDormitoryEntity> page = this.page(
	                new Query<XxDormitoryEntity>().getPage(params), queryWrapper);

	        return new PageUtils(page);
	    }

}