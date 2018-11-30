package com.springboot.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.security.entity.OauthClient;
import com.springboot.security.mapper.OauthClientMapper;
import com.springboot.security.service.OauthClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zt
 * @since 2018-11-20
 */
@Service
public class OauthClientServiceImpl extends ServiceImpl<OauthClientMapper, OauthClient> implements OauthClientService {


    @Autowired
    private OauthClientMapper oauthClientMapper;

    @Override
    public OauthClient getClientByClientId(String clientId) {

        QueryWrapper<OauthClient> wrapper = new QueryWrapper<>();
        clientId= StringUtils.trim(clientId);
        if (StringUtils.isNoneEmpty(clientId)) {
            wrapper.eq("client_id",clientId);
        }
        List<OauthClient>clients=oauthClientMapper.selectList(wrapper);
        if(clients==null || clients.size()==0){
            return null;
        }
        return clients.get(0);
    }
}
