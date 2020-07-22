package com.lyzzz.common.auth;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @location： BlogSystem\com.lyzzz.common.auth
 * @creatTime: 2020/7/18  9:08
 * @author: Administrator
 * @remark:
 */

@Component
public class AuthSessionManager extends DefaultWebSessionManager {

    public AuthSessionManager() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        return super.getSessionId(request, response);
    }
}
