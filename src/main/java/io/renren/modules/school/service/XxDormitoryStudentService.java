package io.renren.modules.school.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.school.entity.XxDormitoryStudentEntity;

import java.util.Map;

/**
 * ????ѧ???
 *
 * @author wufusheng
 * @email ghcghdc@mail.com
 * @date 2020-04-14 09:44:21
 */
public interface XxDormitoryStudentService extends IService<XxDormitoryStudentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

