package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleUserResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.entity.ScheduleUser;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.repository.ScheduleRepository;
import com.sparta.schedule.repository.ScheduleUserRepository;
import com.sparta.schedule.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleUserService {
    private final ScheduleUserRepository scheduleUserRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    public ScheduleUserService(ScheduleUserRepository scheduleUserRepository, ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleUserRepository = scheduleUserRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    public void assignUserToSchedule(Long scheduleId, Long userId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 할일이 존재하지 않습니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        ScheduleUser scheduleUser = new ScheduleUser(schedule, user);
        scheduleUserRepository.save(scheduleUser);
    }

    public List<ScheduleUserResponseDto> getUsersAssignedToSchedule(Long scheduleId) {
        return scheduleUserRepository.findAll().stream()
                .filter(scheduleUser -> scheduleUser.getSchedule().getId().equals(scheduleId))
                .map(ScheduleUserResponseDto::new)
                .collect(Collectors.toList());
    }
}
