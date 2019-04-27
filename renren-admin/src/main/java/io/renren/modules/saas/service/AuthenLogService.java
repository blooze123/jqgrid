package io.renren.modules.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.saas.entity.AuthenLogEntity;
import io.renren.modules.saas.entity.EntAuthenticationEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author blooze
 * @email 1459264166@qq.com
 * @date 2019-04-25 11:34:20
 */
public interface AuthenLogService extends IService<AuthenLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<EntAuthenticationEntity> selectAll();

    List<EntAuthenticationEntity> selectByCondition(EntAuthenticationEntity authenticationEntity);

    R selectBycompanyCode(EntAuthenticationEntity authenticationEntity);

    List<AuthenLogEntity> selectAuthenLog(String companyCode);
}

