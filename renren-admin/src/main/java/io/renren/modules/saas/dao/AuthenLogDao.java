package io.renren.modules.saas.dao;

import io.renren.common.utils.R;
import io.renren.modules.saas.entity.AuthenEntity;
import io.renren.modules.saas.entity.AuthenLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.saas.entity.EntAuthenticationEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author blooze
 * @email 1459264166@qq.com
 * @date 2019-04-25 11:34:20
 */
@Mapper
public interface AuthenLogDao extends BaseMapper<AuthenLogEntity> {

    List<EntAuthenticationEntity> selectAll();

    List<EntAuthenticationEntity> selectByCondition(EntAuthenticationEntity authenticationEntity);

    List<EntAuthenticationEntity> selectBycompanyCode(EntAuthenticationEntity authenticationEntity);

    List<AuthenLogEntity> selectAuthenLog(String companyCode);
}
