package io.ggogit.ggogit.domain.member.service;


import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthService {
    ResponseEntity<String> login(List<String> credentials);
}
