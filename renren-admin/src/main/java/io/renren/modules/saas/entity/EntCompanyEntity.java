package io.renren.modules.saas.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-04-08 15:33:16
 */
@Data
@TableName("ent_company")
public class EntCompanyEntity extends EntBaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 公司主键
	 */
	@TableId(type = IdType.AUTO)
	private Integer companyId;
	/**
	 * 唯一标识号
	 */
	@TableId(type =IdType.UUID )
	private String companyCode;
	/**
	 * 租户Id
	 */
	private String corpCode;
	/**
	 * 公司logo，2进制存储图片
	 */
	private String logo;
	/**
	 * 公司名
	 */
	private String name;
	/**
	 * 公司类型（股份有限公司等）
	 */
	private String companyType;
	/**
	 * 联系电话
	 */
	private String telephone;
	/**
	 * 公司域名
	 */
	private String companyDomain;
	/**
	 * 省份
	 */
	private Integer province;
	/**
	 * 城市
	 */
	private Integer city;
	/**
	 * 行业大类
	 */
	private String industry1;
	/**
	 * 行业类型
	 */
	private String industry2;
	/**
	 * 员工数目
	 */
	private Integer employeeNum;
	/**
	 * 经营范围
	 */
	private String businessScope;
	/**
	 * 标签
	 */
	private String tag;
	/**
	 * 企业抬头的唯一标识
	 */
	private Integer invoiceId;
	/**
	 * 企业认证的Id唯一标识
	 */
	private Integer authenticationId;
	/**
	 * 欢迎语
	 */
	private String welcome;
	/**
	 * 对成员加入的邀请短信信息
	 */
	private String invitationMember;
	/**
	 * 对合作伙伴的邀请短信信息
	 */
	private String invitationPartner;
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 修改人
	 */
	private String updateBy;
	@TableField(exist = false)
	private EntInvoiceEntity invoiceEntity;
	@TableField(exist = false)
	private EntAuthenticationEntity authenticationEntity;
	@TableField(exist = false)
	private String provinceName;
	@TableField(exist = false)
	private String cityName;
}
