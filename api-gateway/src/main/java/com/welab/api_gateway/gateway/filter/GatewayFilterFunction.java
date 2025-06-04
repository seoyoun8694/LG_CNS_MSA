package com.welab.api_gateway.gateway.filter;

import org.springframework.cloud.gateway.server.mvc.common.Shortcut;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import static org.springframework.web.servlet.function.HandlerFilterFunction.ofRequestProcessor;

public interface GatewayFilterFunction {
    @Shortcut
    static HandlerFilterFunction<ServerResponse, ServerResponse> addAuthenticationHeader() {
        return ofRequestProcessor(AuthenticationHeaderFilterFunction.addHeader());
    }
}