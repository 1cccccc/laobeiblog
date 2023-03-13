package com.xi.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    /**
     * AuthorizationDecision返回的如果是true表示通过授权，如果是false表示无法通过授权，没有权限访问
     * @param authentication the {@link Supplier} of the {@link Authentication} to check
     * @param requestAuthorizationContext the {@link RequestAuthorizationContext} object to check
     * @return
     */
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        LoginUserEntity loginUser = (LoginUserEntity) authentication.get().getPrincipal();

        //TODO 通过用户id查询角色信息
        //TODO 通过所有的角色id查询权限id
        //TODO 通过所有的权限id去查询权限
        /**
         遍历权限列表，通过下面的方式进行比对，如果比对成功表示用户具有该权限

             HttpServletRequest request = requestAuthorizationContext.getRequest();
             AntPathRequestMatcher matcher = new AntPathRequestMatcher(用户的权限url, 用户的的权限url的请求方式);
             matcher.matches(request);//进行方法比对

         */

        //这里如果没有通过授权我们需要抛出一个BadCredentialsException异常，以便我们后面捕获之后返回给客户端
        return new AuthorizationDecision(true);
    }
}
