package com.welab.backend_user.service;

import com.welab.backend_user.common.exception.BadParameter;
import com.welab.backend_user.common.exception.NotFound;
import com.welab.backend_user.domain.SiteUser;
import com.welab.backend_user.domain.dto.SiteUserLoginDto;
import com.welab.backend_user.domain.dto.SiteUserRegisterDto;
import com.welab.backend_user.domain.repository.SiteUserRepository;
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

    @Transactional
    public void registerUser(SiteUserRegisterDto registerDto) {
        SiteUser siteUser = registerDto.toEntity();

        siteUserRepository.save(siteUser);
    }

    @Transactional(readOnly = true)
    public TokenDto.AccessRefreshToken login(SiteUserLoginDto loginDto) {
        SiteUser user = siteUserRepository.findByUserId(loginDto.getUserId());
        if (user == null) {
            throw new NotFound("사용자를 찾을 수 없습니다.");
        }
        if( !SecureHashUtils.matches(loginDto.getPassword(), user.getPassword())){throw new BadParameter("비밀번호가 맞지 않습니다.");
        }
        return tokenGenerator.generateAccessRefreshToken(loginDto.getUserId(), "WEB");}
}
