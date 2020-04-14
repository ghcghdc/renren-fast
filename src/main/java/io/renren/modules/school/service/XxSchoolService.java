package io.renren.modules.school.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.school.entity.XxSchoolEntity;

import java.util.Map;

/**
 * ѧУ?
 *
 * @author wufusheng
 * @email ghcghdc@mail.com
 * @date 2020-04-14 09:44:22
 */
public interface XxSchoolService extends IService<XxSchoolEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
}

