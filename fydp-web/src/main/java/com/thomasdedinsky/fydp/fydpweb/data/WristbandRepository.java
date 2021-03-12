package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.auth.User;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WristbandRepository extends JpaRepository<Wristband, Long> {
    List<Wristband> findByUser(User user);
    List<Wristband> findByIdNotIn(List<Long> idList);
    List<Wristband> findByIdNotInAndUser(List<Long> idList, User user);
}
