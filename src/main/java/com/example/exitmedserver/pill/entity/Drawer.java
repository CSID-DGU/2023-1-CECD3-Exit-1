package com.example.exitmedserver.pill.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Entity
@Getter
@NoArgsConstructor
public class Drawer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "pill_item_sequence")
    private Long pillItemSequence;

    @Column(name = "final_date")
    private Date finalDate;

    @Column(name = "dosage_cycle")
    private String dosageCycle;

    @Column(name = "comment")
    private String comment;

    @Builder
    public Drawer(Integer id, String userId, Long pillItemSequence, Date finalDate, String dosageCycle, String comment) {
        this.id = id;
        this.userId = userId;
        this.pillItemSequence = pillItemSequence;
        this.finalDate = finalDate;
        this.dosageCycle = dosageCycle;
        this.comment = comment;
    }
}
