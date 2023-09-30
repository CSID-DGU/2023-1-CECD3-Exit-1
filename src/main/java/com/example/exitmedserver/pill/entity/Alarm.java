package com.example.exitmedserver.pill.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@NoArgsConstructor
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "pill_item_sequence")
    private Long pillItemSequence;

    @Column(name = "take_time")
    private Time takeTime;

    @Builder
    public Alarm(Integer id, String userId, Long pillItemSequence, Time takeTime) {
        this.id = id;
        this.userId = userId;
        this.pillItemSequence = pillItemSequence;
        this.takeTime = takeTime;
    }
}
