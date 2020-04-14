package io.renren.modules.school.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * ????ѧ???
 * 
 * @author wufusheng
 * @email ghcghdc@mail.com
 * @date 2020-04-14 09:44:21
 */
@Data
@TableName("xx_dormitory_student")
public class XxDormitoryStudentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Id
	 */
	@TableId
	private Integer id;
	/**
	 * 宿舍id
	 */
	private Integer did;
	/**
	 * 学校Id
	 */
	private Integer sid;

}
