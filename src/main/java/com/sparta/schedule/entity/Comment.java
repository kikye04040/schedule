package com.sparta.schedule.entity;

import com.sparta.schedule.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comments")
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    private String username;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Comment(Schedule schedule, CommentRequestDto requestDto) {
        this.schedule = schedule;
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
        this.modifiedDate = LocalDateTime.now();
    }
}
