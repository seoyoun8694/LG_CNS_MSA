package com.welab.backend_alim.api.open;

import com.welab.backend_alim.domain.dto.SendSmsDto;
import com.welab.backend_alim.remote.user.RemoteUserService;
import com.welab.backend_alim.remote.user.dto.UserInfoDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/alim/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AlimController {
    private final RemoteUserService remoteUserService;

    @GetMapping("/user/info")
    public UserInfoDto.Response userInfo(@RequestParam String userId) {
        return remoteUserService.userInfo(userId);
    }
}
