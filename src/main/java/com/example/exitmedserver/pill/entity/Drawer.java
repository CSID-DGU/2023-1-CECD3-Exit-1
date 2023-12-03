package com.example.exitmedserver.pill.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

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

    @Column(name = "monday", columnDefinition = "TINYINT")
    private boolean monday;

    @Column(name = "tuesday", columnDefinition = "TINYINT")
    private boolean tuesday;

    @Column(name = "wednesday", columnDefinition = "TINYINT")
    private boolean wednesday;

    @Column(name = "thursday", columnDefinition = "TINYINT")
    private boolean thursday;

    @Column(name = "friday", columnDefinition = "TINYINT")
    private boolean friday;

    @Column(name = "saturday", columnDefinition = "TINYINT")
    private boolean saturday;

    @Column(name = "sunday", columnDefinition = "TINYINT")
    private boolean sunday;

    @Column(name = "count_per_dosage")
    private Integer countPerDosage;

    @Column(name = "count_per_day")
    private Integer countPerDay;

    @Builder
    public Drawer(Integer id, String userId, Long pillItemSequence, Date finalDate, String dosageCycle, String comment,
                  boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday, boolean saturday, boolean sunday,
                  Integer countPerDosage, Integer countPerDay) {
        this.id = id;
        this.userId = userId;
        this.pillItemSequence = pillItemSequence;
        this.finalDate = finalDate;
        this.dosageCycle = dosageCycle;
        this.comment = comment;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
        this.countPerDosage = countPerDosage;
        this.countPerDay = countPerDay;
    }
}
