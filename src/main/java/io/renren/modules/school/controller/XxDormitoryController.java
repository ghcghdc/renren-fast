package io.renren.modules.school.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.school.entity.XxDormitoryEntity;
import io.renren.modules.school.entity.XxSchoolEntity;
import io.renren.modules.school.entity.XxStudentEntity;
import io.renren.modules.school.service.XxDormitoryService;
import io.renren.modules.school.service.XxSchoolService;



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
    @Autowired
    private XxSchoolService xxSchoolService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("school:xxdormitory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = xxDormitoryService.queryPage(params);
        
        //实例化page封装对象
        XxDormitoryEntity xDE=new XxDormitoryEntity();
        //遍历page中的list集合
        for(int i=0;i<page.getList().size();i++) {
        	
        	//将list集合封装到对象中
        	xDE=(XxDormitoryEntity) page.getList().get(i);
        	//根据sid查询学校名字
        	xDE.setSchoolName(xxSchoolService.FindName(xDE.getSid()));
        }

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
    /**
     * 宿舍编码校验
     */
    @RequestMapping("/findcode")
    public R findcode(int code) {
    	boolean codeOk=xxDormitoryService.findcode(code);
    	//code=1的时候存在   返回false
    	//code=0的时候不存在   返回true
    	return R.ok().put("codeOk", codeOk);
    }
    /**
     * 级联下拉选择学校
     */
    @RequestMapping("/findSchoolName")
    public R findSchoolName() {
    	List<XxSchoolEntity>schoolList=xxSchoolService.findNameAndID();
        
    	return R.ok().put("schoolList", schoolList);
    }
}
