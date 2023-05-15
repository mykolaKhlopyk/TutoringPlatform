package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.repository.UserRepository;
import com.mkh.tutoringplatform.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.spi.RegisterableService;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void register(User user) {
        userRepository.save(user);
    }
}
