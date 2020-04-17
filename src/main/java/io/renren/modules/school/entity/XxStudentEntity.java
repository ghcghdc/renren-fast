package io.renren.modules.school.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * ѧ???
 * 
 * @author wufusheng
 * @email ghcghdc@mail.com
 * @date 2020-04-14 09:44:22
 */
@Data
@TableName("xx_student")
public class XxStudentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Id
	 */
	@TableId
	private Integer id;
	/**
	 *学生姓名
	 */
	private String name;
	/**
	 * 学生编码
	 */
	@NotBlank(message="学生编码不能为空")
	private String code;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 创建人
	 */
	private Integer crtUser;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")			//设置传入时间为年-月-日
	private Date crtTime;
	/**
	 * 修改人
	 */
	private Integer updUser;
	/**
	 * 修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")			//设置传入时间为年-月-日
	private Date updTime;
	
	//学校名称非数据库内容
	
	@TableField(exist = false)
	private String schoolName;
	
	//宿舍名称非数据库内容
	
	@TableField(exist = false)
	private String dormitoryName;


}
