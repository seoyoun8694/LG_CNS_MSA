package com.welab.backend_user.remote.alim;

import com.welab.backend_user.remote.alim.dto.SendSmsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "backend-alim", path = "/backend/alim/v1")
public interface RemoteAlimService {
    @GetMapping(value = "/hello")
    String hello();

    @PostMapping(value = "/sms")
    SendSmsDto.Response sendSms(SendSmsDto.Request request);
}
