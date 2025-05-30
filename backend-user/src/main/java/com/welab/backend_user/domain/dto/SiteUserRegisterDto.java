package com.welab.backend_user.domain.dto;

import com.welab.backend_user.common.exception.BadParameter;
import com.welab.backend_user.domain.SiteUser;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteUserRegisterDto {
    @NotBlank(message = "아이디를 입력하세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력하세요.")
    private String password;

    @NotBlank(message = "전화번호를 입력하세요.")
    private String phoneNumber;

    public SiteUser toEntity() {
        SiteUser siteUser = new SiteUser();

        siteUser.setUserId(this.userId);
        siteUser.setPassword(this.password);
        siteUser.setPhoneNumber(this.phoneNumber);

        return siteUser;
    }
}
