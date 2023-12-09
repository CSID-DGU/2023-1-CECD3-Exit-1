package com.example.exitmedserver.pill.dto;

import lombok.Data;

import java.util.List;

@Data
public class PillAddDrawerRequestDto {
    private Long pillItemSequence;
    private String finalDate;
    private String dosageCycle;
    private String comment;
    private List<String> takeTime;
    private DayForDrawer dayForDrawer;
    private Integer countPerDosage;
    private Integer countPerDay;
}
