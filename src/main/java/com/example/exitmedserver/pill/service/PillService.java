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
import com.example.exitmedserver.search.entity.FavoriteList;
import com.example.exitmedserver.search.repository.FavoriteListRepository;
import com.example.exitmedserver.user.entity.UserProfile;
import com.example.exitmedserver.user.repository.UserProfileRepository;
import com.example.exitmedserver.util.auth.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PillService {
    private final DrawerRepository drawerRepository;
    private final AlarmRepository alarmRepository;
    private final PillRepository pillRepository;
    private final PillImageRepository pillImageRepository;
    private final UserProfileRepository userProfileRepository;
    private final FavoriteListRepository favoriteListRepository;

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
            Alarm searchedAlarm = alarmRepository.findAlarmByUserIdAndPillItemSequence(userId, pillItemSequence);
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

        JwtProvider jwtProvider = new JwtProvider();
        String userId = jwtProvider.getUserIdFromToken(jwtToken.replace("Bearer ", ""));

        Alarm searchedAlarm = alarmRepository.findAlarmByUserIdAndPillItemSequence(userId, pillItemSequence);
        if (searchedAlarm.isTurnedOn()) {
            Alarm newAlarm = Alarm.builder()
                    .id(null)
                    .userId(userId)
                    .pillItemSequence(pillItemSequence)
                    .takeTime(searchedAlarm.getTakeTime())
                    .isTurnedOn(false)
                    .build();
            alarmRepository.delete(searchedAlarm);
            alarmRepository.save(newAlarm);
            pillToggleAlarmResponseDto.setOn(false);
            pillToggleAlarmResponseDto.setPillItemSequence(pillItemSequence);
        } else {
            Alarm newAlarm = Alarm.builder()
                    .id(null)
                    .userId(userId)
                    .pillItemSequence(pillItemSequence)
                    .takeTime(searchedAlarm.getTakeTime())
                    .isTurnedOn(true)
                    .build();
            alarmRepository.delete(searchedAlarm);
            alarmRepository.save(newAlarm);
            pillToggleAlarmResponseDto.setOn(true);
            pillToggleAlarmResponseDto.setPillItemSequence(pillItemSequence);
        }

        return pillToggleAlarmResponseDto;
    }

    public PillGetAlarmListResponse getAlarmList(String jwtToken) {
        PillGetAlarmListResponse pillGetAlarmListResponse = new PillGetAlarmListResponse();
        List<PillGetAlarmListResponseDto> alarmList = new ArrayList<>();

        JwtProvider jwtProvider = new JwtProvider();
        String userId = jwtProvider.getUserIdFromToken(jwtToken.replace("Bearer ", ""));

        List<Alarm> searchedAlarm = alarmRepository.findAlarmByUserIdAndIsTurnedOnOrderByTakeTimeAsc(userId, true);
        if (!searchedAlarm.isEmpty()) {
            for (Alarm alarm : searchedAlarm) {
                Pill searchedPill = pillRepository.findPillByPillItemSequence(alarm.getPillItemSequence());
                PillGetAlarmListResponseDto pillGetAlarmListResponseDto = new PillGetAlarmListResponseDto();
                pillGetAlarmListResponseDto.setPillName(searchedPill.getPillName());
                SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
                String takeTime = timeFormatter.format(alarm.getTakeTime());
                pillGetAlarmListResponseDto.setTakeTime(takeTime);
                LocalTime timeFromInput = LocalTime.parse(takeTime, DateTimeFormatter.ofPattern("HH:mm"));
                pillGetAlarmListResponseDto.setPassed(LocalTime.now().isAfter(timeFromInput));

                alarmList.add(pillGetAlarmListResponseDto);
            }
            pillGetAlarmListResponse.setData(alarmList);
        }

        return pillGetAlarmListResponse;
    }

    public PillGetPillDetailInfoResponseDto getPillDetailInfo(String jwtToken, Long pillItemSequence) {
        PillGetPillDetailInfoResponseDto pillGetPillDetailInfoResponseDto = new PillGetPillDetailInfoResponseDto();

        JwtProvider jwtProvider = new JwtProvider();
        String userId = jwtProvider.getUserIdFromToken(jwtToken.replace("Bearer ", ""));

        // pillName, dosage, warning => Pill
        // isPregnant, age => UserProfile
        // isFavorite => FavoriteList
        // imageLink => PillImage
        Pill searchedPill = pillRepository.findPillByPillItemSequence(pillItemSequence);
        UserProfile searchedUserProfile = userProfileRepository.findUserProfileByUserId(userId);
        FavoriteList searchedFavoriteList = favoriteListRepository.findFavoriteListByUserIdAndPillItemSequence(userId, pillItemSequence);
        PillImage searchedPillImage = pillImageRepository.findByPillItemSequence(pillItemSequence);

        pillGetPillDetailInfoResponseDto.setPillName(searchedPill.getPillName());
        pillGetPillDetailInfoResponseDto.setDosage(searchedPill.getDosage());
        pillGetPillDetailInfoResponseDto.setWarning(searchedPill.getWarning());
        pillGetPillDetailInfoResponseDto.setClassification(searchedPill.getClassification());
        pillGetPillDetailInfoResponseDto.setEffect(searchedPill.getEffect());
        pillGetPillDetailInfoResponseDto.setStorage(searchedPill.getStorage());
        pillGetPillDetailInfoResponseDto.setIngredient(searchedPill.getIngredient());
        pillGetPillDetailInfoResponseDto.setPregnant(searchedUserProfile.isPregnant());
        pillGetPillDetailInfoResponseDto.setAge(Year.now().getValue() - searchedUserProfile.getDateOfBirth());
        pillGetPillDetailInfoResponseDto.setFavorite(searchedFavoriteList != null);
        pillGetPillDetailInfoResponseDto.setImageLink(searchedPillImage.getImageLink());

        return pillGetPillDetailInfoResponseDto;
    }
}
