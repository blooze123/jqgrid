package io.renren.common.timer;

import io.renren.common.utils.DateUtils;
import io.renren.modules.saas.entity.EntCompanyEntity;
import io.renren.modules.saas.entity.OnlineUserEntity;
import io.renren.modules.saas.service.OnlineUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * 在线用户定时任务(每天0点触发)
 * auth YQ13512
 */
@Component
public class OnlineUserTask {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OnlineUserService ouService;

    @Scheduled(cron="0 0 0 * * ?")
    //@Scheduled(cron="* * * * * ?")
    private void process(){
        //获取前一天的日期
        Date date = DateUtils.addDateDays(new Date(), 1);
        //获取所有的公司
        List<EntCompanyEntity> cpList = ouService.getAllCompany();
        OnlineUserEntity ou = new OnlineUserEntity();
        ou.setCreateDate(date);
        SetOperations jedis = redisTemplate.opsForSet();
        //遍历所有的公司记录当天的用户在线情况
        for (EntCompanyEntity company : cpList) {
            //设置公司ID
            ou.setCompany(company);
            //从redis中获取当天的在线用户数并删除该集合
            Long size = jedis.size(company.getCompanyId().toString());
            int online = size.intValue();
            ou.setOnline(online);
            //判断该公司在线人数是否大于0，是则删除
            if(size>0){
                jedis.pop(company.getCompanyId().toString(),size);
            }
            //设置公司总人数
            ou.setTotal(ouService.getCompanyTotal(company.getCompanyId()));
            //设置活跃度
            DecimalFormat df = new DecimalFormat("0.00");
            String a = df.format((float)online/ou.getTotal());
            ou.setActivity(a);
            //设置公司异常用户数
            ou.setAbnormalUser(ouService.getAbnormalUser(company.getCompanyId()));
            //设置公司未注册用户数
            ou.setUnregisteredUser(ouService.getUnregisteredUser(company.getCompanyId()));
            //插入数据
            ouService.insert(ou);
        }
    }

}
