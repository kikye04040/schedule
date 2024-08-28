package com.sparta.schedule.dto;

import com.sparta.schedule.entity.ScheduleUser;
import lombok.Getter;

@Getter
public class ScheduleUserResponseDto {
    private Long scheduleId;
    private Long userId;

    public ScheduleUserResponseDto(ScheduleUser scheduleUser) {
        this.scheduleId = scheduleUser.getSchedule().getId();
        this.userId = scheduleUser.getUser().getId();
    }
}
