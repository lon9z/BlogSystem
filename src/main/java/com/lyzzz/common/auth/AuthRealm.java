package com.lyzzz.common.auth;

import com.lyzzz.blog.entity.User;
import com.lyzzz.blog.service.UserService;
import com.lyzzz.common.constants.enmus.CommonEnum;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @location： BlogSystem\com.lyzzz.common.auth
 * @creatTime: 2020/7/18  8:38
 * @author: Administrator
 * @remark:
 *
 *
 * AuthRealm 认证/权限
 * 继承AuthorizingRealm
 *
 */

@Component
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * @author Administrator
     * @date 2020/7/18 8:50
     * @return org.apache.shiro.authz.AuthorizationInfo
     *
     *  权限校验
     *
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * @author Administrator
     * @date 2020/7/18 8:51
     * @return org.apache.shiro.authc.AuthenticationInfo
     *
     *  身份校验
     *
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        User user = userService.findByName(userName);

        if (userName == null){
            throw new AuthenticationException(CommonEnum.TOKEN_ERROR.getMsg());
        }
        if (user == null || !user.getPassword().equals(password)){
            throw new IncorrectCredentialsException(CommonEnum.LOGIN_ERROR.getMsg());
        }

        return new SimpleAuthenticationInfo(
                user,
                password,
                getName());
    }
}

