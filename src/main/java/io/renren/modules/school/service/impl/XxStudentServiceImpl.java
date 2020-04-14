package io.renren.modules.school.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.school.dao.XxStudentDao;
import io.renren.modules.school.entity.XxStudentEntity;
import io.renren.modules.school.service.XxStudentService;


@Service("xxStudentService")
public class XxStudentServiceImpl extends ServiceImpl<XxStudentDao, XxStudentEntity> implements XxStudentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<XxStudentEntity> page = this.page(
                new Query<XxStudentEntity>().getPage(params),
                new QueryWrapper<XxStudentEntity>()
        );

        return new PageUtils(page);
    }

}