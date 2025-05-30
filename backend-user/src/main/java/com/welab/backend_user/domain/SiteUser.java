package com.welab.backend_user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Entity
@Table(name = "site_user")
public class SiteUser {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column(name = "user_id", unique = true, nullable = false)
    @Getter @Setter
    private String userId;

    @Column(name = "password", nullable = false)
    @Getter @Setter
    private String password;

    @Column(name = "phone_number", nullable = false)
    @Setter @Getter
    private String phoneNumber;

    @Column(name = "deleted", nullable = false)
    @Getter @Setter
    private Boolean deleted = false;
}
