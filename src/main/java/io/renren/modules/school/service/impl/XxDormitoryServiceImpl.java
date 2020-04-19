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
import io.renren.modules.school.dao.XxDormitoryDao;
import io.renren.modules.school.entity.XxDormitoryEntity;
import io.renren.modules.school.service.XxDormitoryService;

@Transactional
@Service("xxDormitoryService")
public class XxDormitoryServiceImpl extends ServiceImpl<XxDormitoryDao, XxDormitoryEntity> implements XxDormitoryService {
	
	@Autowired
	private XxDormitoryDao dormitorDao;
	
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
	  
	  public boolean findcode(Integer code) {
	    	int codeOk=dormitorDao.findCode(code);
	    	if(codeOk==1) {
	    		return false;
	    	}else {
	    		return true;
	    	}

	    }

	  /**
	   * 根据code查id
	   */
	@Override
	public int findIdByCode(Integer code) {
		return dormitorDao.findIdByCode(code);
	}

	/**
	 * 根据code查name
	 */
	@Override
	public String FindName(int sid) {
		
		return dormitorDao.findName(sid);
	}

	/**
	 * 根据sid查name
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<XxDormitoryEntity> findNameAndIdBySId(int sid) {
		
		return (List<XxDormitoryEntity>) dormitorDao.findNameAndIdBySId(sid);
	}

	@Override
	public int findSidById(int id) {
		
		return dormitorDao.findSidById(id);
	}

}