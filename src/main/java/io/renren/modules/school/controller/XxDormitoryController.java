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

import io.renren.modules.school.entity.XxDormitoryEntity;
import io.renren.modules.school.service.XxDormitoryService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * ?????
 *
 * @author wufusheng
 * @email ghcghdc@mail.com
 * @date 2020-04-14 09:44:21
 */
@RestController
@RequestMapping("school/xxdormitory")
public class XxDormitoryController {
    @Autowired
    private XxDormitoryService xxDormitoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("school:xxdormitory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = xxDormitoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("school:xxdormitory:info")
    public R info(@PathVariable("id") Integer id){
		XxDormitoryEntity xxDormitory = xxDormitoryService.getById(id);

        return R.ok().put("xxDormitory", xxDormitory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("school:xxdormitory:save")
    public R save(@RequestBody XxDormitoryEntity xxDormitory){
		xxDormitoryService.save(xxDormitory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("school:xxdormitory:update")
    public R update(@RequestBody XxDormitoryEntity xxDormitory){
		xxDormitoryService.updateById(xxDormitory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("school:xxdormitory:delete")
    public R delete(@RequestBody Integer[] ids){
		xxDormitoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
