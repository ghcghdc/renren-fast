package io.renren.modules.school.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * ѧУ?
 * 
 * @author wufusheng
 * @email ghcghdc@mail.com
 * @date 2020-04-14 09:44:22
 */
@Data
@TableName("xx_school")
public class XxSchoolEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Id
	 */
	@TableId
	private Integer id;
	/**
	 * 学校名称
	 */
	private String name;
	/**
	 * 学校编号
	 */
	@NotBlank(message="学校编码不能为空")
	private String code;
	/**
	 * 创建人
	 */
	private Integer crtUser;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")			//设置传入时间为年-月-日
	private Date crtTime;

}
