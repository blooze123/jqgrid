package io.renren.modules.saas.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author YQ13512
 * @email 501658204@qq.com
 * @date 2019-04-26 19:31:08
 */
@Data
@TableName("cd_log_config")
public class LogConfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 日志标题
	 */
	private String titlt;
	/**
	 * 请求地址
	 */
	private String url;
	/**
	 * 日志类型
	 */
	private Integer logType;
	/**
	 * 业务类型
	 */
	private String bussType;
	/**
	 *（0：前台  1：后台）
	 */
	private Integer type;

}
