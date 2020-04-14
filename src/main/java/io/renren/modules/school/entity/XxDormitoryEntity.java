package io.renren.modules.school.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * ?????
 * 
 * @author wufusheng
 * @email ghcghdc@mail.com
 * @date 2020-04-14 09:44:21
 */
@Data
@TableName("xx_dormitory")
public class XxDormitoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Id
	 */
	@TableId
	private Integer id;
	/**
	 * 学校Id
	 */
	private Integer sid;
	/**
	 * 宿舍名称
	 */
	private String name;
	/**
	 * 宿舍编号
	 */
	@NotBlank(message="宿舍编码不能为空")
	private String code;
	/**
	 * 描述信息
	 */
	private String description;
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

}
