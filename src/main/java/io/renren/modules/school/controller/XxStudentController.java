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

import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.school.entity.XxDormitoryEntity;
import io.renren.modules.school.entity.XxDormitoryStudentEntity;
import io.renren.modules.school.entity.XxStudentEntity;
import io.renren.modules.school.service.XxDormitoryService;
import io.renren.modules.school.service.XxDormitoryStudentService;
import io.renren.modules.school.service.XxSchoolService;
import io.renren.modules.school.service.XxStudentService;
import io.renren.modules.school.utils.ExcelUtils;


/**
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
    
    //获取当前时间
    private Date date = new Date();
    
    /**
     * 列表
     */
	@RequestMapping("/list")
    @RequiresPermissions("school:xxstudent:list")
    public R list(@RequestParam Map<String, Object> params){
    	PageUtils page = xxStudentService.queryPage(params);
    	 	//实例化page封装对象
    	XxStudentEntity xxStudentEntity=new XxStudentEntity();
    		//创建名字存储集合
    	List<String> sNameList=new ArrayList<>();	//学校名称集合
    	List<String> dNameList=new ArrayList<>();	//宿舍名称集合
    		//查询中间表去全部数据
        List<XxDormitoryStudentEntity> x=xxDormitoryStudentService.queryAll();
        	//遍历出中间表数据
        for(XxDormitoryStudentEntity s:x) {
        	sNameList.add(xxSchoolService.FindName(xxDormitoryService.findSidById(s.getDid())));//根据宿舍id查sid
        	dNameList.add(xxDormitoryService.FindName(s.getDid()));				//根据宿舍id查询宿舍名称
        }
        	//遍历page中的list集合
        for(int i=0;i<page.getList().size();i++) {
        	//将list集合封装到page对象中
        	xxStudentEntity=(XxStudentEntity) page.getList().get(i);
        	//传入学校名称至对象中
        	xxStudentEntity.setSchoolName(sNameList.get(i));
        	//传入宿舍名称至对象中
        	xxStudentEntity.setDormitoryName((dNameList.get(i)));
        	
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
    	
    	/**
    	 * 根据学生id查询中间表数据
    	 */
    	int did=0;
    	int sid=0;
    	//根据学生id查出宿舍id(查询中间表)
    	did = xxDormitoryStudentService.findDidBySid(id);  
    	//根据宿舍id查询学校id(查询宿舍表)
    	sid = xxDormitoryService.findSidById(did);
    	xxStudent.setSid(sid);	
    	xxStudent.setDid(did);
        return R.ok().put("xxStudent", xxStudent);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("school:xxstudent:save")
    public R save(@RequestBody XxStudentEntity xxStudent){
    	
        //设置创建人和创建时间
        xxStudent.setCrtUser(10001);
        xxStudent.setCrtTime(date);
    	xxStudentService.save(xxStudent);
		/**
		 * 中间表数据保存
		 */
		XxDormitoryStudentEntity xxDormitoryStudentEntity = new XxDormitoryStudentEntity();
		int sid=0;
		sid=xxStudentService.findMaxId();
		System.out.println("最后增加的学生id："+sid);
        xxDormitoryStudentEntity.setSid(sid);		//获取学生id
        xxDormitoryStudentEntity.setDid(xxStudent.getDid());	//获取宿舍id
        
        // 提交数据到持久层
        xxDormitoryStudentService.save(xxDormitoryStudentEntity);
		
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("school:xxstudent:update")
    public R update(@RequestBody XxStudentEntity xxStudent){
    	
    	xxStudentService.updateById(xxStudent);
		/**
		 * 中间表数据传入数据
		 */
    	int id=0;
    	XxDormitoryStudentEntity xxDormitoryStudentEntity=new XxDormitoryStudentEntity();
    	id = xxDormitoryStudentService.findIdBySid(xxStudent.getId());
    	
		xxDormitoryStudentEntity.setId(id);
        xxDormitoryStudentEntity.setSid(xxStudent.getId());		//学生id
        xxDormitoryStudentEntity.setDid(xxStudent.getDid());	//宿舍id
        //根据宿舍id查询sid，再根据sid查询学校名称
        
        xxDormitoryStudentService.updateById(xxDormitoryStudentEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("school:xxstudent:delete")
    public R delete(@RequestBody Integer[] ids){
    	
    	//同时删除中间表数据,（首先删除中间表）
    	xxDormitoryStudentService.removeByIds(Arrays.asList(ids));
		xxStudentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
    
    /**
     * 根据学校查宿舍
     */
    @RequestMapping("/findDormitory")
    public R findDormitory(int sid) {
    	
    	List<XxDormitoryEntity> dormitoryList=xxDormitoryService.findNameAndIdBySId(sid);
    	
    	return R.ok().put("dormitoryList", dormitoryList);
    }
    
    /**
     * 根据宿舍id查宿舍
     */
    @RequestMapping("/findDormitoryName")
    public R findDormitoryName(int id) {
    	
    	List<XxDormitoryEntity> dormitoryList=new ArrayList<>();
    	//根据宿舍id查ss名称
    	String name=xxDormitoryService.FindName(id);
    	XxDormitoryEntity xxDormitoryEntity=new XxDormitoryEntity();
    	//将数据放入对象中
    	xxDormitoryEntity.setId(id);
    	xxDormitoryEntity.setName(name);
    	dormitoryList.set(1, xxDormitoryEntity);
    	return R.ok().put("dormitoryList", dormitoryList);
    }
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
        XxStudentEntity xxStudentEntity=new XxStudentEntity();
//        int sCode=0;			//学校编码
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
            
//            sCode=Integer.parseInt(lo.get(0).toString());	//学校编码
            dCode=Integer.parseInt(lo.get(1).toString());	//宿舍编码
            
            //先保存学生数据
            xxStudentService.save(xxStudentEntity);
            
            //中间表插入数据
        	int id=0;
        	id=xxStudentService.findMaxId();
            xxDormitoryStudentEntity.setSid(id);				//学生id
            xxDormitoryStudentEntity.setDid(xxDormitoryService.findIdByCode(dCode));	//宿舍编码转换成宿舍id
            //数据写入持久层
            //提交数据到中间表
            xxDormitoryStudentService.save(xxDormitoryStudentEntity);
        }
        return R.ok().put("state", op);
    }
}
