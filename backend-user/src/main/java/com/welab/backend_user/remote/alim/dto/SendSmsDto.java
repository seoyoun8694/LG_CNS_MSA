package com.welab.backend_user.remote.alim.dto;

import com.welab.backend_user.domain.SiteUser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SendSmsDto {
    @Getter
    @Setter
    public static class Request {
        private String userId;
        private String phoneNumber;
        private String title;
        private String message;

        public static Request fromEntity(SiteUser siteUser) {
            Request request = new Request();

            request.userId = siteUser.getUserId();
            request.phoneNumber = siteUser.getPhoneNumber();
            request.title = "가입축하 메시지 타이틀";
            request.message = "가입축하 메시지";

            return request;
        }
    }

    @Getter @Setter
    public static class Response {
        private String result;
    }
}