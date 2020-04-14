package io.renren.modules.school.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
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
        IPage<XxDormitoryEntity> page = this.page(
                new Query<XxDormitoryEntity>().getPage(params),
                new QueryWrapper<XxDormitoryEntity>()
        );

        return new PageUtils(page);
    }

}