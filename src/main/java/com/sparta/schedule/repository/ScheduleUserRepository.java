package com.sparta.schedule.repository;

import com.sparta.schedule.entity.ScheduleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleUserRepository extends JpaRepository<ScheduleUser, Long> {
}
