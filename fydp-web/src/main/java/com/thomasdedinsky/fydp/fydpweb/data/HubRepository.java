package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.Hub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HubRepository extends JpaRepository<Hub, Long> {
    List<Hub> findByNickname(String nickname);
    List<Hub> findByIdNotIn(List<Long> idList);
}
