package io.renren.modules.school.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.school.dao.XxDormitoryStudentDao;
import io.renren.modules.school.entity.XxDormitoryEntity;
import io.renren.modules.school.entity.XxDormitoryStudentEntity;
import io.renren.modules.school.service.XxDormitoryStudentService;


@Service("xxDormitoryStudentService")
public class XxDormitoryStudentServiceImpl extends ServiceImpl<XxDormitoryStudentDao, XxDormitoryStudentEntity> implements XxDormitoryStudentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	
        IPage<XxDormitoryStudentEntity> page = this.page(
                new Query<XxDormitoryStudentEntity>().getPage(params),
                new QueryWrapper<XxDormitoryStudentEntity>());

        return new PageUtils(page);
    }

}