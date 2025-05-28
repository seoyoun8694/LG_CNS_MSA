package com.welab.backend_alim.remote.user;

import com.welab.backend_alim.remote.user.dto.UserInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "backend-user", path = "/backend/user/v1")
public interface RemoteUserService {
    @PostMapping(value = "/info")
    UserInfoDto.Response userInfo(@RequestBody UserInfoDto.Request request);
}