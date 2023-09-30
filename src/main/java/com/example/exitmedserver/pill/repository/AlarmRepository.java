package com.example.exitmedserver.pill.repository;

import com.example.exitmedserver.pill.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Integer> {
    List<Alarm> findAlarmByUserId(String userId);
    Alarm findByPillItemSequence(Long pillItemSequence);
    Alarm findAlarmByUserIdAndPillItemSequence(String userId, Long pillItemSequence);
}
