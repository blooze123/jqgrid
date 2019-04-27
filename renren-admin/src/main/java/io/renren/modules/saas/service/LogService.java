package io.renren.modules.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.saas.entity.LogConfigEntity;
import io.renren.modules.saas.entity.LogEntity;

import java.util.Map;

/**
 * 
 *
 * @author YQ13512
 * @email 501658204@qq.com
 * @date 2019-04-25 10:08:08
 */
public interface LogService extends IService<LogEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取日志配置
     * @param url
     * @return
     */
    LogConfigEntity getLogConfig(String url);

    /**
     * 插入
     */
    void insert(LogEntity log);
}

