package com.example.exitmedserver.pill.service;

import com.example.exitmedserver.pill.dto.PillAddDrawerRequestDto;
import com.example.exitmedserver.pill.dto.PillAddDrawerResponseDto;
import com.example.exitmedserver.pill.entity.Alarm;
import com.example.exitmedserver.pill.entity.Drawer;
import com.example.exitmedserver.pill.repository.AlarmRepository;
import com.example.exitmedserver.pill.repository.DrawerRepository;
import com.example.exitmedserver.util.auth.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
@RequiredArgsConstructor
public class PillService {
    private final DrawerRepository drawerRepository;
    private final AlarmRepository alarmRepository;

    public PillAddDrawerResponseDto addDrawer(String jwtToken, PillAddDrawerRequestDto pillAddDrawerRequestDto) {
        PillAddDrawerResponseDto pillAddDrawerResponseDto = new PillAddDrawerResponseDto();
        pillAddDrawerResponseDto.setAdded(false);

        JwtProvider jwtProvider = new JwtProvider();
        String userId = jwtProvider.getUserIdFromToken(jwtToken.replace("Bearer ", ""));

        Drawer tempDrawer = drawerRepository.findDrawerByUserIdAndPillItemSequence(userId, pillAddDrawerRequestDto.getPillItemSequence());
        if (tempDrawer == null) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm");
            try {
                Date date = new Date(dateFormatter.parse(pillAddDrawerRequestDto.getFinalDate()).getTime());
                Time takeTime = new Time(timeFormatter.parse(pillAddDrawerRequestDto.getTakeTime()).getTime());
                Drawer drawer = Drawer.builder()
                        .id(null)
                        .userId(userId)
                        .dosageCycle(pillAddDrawerRequestDto.getDosageCycle())
                        .finalDate(date)
                        .pillItemSequence(pillAddDrawerRequestDto.getPillItemSequence())
                        .comment(pillAddDrawerRequestDto.getComment())
                        .build();

                Alarm alarm = Alarm.builder()
                        .id(null)
                        .pillItemSequence(pillAddDrawerRequestDto.getPillItemSequence())
                        .userId(userId)
                        .takeTime(takeTime)
                        .build();

                drawerRepository.save(drawer);
                alarmRepository.save(alarm);
                pillAddDrawerResponseDto.setAdded(true);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return pillAddDrawerResponseDto;
    }
}
