package com.api.gateway.api.gateway.filter;

import com.api.gateway.api.gateway.Exception.TokenExpireException;
import com.api.gateway.api.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if(routeValidator.isSecure.test(exchange.getRequest())){
              if(!exchange.getRequest().getHeaders().containsKey("AUTHORIZATION")){
                  throw new RuntimeException("Missing Authorization Headers");
              }
              String authHeader = exchange.getRequest().getHeaders().get("AUTHORIZATION").get(0);
              if(authHeader!=null && authHeader.startsWith("Bearer")){
                  authHeader =authHeader.substring(7);
              }
              try {
                  jwtUtil.verifyTokenAndExttractAllClaims(authHeader);
              }catch (Exception e){
                  e.printStackTrace();
                  throw new TokenExpireException("Access Token expired");
              }

            }
            return chain.filter(exchange);
        };

    }

    public AuthenticationFilter() {
        super(Config.class);
    }

    public static class Config{

    }


}
