package com.welab.backend_alim.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SendSmsDto {
    @Getter @Setter
    public static class Request {
        private String userId;
        private String phoneNumber;
        private String title;
        private String message;
    }

    @Getter @Setter
    public static class Response {
        private String result;
    }
}
