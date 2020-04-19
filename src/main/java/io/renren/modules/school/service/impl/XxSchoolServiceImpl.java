package io.renren.modules.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.school.dao.XxSchoolDao;
import io.renren.modules.school.entity.XxSchoolEntity;
import io.renren.modules.school.service.XxSchoolService;

@Transactional
@Service("xxSchoolService")
public class XxSchoolServiceImpl extends ServiceImpl<XxSchoolDao, XxSchoolEntity> implements XxSchoolService {

	@Autowired
	private XxSchoolDao xxSchoolDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	QueryWrapper<XxSchoolEntity> queryWrapper = new QueryWrapper<XxSchoolEntity>();
    	if( params.get("name").toString() != "" ) {
    		queryWrapper.like("name", params.get("name").toString());
    	}
        IPage<XxSchoolEntity> page = this.page(
                new Query<XxSchoolEntity>().getPage(params), queryWrapper);

        return new PageUtils(page);
    }
    public boolean findcode(Integer code) {
    	int codeOk=xxSchoolDao.findCode(code);
    	if(codeOk==1) {
    		return false;
    	}else {
    		return true;
    	}

    }
    /*
     * 根据code查id
     */
	@Override
	public int findIdByCode(Integer code) {
		return xxSchoolDao.findIdByCode(code);
	}
	/*
	 * 根据code查名字
	 */
	@Override
	public String FindName(int sid) {
		return xxSchoolDao.findName(sid);
	}
	/**
	 * 查询学校名字和id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<XxSchoolEntity> findNameAndID() {
		
		return (List<XxSchoolEntity>) xxSchoolDao.findNameAndID();
	}

}