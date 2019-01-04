package com.springboot.security.oauth2;

import com.springboot.security.entity.OauthClient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * ClientDetails的实现
 * 注:粗略实现,部分方法不完善
 * @author ztgreat
 * @since 2018-11-10
 */
public class ClientDetailsImpl implements ClientDetails {

    private OauthClient client;

    private static final String stringSplit=",";


    public ClientDetailsImpl(OauthClient client) {
        this.client = client;
    }

    @Override
    public String getClientId() {
        return client.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        HashSet<String>ret=new HashSet<>();
        if(client.getResourceIds()==null){
            return ret;
        }
        String[] resources=client.getResourceIds().split(stringSplit);
        for (String resource:resources){
            ret.add(resource);
        }
        return ret;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return client.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        HashSet<String>ret=new HashSet<>();
        if(client.getScope()==null){
            return ret;
        }
        String[] scops=client.getScope().split(stringSplit);
        for (String scop:scops){
            ret.add(scop);
        }
        return ret;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {

        HashSet<String>ret=new HashSet<>();
        if(client.getAuthorizedGrantTypes()==null){
            return ret;
        }
        String[] authorizedGrantTypes=client.getAuthorizedGrantTypes().split(stringSplit);
        for (String authorizedGrantType:authorizedGrantTypes){
            ret.add(authorizedGrantType);
        }
        return ret;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        HashSet<String>ret=new HashSet<>();
        if(client.getWebServerRedirectUri()==null){
            return ret;
        }
        String[] redirectUris=client.getWebServerRedirectUri().split(stringSplit);
        for (String redirectUri:redirectUris){
            ret.add(redirectUri);
        }
        return ret;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        HashSet<GrantedAuthority>ret=new HashSet<>();
        if(client.getAuthorities()==null){
            return ret;
        }
        String[] authorities=client.getAuthorities().split(stringSplit);
        for (String authoritie:authorities){
            ret.add(new SimpleGrantedAuthority(authoritie));
        }
        return ret;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return client.getAccessTokenValidity();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return client.getRefreshTokenValidity();
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
