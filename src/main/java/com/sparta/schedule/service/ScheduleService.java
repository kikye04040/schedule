package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.dto.UserResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.repository.ScheduleRepository;
import com.sparta.schedule.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        Schedule schedule = new Schedule(requestDto, user);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(savedSchedule);
    }

    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID를 가진 할일이 존재하지 않습니다."));
        ScheduleResponseDto dto = new ScheduleResponseDto(schedule);
        if (schedule.getScheduleUsers() != null) {
            dto.setUsers(schedule.getScheduleUsers().stream().map(scheduleUser -> {
                return new UserResponseDto(scheduleUser.getUser());
            }).collect(Collectors.toList()));
        }
        return dto;
    }

    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID를 가진 할일이 존재하지 않습니다."));
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        schedule.Update(requestDto, user);
        Schedule updatedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(updatedSchedule);
    }

    public List<ScheduleResponseDto> getSchedules(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Schedule> schedules = scheduleRepository.findAllByOrderByModifiedDateDesc(pageable);
        return schedules.stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }
}