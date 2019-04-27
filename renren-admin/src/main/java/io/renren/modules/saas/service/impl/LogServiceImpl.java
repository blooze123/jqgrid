package io.renren.modules.saas.service.impl;

import io.renren.modules.saas.entity.LogConfigEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.saas.dao.LogDao;
import io.renren.modules.saas.entity.LogEntity;
import io.renren.modules.saas.service.LogService;

import javax.annotation.Resource;


@Service("logService")
public class LogServiceImpl extends ServiceImpl<LogDao, LogEntity> implements LogService {

    @Resource
    private LogDao lDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogEntity> page = this.page(
                new Query<LogEntity>().getPage(params),
                new QueryWrapper<LogEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 获取日志配置
     * @param url
     * @return
     */
    @Override
    public LogConfigEntity getLogConfig(String url) {
        return lDao.getLogConfig(url);
    }

    /**
     * 插入
     */
    @Override
    public void insert(LogEntity log) {
        lDao.save(log);
    }


}
