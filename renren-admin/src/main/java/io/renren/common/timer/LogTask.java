package io.renren.common.timer;

import io.renren.common.utils.DateUtils;
import io.renren.modules.saas.entity.EntCompanyEntity;
import io.renren.modules.saas.entity.LogConfigEntity;
import io.renren.modules.saas.entity.LogEntity;
import io.renren.modules.saas.service.LogService;
import io.renren.modules.saas.service.OnlineUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 前后台日志定时任务(每天0点触发)
 * auth YQ13512
 */
@Component
public class LogTask {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OnlineUserService ouService;
    @Autowired
    private LogService lService;

    //@Scheduled(cron="* * * * * ?")
    @Scheduled(cron="0 0 0 * * ?")
    private void process(){
        HashOperations jedisHash = redisTemplate.opsForHash();
        SetOperations jedisSet = redisTemplate.opsForSet();
        LogEntity log = new LogEntity();
        //获取前一天的日期
        Date date = DateUtils.addDateDays(new Date(), 1);
        //获取所有的公司
        List<EntCompanyEntity> cpList = ouService.getAllCompany();
        //遍历所有的公司记录当天的用户在线情况
        for (EntCompanyEntity company : cpList) {
            Set<String> keys = jedisHash.keys(company.getCompanyId().toString());
            String url = null;
            for(String key : keys){
                url = key.substring(0, key.indexOf('_'));
                LogConfigEntity logConfig = lService.getLogConfig(url);
                //设置基本信息
                log.setTitle(logConfig.getTitlt());
                log.setUrl(logConfig.getUrl());
                log.setLogType(logConfig.getLogType());
                log.setBusinessType(logConfig.getBussType());
                log.setType(logConfig.getType());
                log.setOperationDate(date);
                log.setCompany(company);
                //设置响应时间
                log.setResponseTime(Long.valueOf((String) jedisHash.get(company.getCompanyId().toString(),url + "_responseTime")));
                //设置响应次数
                log.setResponseNumber(Integer.valueOf((String) jedisHash.get(company.getCompanyId().toString(),url + "_responseNumber")));
                //设置异常次数
                log.setExceptionNumber(Integer.valueOf((String) jedisHash.get(company.getCompanyId().toString(),url + "_exceptionNumber")));
                //设置设备数量
                log.setEquipmentQuantity(jedisSet.size(company.getCompanyId() + "_" + url + "_hostName").intValue());
                //设置浏览器种类
                log.setBrowserNumber(jedisSet.size(company.getCompanyId() + "_" + url + "_browserType").intValue());
                //插入这条日志
                lService.insert(log);
            }
            //删除redis中当天所有的数据
            redisTemplate.delete(company.getCompanyId() + "_" + url + "_browserType");
            redisTemplate.delete(company.getCompanyId() + "_" + url + "_hostName");
            redisTemplate.delete(company.getCompanyId().toString());
        }
    }
}
