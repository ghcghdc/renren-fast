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

import io.renren.modules.school.entity.XxSchoolEntity;
import io.renren.modules.school.service.XxSchoolService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 *
 * @author wufusheng
 * @email ghcghdc@mail.com
 * @date 2020-04-14 09:44:22
 */
@RestController
@RequestMapping("school/xxschool")
public class XxSchoolController {
    @Autowired
    private XxSchoolService xxSchoolService;

    /**
     * 列表
     */
    @RequestMapping("/list") 
    @RequiresPermissions("school:xxschool:list")
    public R list(@RequestParam Map<String, Object> params){
    	System.out.println("打印-------------------------"+params.get("name"));
        PageUtils page = xxSchoolService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("school:xxschool:info")
    public R info(@PathVariable("id") Integer id){
		XxSchoolEntity xxSchool = xxSchoolService.getById(id);

        return R.ok().put("xxSchool", xxSchool);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("school:xxschool:save")
    public R save(@RequestBody XxSchoolEntity xxSchool){
		xxSchoolService.save(xxSchool);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("school:xxschool:update")
    public R update(@RequestBody XxSchoolEntity xxSchool){
		xxSchoolService.updateById(xxSchool);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("school:xxschool:delete")
    public R delete(@RequestBody Integer[] ids){
		xxSchoolService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
