package io.renren.modules.school.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

import io.renren.common.utils.PageUtils;
import io.renren.modules.school.entity.XxStudentEntity;

/**
 *
 * @author wufusheng
 * @email ghcghdc@mail.com
 * @date 2020-04-14 09:44:22
 */
public interface XxStudentService extends IService<XxStudentEntity> {

    PageUtils queryPage(Map<String, Object> params);
    //查询最大的id
  	int findMaxId();
}

