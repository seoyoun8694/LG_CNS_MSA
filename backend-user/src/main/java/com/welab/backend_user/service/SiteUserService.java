package com.welab.backend_user.service;

import com.welab.backend_user.common.exception.BadParameter;
import com.welab.backend_user.common.exception.NotFound;
import com.welab.backend_user.domain.SiteUser;
import com.welab.backend_user.domain.dto.SiteUserInfoDto;
import com.welab.backend_user.domain.dto.SiteUserLoginDto;
import com.welab.backend_user.domain.dto.SiteUserRefreshDto;
import com.welab.backend_user.domain.dto.SiteUserRegisterDto;
import com.welab.backend_user.domain.repository.SiteUserRepository;
import com.welab.backend_user.remote.alim.RemoteAlimService;
import com.welab.backend_user.remote.alim.dto.SendSmsDto;
import com.welab.backend_user.secret.hash.SecureHashUtils;
import com.welab.backend_user.secret.jwt.TokenGenerator;
import com.welab.backend_user.secret.jwt.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SiteUserService {
    private final SiteUserRepository siteUserRepository;
    private final TokenGenerator tokenGenerator;
    private final RemoteAlimService remoteAlimService;

    @Transactional
    public void registerUser(SiteUserRegisterDto registerDto) {
        SiteUser siteUser = registerDto.toEntity();

        siteUserRepository.save(siteUser);

        // 알림톡 전송 요청
        SendSmsDto.Request request = SendSmsDto.Request.fromEntity(siteUser);
        remoteAlimService.sendSms(request);
    }

    @Transactional(readOnly = true)
    public TokenDto.AccessRefreshToken login(SiteUserLoginDto loginDto) {
        SiteUser user = siteUserRepository.findByUserId(loginDto.getUserId());
        if (user == null) {
            throw new NotFound("아이디 또는 비밀번호를 확인하세요.");
        }

        if(!SecureHashUtils.matches(loginDto.getPassword(), user.getPassword())) {
            throw new BadParameter("아이디 또는 비밀번호를 확인하세요.");
        }

        return tokenGenerator.generateAccessRefreshToken(loginDto.getUserId(), "WEB");
    }

    @Transactional(readOnly = true)
    public TokenDto.AccessToken refresh(SiteUserRefreshDto refreshDto) {
        String userId = tokenGenerator.validateJwtToken(refreshDto.getToken());
        if (userId == null) {
            throw new BadParameter("토큰이 유효하지 않습니다.");
        }

        SiteUser user = siteUserRepository.findByUserId(userId);
        if (user == null) {
            throw new NotFound("사용자를 찾을 수 없습니다.");
        }

        return tokenGenerator.generateAccessToken(userId, "WEB");
    }

    @Transactional(readOnly = true)
    public SiteUserInfoDto userInfo(String userId) {
        SiteUser user = siteUserRepository.findByUserId(userId);
        if (user == null) {
            throw new NotFound("사용자를 찾을 수 없습니다.");
        }
        return SiteUserInfoDto.fromEntity(user);
    }
}