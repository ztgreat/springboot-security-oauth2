package com.springboot.security.oauth2;
import com.springboot.security.entity.OauthClient;
import com.springboot.security.service.OauthClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * 从 数据库 获取 oauth 客户端信息
 * @author ztgreat
 * @since 2018-11-20
 */
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {


    @Autowired
    private OauthClientService oauthClientService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

       OauthClient client= oauthClientService.getClientByClientId(clientId);
       if (client==null){
           return null;
       }
        ClientDetails clientDetails = new ClientDetailsImpl(client);
        return clientDetails;
    }
}
