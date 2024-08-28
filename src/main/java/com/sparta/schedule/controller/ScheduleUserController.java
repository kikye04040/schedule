package com.sparta.schedule.controller;

import com.sparta.schedule.dto.AssignRequestDto;
import com.sparta.schedule.dto.AssignResponseDto;
import com.sparta.schedule.dto.ScheduleUserResponseDto;
import com.sparta.schedule.service.ScheduleUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule-users")
public class ScheduleUserController {
    private final ScheduleUserService scheduleUserService;
    public ScheduleUserController(ScheduleUserService scheduleUserService) {
        this.scheduleUserService = scheduleUserService;
    }

    @PostMapping("/assign")
    public ResponseEntity<AssignResponseDto> assignUserToSchedule(@RequestBody AssignRequestDto requestDto) {
        try {
            scheduleUserService.assignUserToSchedule(requestDto.getScheduleId(), requestDto.getUserId());
            AssignResponseDto responseDto = new AssignResponseDto(true, "유저를 일정에 성공적으로 할당되었습니다.");
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            AssignResponseDto responseDto = new AssignResponseDto(false, "유저를 일정 할당에 실패하였습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    @GetMapping("/{scheduleId}/users")
    public List<ScheduleUserResponseDto> getUsersAssignedToSchedule(@PathVariable Long scheduleId) {
        return scheduleUserService.getUsersAssignedToSchedule(scheduleId);
    }
}
