package io.renren.modules.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.school.dao.XxDormitoryStudentDao;
import io.renren.modules.school.entity.XxDormitoryStudentEntity;
import io.renren.modules.school.service.XxDormitoryStudentService;

@Transactional
@Service("xxDormitoryStudentService")
public class XxDormitoryStudentServiceImpl extends ServiceImpl<XxDormitoryStudentDao, XxDormitoryStudentEntity> implements XxDormitoryStudentService {

	@Autowired
	private XxDormitoryStudentDao xxDormitoryStudentDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	
        IPage<XxDormitoryStudentEntity> page = this.page(
                new Query<XxDormitoryStudentEntity>().getPage(params),
                new QueryWrapper<XxDormitoryStudentEntity>());

        return new PageUtils(page);
    }

	//查询全部
	@Override
	public List<XxDormitoryStudentEntity> queryAll() {
		
		return xxDormitoryStudentDao.queryAll();
	}

	//根据学生id查宿舍id
	@Override
	public int findDidBySid(int sid) {
		
		return xxDormitoryStudentDao.findDidBySid(sid);
	}

	//根据学生id查id
	@Override
	public int findIdBySid(int sid) {
		
		return xxDormitoryStudentDao.findIdBySid(sid);
	}

}