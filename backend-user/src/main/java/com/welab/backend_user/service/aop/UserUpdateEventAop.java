package com.welab.backend_user.service.aop;

import com.welab.backend_user.common.type.ActionAndId;
import com.welab.backend_user.domain.SiteUser;
import com.welab.backend_user.domain.event.SiteUserInfoEvent;
import com.welab.backend_user.domain.repository.SiteUserRepository;
import com.welab.backend_user.event.producer.KafkaMessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class UserUpdateEventAop {
    private final SiteUserRepository siteUserRepository;
    private final KafkaMessageProducer kafkaMessageProducer;
    @AfterReturning(
            value = "execution(* com.welab.backend_user.service.SiteUserService.*AndNotify(..))",returning = "actionAndId"
    )
    public void publishUserUpdateEvent(JoinPoint joinPoint, ActionAndId actionAndId) {publishUserUpdateEvent(actionAndId);
    }

    public void publishUserUpdateEvent(ActionAndId actionAndId) {
        try {
            SiteUser siteUser = siteUserRepository.findById(actionAndId.getId())
                    .orElse(null);
            if (siteUser == null) {
                return;
            }
            SiteUserInfoEvent event = SiteUserInfoEvent.fromEntity(actionAndId.getAction(), siteUser);kafkaMessageProducer.send(SiteUserInfoEvent.Topic, event);
        } catch (Exception e) {
            log.warn("사용자 정보 업데이트 이벤트를 전송하지 못하였습니다. id={}", actionAndId.getId());
        }
    }
}