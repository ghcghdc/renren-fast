package io.renren.modules.school.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.school.dao.XxSchoolDao;
import io.renren.modules.school.entity.XxSchoolEntity;
import io.renren.modules.school.service.XxSchoolService;


@Service("xxSchoolService")
public class XxSchoolServiceImpl extends ServiceImpl<XxSchoolDao, XxSchoolEntity> implements XxSchoolService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<XxSchoolEntity> page = this.page(
                new Query<XxSchoolEntity>().getPage(params),
                new QueryWrapper<XxSchoolEntity>()
        );

        return new PageUtils(page);
    }

}