package com.thomasdedinsky.fydp.fydpweb.auth;

import com.thomasdedinsky.fydp.fydpweb.auth.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
    public ConfirmationToken findConfirmationTokenByConfirmationToken(String token);
}