package io.renren.modules.saas.dao;

import io.renren.modules.saas.entity.LogConfigEntity;
import io.renren.modules.saas.entity.LogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author YQ13512
 * @email 501658204@qq.com
 * @date 2019-04-25 10:08:08
 */
@Mapper
public interface LogDao extends BaseMapper<LogEntity> {

    /**
     * 获取日志配置
     * @param url
     * @return
     */
    LogConfigEntity getLogConfig(String url);

    /**
     * 插入
     */
    void save(LogEntity log);

}
