package com.thomasdedinsky.fydp.fydpweb.auth;

import com.thomasdedinsky.fydp.fydpweb.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
    User findByEmail(String email);
}
