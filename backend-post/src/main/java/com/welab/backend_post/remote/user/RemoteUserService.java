package com.welab.backend_post.remote.user;

import com.welab.backend_post.common.dto.ApiResponseDto;
import com.welab.backend_post.remote.user.dto.SiteUserInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "backend-user", path = "/backend/user/v1")
public interface RemoteUserService {
    @GetMapping(value = "/user/{userId}")
    public ApiResponseDto<SiteUserInfoDto> userInfo(@PathVariable String userId);
}