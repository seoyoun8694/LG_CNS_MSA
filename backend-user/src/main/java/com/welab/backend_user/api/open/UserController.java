package com.welab.backend_user.api.open;

import com.welab.backend_user.common.dto.ApiResponseDto;
import com.welab.backend_user.common.exception.NotFound;
import com.welab.backend_user.common.web.context.GatewayRequestHeaderUtils;
import com.welab.backend_user.remote.alim.RemoteAlimService;
import com.welab.backend_user.remote.alim.dto.SendSmsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/user/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {
    private final RemoteAlimService remoteAlimService;

    @GetMapping(value = "/test")
    public ApiResponseDto<String> test() {
        // String response = remoteAlimService.sms();
        String userId = GatewayRequestHeaderUtils.getUserIdOrThrowException();
        log.info("userId = {}", userId);
        return ApiResponseDto.createOk(userId);
    }
    @PostMapping(value = "/sms")
    public ApiResponseDto<SendSmsDto.Response> sms(@RequestBody SendSmsDto.Request request) {
        var result = remoteAlimService.sendSms(request);
        return ApiResponseDto.createOk(result);
    }
}
