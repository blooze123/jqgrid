package io.renren.modules.saas.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author blooze
 * @email 1459264166@qq.com
 * @date 2019-04-25 11:34:20
 */
@Data
@TableName("saas_authen")
public class AuthenEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer authenId;
	/**
	 * 公司id
	 */
	private String companyCode;
	/**
	 * 经办人
	 */
	private String operator;
	/**
	 * 经办时间
	 */
	private Date operatorTime;
	/**
	 * 
	 */
	private String createBy;
	/**
	 * 
	 */
	private String updateBy;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;



}
