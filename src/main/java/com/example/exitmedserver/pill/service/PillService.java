package com.example.exitmedserver.pill.service;

import com.example.exitmedserver.pill.dto.*;
import com.example.exitmedserver.pill.entity.Alarm;
import com.example.exitmedserver.pill.entity.Drawer;
import com.example.exitmedserver.pill.entity.Pill;
import com.example.exitmedserver.pill.entity.PillImage;
import com.example.exitmedserver.pill.repository.AlarmRepository;
import com.example.exitmedserver.pill.repository.DrawerRepository;
import com.example.exitmedserver.pill.repository.PillImageRepository;
import com.example.exitmedserver.pill.repository.PillRepository;
import com.example.exitmedserver.util.auth.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PillService {
    private final DrawerRepository drawerRepository;
    private final AlarmRepository alarmRepository;
    private final PillRepository pillRepository;
    private final PillImageRepository pillImageRepository;

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
                        .isTurnedOn(true)
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

    public PillGetDrawerListResponse getDrawerList(String jwtToken) {
        PillGetDrawerListResponse pillGetDrawerListResponse = new PillGetDrawerListResponse();
        List<PillGetDrawerListResponseDto> drawerList = new ArrayList<>();

        JwtProvider jwtProvider = new JwtProvider();
        String userId = jwtProvider.getUserIdFromToken(jwtToken.replace("Bearer ", ""));

        List<Drawer> searchedDrawerList = drawerRepository.findDrawerByUserId(userId);
        if (!searchedDrawerList.isEmpty()) {
            List<Alarm> alarmList = alarmRepository.findAlarmByUserId(userId);
            int size = searchedDrawerList.size();
            for (int i = 0; i < size; i++) {
                PillGetDrawerListResponseDto pillGetDrawerListResponseDto = new PillGetDrawerListResponseDto();
                Drawer searchedDrawer = searchedDrawerList.get(i);
                Pill searchedPill = pillRepository.findPillByPillItemSequence(searchedDrawer.getPillItemSequence());

                PillImage searchedPillImage = pillImageRepository.findByPillItemSequence(searchedDrawer.getPillItemSequence());
                pillGetDrawerListResponseDto.setPillItemSequence(searchedDrawer.getPillItemSequence());
                pillGetDrawerListResponseDto.setPillName(searchedPill.getPillName());
                pillGetDrawerListResponseDto.setClassification(searchedPill.getClassification());
                pillGetDrawerListResponseDto.setImageLink(searchedPillImage.getImageLink());
                pillGetDrawerListResponseDto.setAlarmTurnedOn(alarmList.get(i).isTurnedOn());

                drawerList.add(pillGetDrawerListResponseDto);
            }
            pillGetDrawerListResponse.setData(drawerList);
        }
        return pillGetDrawerListResponse;
    }

    public PillGetPillInDrawerResponseDto getPillInDrawer(String jwtToken, Long pillItemSequence) {
        PillGetPillInDrawerResponseDto pillGetPillInDrawerResponseDto = new PillGetPillInDrawerResponseDto();

        JwtProvider jwtProvider = new JwtProvider();
        String userId = jwtProvider.getUserIdFromToken(jwtToken.replace("Bearer ", ""));

        Drawer searchedDrawer = drawerRepository.findDrawerByUserIdAndPillItemSequence(userId, pillItemSequence);
        if (searchedDrawer != null) {
            Alarm searchedAlarm = alarmRepository.findByPillItemSequence(pillItemSequence);
            Pill searchedPill = pillRepository.findPillByPillItemSequence(pillItemSequence);
            PillImage searchedPillImage = pillImageRepository.findByPillItemSequence(pillItemSequence);
            pillGetPillInDrawerResponseDto.setPillName(searchedPill.getPillName());
            pillGetPillInDrawerResponseDto.setImageLink(searchedPillImage.getImageLink());
            SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
            pillGetPillInDrawerResponseDto.setAlarm(timeFormatter.format(searchedAlarm.getTakeTime()));
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            pillGetPillInDrawerResponseDto.setFinalDate(dateFormatter.format(searchedDrawer.getFinalDate()));
            pillGetPillInDrawerResponseDto.setDosage(searchedPill.getDosage());
            pillGetPillInDrawerResponseDto.setComment(searchedDrawer.getComment());
        }
        return pillGetPillInDrawerResponseDto;
    }

    public PillToggleAlarmResponseDto toggleAlarm(String jwtToken, Long pillItemSequence) {
        PillToggleAlarmResponseDto pillToggleAlarmResponseDto = new PillToggleAlarmResponseDto();

        return pillToggleAlarmResponseDto;
    }
}
