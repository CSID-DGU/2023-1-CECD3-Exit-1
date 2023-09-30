package com.example.exitmedserver.pill.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@Getter
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

    @Column(name = "is_turned_on", columnDefinition = "TINYINT")
    private boolean isTurnedOn;

    @Builder
    public Alarm(Integer id, String userId, Long pillItemSequence, Time takeTime, boolean isTurnedOn) {
        this.id = id;
        this.userId = userId;
        this.pillItemSequence = pillItemSequence;
        this.takeTime = takeTime;
        this.isTurnedOn = isTurnedOn;
    }
}
