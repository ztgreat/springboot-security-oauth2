package com.springboot.security.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.security.entity.SysOauthClient;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.security.entity.SysRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zt
 * @since 2018-11-20
 */
public interface SysOauthClientService extends IService<SysOauthClient> {

    SysOauthClient getClientByClientId(String clientId);

    IPage<SysOauthClient> page(int current, int pageSize, String search);

}
