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

import io.renren.modules.school.entity.XxStudentEntity;
import io.renren.modules.school.service.XxStudentService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * ѧ???
 *
 * @author wufusheng
 * @email ghcghdc@mail.com
 * @date 2020-04-14 09:44:22
 */
@RestController
@RequestMapping("school/xxstudent")
public class XxStudentController {
    @Autowired
    private XxStudentService xxStudentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("school:xxstudent:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = xxStudentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("school:xxstudent:info")
    public R info(@PathVariable("id") Integer id){
		XxStudentEntity xxStudent = xxStudentService.getById(id);

        return R.ok().put("xxStudent", xxStudent);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("school:xxstudent:save")
    public R save(@RequestBody XxStudentEntity xxStudent){
		xxStudentService.save(xxStudent);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("school:xxstudent:update")
    public R update(@RequestBody XxStudentEntity xxStudent){
		xxStudentService.updateById(xxStudent);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("school:xxstudent:delete")
    public R delete(@RequestBody Integer[] ids){
		xxStudentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
