package io.renren.modules.school.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;

import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.oss.controller.SysOssController;
import io.renren.modules.school.entity.XxDormitoryStudentEntity;
import io.renren.modules.school.entity.XxStudentEntity;
import io.renren.modules.school.service.XxDormitoryService;
import io.renren.modules.school.service.XxDormitoryStudentService;
import io.renren.modules.school.service.XxSchoolService;
import io.renren.modules.school.service.XxStudentService;
import io.renren.modules.school.utils.ExcelUtils;


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
    @Autowired
    private XxDormitoryStudentService xxDormitoryStudentService;
    @Autowired
    private XxDormitoryService xxDormitoryService;
    @Autowired
    private XxSchoolService xxSchoolService;
    
    
    /*
     * 列表
     */
	@RequestMapping("/list")
    @RequiresPermissions("school:xxstudent:list")
    public R list(@RequestParam Map<String, Object> params){
    	PageUtils page = xxStudentService.queryPage(params);
    	 	//实例化page封装对象
    	XxStudentEntity sSE=new XxStudentEntity();
    		//创建名字存储集合
    	List<String> sName=new ArrayList<>();
    	List<String> dName=new ArrayList<>();
    		//查询中间表去全部数据
        List<XxDormitoryStudentEntity> x=xxDormitoryStudentService.queryAll();
        	//遍历出中间表数据
        for(XxDormitoryStudentEntity s:x) {
        	sName.add(xxSchoolService.FindName(s.getSid()));
        	dName.add(xxDormitoryService.FindName(s.getSid()));
        }
        	//遍历page中的list集合
        for(int i=0;i<page.getList().size();i++) {
        	//将list集合封装到对象中
        	sSE=(XxStudentEntity) page.getList().get(i);
        	//传入学校名称至对象中
        	sSE.setSchoolName(sName.get(i));
        	sSE.setDormitoryName((dName.get(i)));
        	
        }

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
/*
 * 还需要添加中间表数据保存
 */
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
		//同时删除中间表数据
		xxDormitoryStudentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
//    /**
//     * 学生编码校验
//     */
//    @RequestMapping("/findcode")
//    public R findcode(int code) {
//    	boolean codeOk=xxStudentService.findcode(code);
//    	//code=1的时候存在   返回false
//    	//code=0的时候不存在   返回true
//    	return R.ok().put("codeOk", codeOk);
//    }
    /**
     * 批量导入
     * @throws IOException 
     */
    @RequestMapping("/upfile")
    public R upFile(@RequestParam("file") MultipartFile file) throws Exception{
    	XxDormitoryStudentEntity xxDormitoryStudentEntity=new XxDormitoryStudentEntity();

        if (file.isEmpty()) {
        	throw new RRException("上传文件不能为空");
        }
      //使用工具类取出数据
        InputStream inputStream = file.getInputStream();
        List<List<Object>> list = ExcelUtils.parseExcel(inputStream,file.getOriginalFilename());
        inputStream.close();
        Date date = new Date();
        XxStudentEntity xxStudentEntity=new XxStudentEntity();
        int sCode=0;			//学校编码
        int dCode=0;			//宿舍编码
        boolean op=true;
    	
        /**
         * 解析下标
         * 学校编码	  宿舍编号 	 学生姓名	手机号	电子邮箱
         */
        for (int i = 0; i < list.size(); i++) {    
            List<Object> lo = list.get(i);
            xxStudentEntity.setName(lo.get(2).toString());
            xxStudentEntity.setMobile(lo.get(3).toString());
            xxStudentEntity.setEmail(lo.get(4).toString());
            xxStudentEntity.setCrtUser(10001);
            xxStudentEntity.setCrtTime(date);
            xxStudentEntity.setUpdUser(10001);
            xxStudentEntity.setUpdTime(date);
            sCode=Integer.parseInt(lo.get(0).toString());	//学校编码
            dCode=Integer.parseInt(lo.get(1).toString());	//宿舍编码
            //数据写入持久层
            xxStudentService.insertStudent(xxStudentEntity);
            
            xxDormitoryStudentEntity.setSid(xxSchoolService.findIdByCode(sCode));	
            xxDormitoryStudentEntity.setDid(xxDormitoryService.findIdByCode(dCode));
            //提交数据到中间表
            xxDormitoryStudentService.insertSAndD(xxDormitoryStudentEntity);
        }
        return R.ok().put("state", op);
    }
}
