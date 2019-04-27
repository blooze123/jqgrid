package io.renren.modules.saas.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.saas.entity.EntAuthenticationEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.saas.entity.AuthenLogEntity;
import io.renren.modules.saas.service.AuthenLogService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author blooze
 * @email 1459264166@qq.com
 * @date 2019-04-25 11:34:20
 */
@RestController
@RequestMapping("saas/authenlog")
public class AuthenLogController {
    @Autowired
    private AuthenLogService authenLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("saas:authenlog:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authenLogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{authenLogId}")
    @RequiresPermissions("saas:authenlog:info")
    public R info(@PathVariable("authenLogId") Integer authenLogId){
        AuthenLogEntity authenLog = authenLogService.getById(authenLogId);

        return R.ok().put("authenLog", authenLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("saas:authenlog:save")
    public R save(@RequestBody AuthenLogEntity authenLog){
        authenLogService.save(authenLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("saas:authenlog:update")
    public R update(@RequestBody AuthenLogEntity authenLog){
        ValidatorUtils.validateEntity(authenLog);
        authenLogService.updateById(authenLog);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("saas:authenlog:delete")
    public R delete(@RequestBody Integer[] authenLogIds){
        authenLogService.removeByIds(Arrays.asList(authenLogIds));
        return R.ok();
    }

//    @RequestMapping("/selectAll")
//    @RequiresPermissions("saas:authenlog:list")
//    public R selectAll(@RequestParam Map<String, Object> params){
//        List<EntAuthenticationEntity> authenticationEntityList=authenLogService.selectAll();
//        PageUtils page=new PageUtils(authenticationEntityList,authenticationEntityList.size(),Integer.parseInt(params.get("limit").toString()) ,Integer.parseInt(params.get("page").toString()));
//        return R.ok().put("page", page);
//    }


    /**
     * blooze
     * 按条件查询，空默认是查询所以
     * @param jsonobj
     * @param params
     * @return
     */
    @RequestMapping("/selectByCondition")
    @RequiresPermissions("saas:authenlog:select")
    public R selectByCondition(String jsonobj,@RequestParam Map<String, Object> params){
        EntAuthenticationEntity authenticationEntity=JSON.parseObject(jsonobj,EntAuthenticationEntity.class);
        System.out.println(authenticationEntity);
        List<EntAuthenticationEntity> list=authenLogService.selectByCondition(authenticationEntity);
        PageUtils page=new PageUtils(list,list.size(),Integer.parseInt(params.get("limit").toString()) ,Integer.parseInt(params.get("page").toString()));
        return R.ok().put("page", page);
//        return R.ok();
    }


    /**
     * blooze
     * 查询认证的弹窗信息
     * @param jsonobj
     * @return
     */
    @RequestMapping("/selectBycompanyCode")
    @RequiresPermissions("saas:authenlog:authen")
    public R selectBycompanyCode(String jsonobj){
        EntAuthenticationEntity authenticationEntity=JSON.parseObject(jsonobj,EntAuthenticationEntity.class);
        System.out.println(authenticationEntity);
        return authenLogService.selectBycompanyCode(authenticationEntity);
    }

    /**
     * blooze
     * 查询详情的接口
     * @param companyCode
     * @return
     */
    @RequestMapping("/selectAuthenLog")
    @RequiresPermissions("saas:authenlog:detail")
    public R selectAuthenLog(String companyCode){
        EntAuthenticationEntity authenticationEntity=new EntAuthenticationEntity();
        authenticationEntity.setCompanyCode(companyCode);
        List<EntAuthenticationEntity> list=authenLogService.selectByCondition(authenticationEntity);
        List<AuthenLogEntity> authenLogEntityList=authenLogService.selectAuthenLog(companyCode);
        return  R.ok().put("authenLogEntityList",authenLogEntityList).put("EntAuthenticationEntityList",list);
    }

    /**
     * blooze
     * 插入认证信息的接口
     * @param authenLogEntity
     * @return
     */
    @RequestMapping("/insertSaasLog")
    @RequiresPermissions("saas:authenlog:insert")
    public R insertSaasLog(AuthenLogEntity authenLogEntity){
        boolean isSuccess=authenLogService.save(authenLogEntity);
        if (isSuccess){
            return R.ok("操作成功");
        }
        return R.error("操作异常！！！");
    }





}
