package com.api.gateway.api.gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openEndPoints=
         Arrays.asList(
                "/auth-service/v1/user/login",
                "/auth-service/v1/user/signup"
        );

    public Predicate<ServerHttpRequest> isSecure =
            request->openEndPoints.stream()
                    .noneMatch(uri->request.getURI().getPath().contains(uri));


}
