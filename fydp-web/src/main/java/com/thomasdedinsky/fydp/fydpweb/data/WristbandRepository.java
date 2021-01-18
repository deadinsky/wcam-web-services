package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.auth.User;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WristbandRepository extends JpaRepository<Wristband, Integer> {
    List<Wristband> findByUser(User user);
    Page<Wristband> findByUser(User user, Pageable pageable);
    List<Wristband> findByIdNotIn(List<Integer> idList);
    List<Wristband> findByIdNotInAndUser(List<Integer> idList, User user);
}
