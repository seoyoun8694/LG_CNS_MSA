package com.welab.backend_user.domain.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfoDto {
    @Getter @Setter
    public static class Request {
        private String userId;
    }

    @Getter @Setter
    public static class Response {
        private String userId;
        private String userName;
        private String phoneNumber;
    }
}
