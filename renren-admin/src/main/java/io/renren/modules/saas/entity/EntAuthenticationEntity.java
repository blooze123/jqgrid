package io.renren.modules.saas.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-04-08 15:33:16
 */
@Data
@TableName("ent_authentication")
public class EntAuthenticationEntity extends EntBaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 认证表主键，唯一标识
	 */
	@TableId
	private Integer authenticationId;
	/**
	 * 租户code
	 */
	private String corpCode;
	/**
	 * 公司编号
	 */
	private String companyCode;
	/**
	 * 组织机构代码
	 */
	private String organizationCode;
	/**
	 * 公司成立日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date setupDate;
	/**
	 * 注册资金
	 */
	private Double registeredCapital;
	/**
	 * 公司法人
	 */
	private String legalEntity;
	/**
	 * 企业地址
	 */
	private String companyAddress;
	/**
	 * 企业状态( 存续0, 在业1, 吊销2,注销3,迁入4,迁出5,停业6,清算7)
	 */
	private Integer companyState;
	/**
	 * 企业营业执照
	 */
	private String companyLicense;
	/**
	 * 审核状态
	 */
	private Integer state;
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 修改人
	 */
	private String updateBy;

	/**
	 * 认证人
	 */
	private String authenticator;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date authenTime;

	@TableField(exist = false)
	private String companyName;

	@TableField(exist = false)
	private AuthenEntity authenEntity;

}
