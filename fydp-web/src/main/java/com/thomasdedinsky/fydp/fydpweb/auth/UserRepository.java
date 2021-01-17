package com.thomasdedinsky.fydp.fydpweb.auth;

import com.thomasdedinsky.fydp.fydpweb.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);
    User findByEmail(String email);
}
