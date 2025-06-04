package com.welab.backend_user.api.backend;

import com.welab.backend_user.domain.dto.UserInfoDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/backend/user/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class BackendUserController {
    @GetMapping(value = "/hello")
    public String hello() {
        return "유저 백엔드 서비스가 호출되었습니다";
    }

    @PostMapping(value = "/info")
    public UserInfoDto.Response userInfo(@RequestBody UserInfoDto.Request request) {
        UserInfoDto.Response response = new UserInfoDto.Response();

        response.setUserId(request.getUserId());
        response.setUserName("개발자");
        response.setPhoneNumber("010-0000-0000");

        return response;
    }

    @GetMapping(value = "/info")
    public UserInfoDto.Response userInfo(@RequestParam("userId") String userId) {
        log.info("userInfo: userId={}", userId);

        UserInfoDto.Response response = new UserInfoDto.Response();

        response.setUserId(userId);
        response.setUserName("개발자");
        response.setPhoneNumber("010-0000-0000");

        return response;
    }
}