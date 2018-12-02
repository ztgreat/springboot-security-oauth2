package com.springboot.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.security.entity.SysMenu;
import com.springboot.security.entity.SysOauthClient;
import com.springboot.security.entity.SysRole;
import com.springboot.security.mapper.SysOauthClientMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.security.service.SysOauthClientService;
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
public class SysOauthClientServiceImpl extends ServiceImpl<SysOauthClientMapper, SysOauthClient> implements SysOauthClientService {


    @Autowired
    private SysOauthClientMapper sysOauthClientMapper;

    @Override
    public SysOauthClient getClientByClientId(String clientId) {

        QueryWrapper<SysOauthClient> wrapper = new QueryWrapper<>();
        clientId= StringUtils.trim(clientId);
        if (StringUtils.isNoneEmpty(clientId)) {
            wrapper.eq("client_id",clientId);
        }
        List<SysOauthClient>clients= sysOauthClientMapper.selectList(wrapper);
        if(clients==null || clients.size()==0){
            return null;
        }
        return clients.get(0);
    }

    @Override
    public IPage<SysOauthClient> page(int pageNum, int pageSize, String search) {

        QueryWrapper<SysOauthClient> wrapper = new QueryWrapper<>();
        search=StringUtils.trim(search);
        if (StringUtils.isNoneEmpty(search)) {
            wrapper.like(true,"client_id","%" + search + "%");
        }
        Page<SysOauthClient> page = new Page<>(pageNum,pageSize);
        return sysOauthClientMapper.selectPage(page,wrapper);
    }

}
