package com.springboot.security.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.security.entity.OauthClient;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zt
 * @since 2018-11-20
 */
public interface OauthClientService extends IService<OauthClient> {

    OauthClient getClientByClientId(String clientId);

    IPage<OauthClient> page(int current, int pageSize, String search);

}
