package com.sparta.schedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignResponseDto {
    private boolean success;
    private String message;

    public AssignResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
