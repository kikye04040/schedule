package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ScheduleResponseDto {
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private int commentCount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<UserResponseDto> users;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.userId = schedule.getUser().getId();
        this.title = schedule.getTitle();
        this.description = schedule.getDescription();
        this.commentCount = schedule.getCommentCount();
        this.createdDate = schedule.getCreatedDate();
        this.modifiedDate = schedule.getModifiedDate();
    }
}
