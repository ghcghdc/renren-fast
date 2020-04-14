package io.renren.modules.school.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.school.entity.XxDormitoryStudentEntity;
import io.renren.modules.school.service.XxDormitoryStudentService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * ????ѧ???
 *
 * @author wufusheng
 * @email ghcghdc@mail.com
 * @date 2020-04-14 09:44:21
 */
@RestController
@RequestMapping("school/xxdormitorystudent")
public class XxDormitoryStudentController {
    @Autowired
    private XxDormitoryStudentService xxDormitoryStudentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("school:xxdormitorystudent:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = xxDormitoryStudentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("school:xxdormitorystudent:info")
    public R info(@PathVariable("id") Integer id){
		XxDormitoryStudentEntity xxDormitoryStudent = xxDormitoryStudentService.getById(id);

        return R.ok().put("xxDormitoryStudent", xxDormitoryStudent);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("school:xxdormitorystudent:save")
    public R save(@RequestBody XxDormitoryStudentEntity xxDormitoryStudent){
		xxDormitoryStudentService.save(xxDormitoryStudent);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("school:xxdormitorystudent:update")
    public R update(@RequestBody XxDormitoryStudentEntity xxDormitoryStudent){
		xxDormitoryStudentService.updateById(xxDormitoryStudent);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("school:xxdormitorystudent:delete")
    public R delete(@RequestBody Integer[] ids){
		xxDormitoryStudentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
