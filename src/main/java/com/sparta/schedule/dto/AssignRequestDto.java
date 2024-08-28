package com.sparta.schedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignRequestDto {
    private Long scheduleId;
    private Long userId;
}
