package com.example.exitmedserver.pill.service;

import com.example.exitmedserver.pill.dto.*;
import com.example.exitmedserver.pill.entity.*;
import com.example.exitmedserver.pill.repository.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PillService {
    private final DrawerRepository drawerRepository;
    private final AlarmRepository alarmRepository;
    private final PillRepository pillRepository;
    private final PillImageRepository pillImageRepository;
    private final UserProfileRepository userProfileRepository;
    private final FavoriteListRepository favoriteListRepository;
    private final IngredientRepository ingredientRepository;
    private final MaxIngredientRepository maxIngredientRepository;

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
                Drawer drawer = Drawer.builder()
                        .id(null)
                        .userId(userId)
                        .dosageCycle(pillAddDrawerRequestDto.getDosageCycle())
                        .finalDate(date)
                        .pillItemSequence(pillAddDrawerRequestDto.getPillItemSequence())
                        .comment(pillAddDrawerRequestDto.getComment())
                        .monday(pillAddDrawerRequestDto.getDayForDrawer().isMonday())
                        .tuesday(pillAddDrawerRequestDto.getDayForDrawer().isTuesday())
                        .wednesday(pillAddDrawerRequestDto.getDayForDrawer().isWednesday())
                        .thursday(pillAddDrawerRequestDto.getDayForDrawer().isThursday())
                        .friday(pillAddDrawerRequestDto.getDayForDrawer().isFriday())
                        .saturday(pillAddDrawerRequestDto.getDayForDrawer().isSaturday())
                        .sunday(pillAddDrawerRequestDto.getDayForDrawer().isSunday())
                        .countPerDosage(pillAddDrawerRequestDto.getCountPerDosage())
                        .countPerDay(pillAddDrawerRequestDto.getCountPerDay())
                        .build();

                List<String> takeTimeList = pillAddDrawerRequestDto.getTakeTime();
                for (String takeTime : takeTimeList) {
                    Time takeTimeToAdd = new Time(timeFormatter.parse(takeTime).getTime());
                    Alarm alarm = Alarm.builder()
                            .id(null)
                            .pillItemSequence(pillAddDrawerRequestDto.getPillItemSequence())
                            .userId(userId)
                            .takeTime(takeTimeToAdd)
                            .isTurnedOn(true)
                            .build();
                    alarmRepository.save(alarm);
                }
                drawerRepository.save(drawer);

                pillAddDrawerResponseDto.setAdded(true);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return pillAddDrawerResponseDto;
    }

    public PillGetMaxAllowedResponse getMaxAllowed(String jwtToken, Long pillItemSequence) {
        PillGetMaxAllowedResponse pillGetMaxAllowedResponse = new PillGetMaxAllowedResponse();
        List<PillGetMaxAllowedResponseDto> maxAllowedList = new ArrayList<>();

        JwtProvider jwtProvider = new JwtProvider();
        String userId = jwtProvider.getUserIdFromToken(jwtToken.replace("Bearer ", ""));

        // get all ingredient list of the pill that user want to add
        // 추가하고 싶은 약의 모든 성분 불러오기
        List<Ingredient> ingredientsToAdd = ingredientRepository.findIngredientByPillItemSequence(pillItemSequence);

        // get all max ingredient of the pill that user want to add
        // 추가하고 싶은 약의 모든 최대 허용량 불러오기
        List<MaxIngredient> maxIngredientsToAdd = new ArrayList<>();
        for (Ingredient ingredient : ingredientsToAdd) {
            MaxIngredient maxIngredient = maxIngredientRepository.findMaxIngredientByMainIngredient(ingredient.getIngredient());
            if (maxIngredient != null) {
                maxIngredientsToAdd.add(maxIngredient);
                PillGetMaxAllowedResponseDto pillGetMaxAllowedResponseDto = new PillGetMaxAllowedResponseDto();
                pillGetMaxAllowedResponseDto.setMaxDosage(maxIngredient.getMaxDosage());
                pillGetMaxAllowedResponseDto.setMainIngredient(maxIngredient.getMainIngredient());
                pillGetMaxAllowedResponseDto.setUnit(maxIngredient.getUnit());
                pillGetMaxAllowedResponseDto.setDosage(ingredient.getDosage());
                maxAllowedList.add(pillGetMaxAllowedResponseDto);
            }
        }

        //List<MaxIngredient> searchedMaxIngredient = maxIngredientRepository.findMaxIngredientByPillItemSequence(pillItemSequence);
        List<IngredientDataHolder> ingredientDataHolderList = new ArrayList<>();

        // get all item sequence list of user
        // 유저의 약서랍에 들어있는 모든 약의 item sequence 불러오기
        List<Drawer> userDrawer = drawerRepository.findDrawerByUserId(userId);
        List<Long> itemSequenceList = new ArrayList<>();

        for (MaxIngredient maxIngredient : maxIngredientsToAdd) {
            String ingredientName = maxIngredient.getMainIngredient();
            for (Drawer drawer : userDrawer) {
                List<Ingredient> ingredientsInDrawer = ingredientRepository.findIngredientByPillItemSequence(drawer.getPillItemSequence());
                for (Ingredient ingredient : ingredientsInDrawer) {
                    if (ingredient.getIngredient().equals(ingredientName)) {
                        IngredientDataHolder ingredientDataHolder = new IngredientDataHolder();
                        ingredientDataHolder.setIngredientName(ingredientName);
                        ingredientDataHolder.setDosage(ingredient.getDosage());
                        ingredientDataHolder.setCntPerDay(drawer.getCountPerDay());
                        ingredientDataHolder.setCntPerDosage(drawer.getCountPerDosage());
                        ingredientDataHolder.setUnit(ingredient.getUnit());
                        ingredientDataHolderList.add(ingredientDataHolder);
                    }
                }
            }
        }

        // ingredientDataHolderList에는
        // {성분명, 투여량, 단위, 몇알, 몇회} 리스트 형태로 저장되어있음
        Map<String, Float> ingredientAndAccumulatedDosage = new HashMap<>();
        for (IngredientDataHolder ingredientDataHolder : ingredientDataHolderList) {
            String ingredientName = ingredientDataHolder.getIngredientName();
            Float accumulatedDosage = ingredientDataHolder.getDosage() * ingredientDataHolder.getCntPerDosage() * ingredientDataHolder.getCntPerDay();
            if (ingredientAndAccumulatedDosage.containsKey(ingredientName)) {
                Float oldAccumulatedDosage = ingredientAndAccumulatedDosage.get(ingredientName);
                ingredientAndAccumulatedDosage.replace(ingredientName, oldAccumulatedDosage + accumulatedDosage);
            } else {
                ingredientAndAccumulatedDosage.put(ingredientName, accumulatedDosage);
            }
        }

        for (PillGetMaxAllowedResponseDto pillGetMaxAllowedResponseDto : maxAllowedList) {
            for (String ingredient : ingredientAndAccumulatedDosage.keySet()) {
                if (pillGetMaxAllowedResponseDto.getMainIngredient().equals(ingredient)) {
                    pillGetMaxAllowedResponseDto.setAccumulatedDosage(ingredientAndAccumulatedDosage.get(ingredient));
                }
            }
        }


        // item sequence, 하루 복용 개수 map
//        Map<Long, Integer> ingredientAndCount = new HashMap<>();
//        int cntPerDosage = 0;
//        int cntPerDay = 0;
//        for (Drawer drawer : userDrawer) {
//            List<Ingredient> ingredientsInDrawer = ingredientRepository.findIngredientByPillItemSequence(drawer.getPillItemSequence());
//            for (Ingredient ingredient : ingredientsInDrawer) {
//                for (MaxIngredient maxIngredient : maxIngredientsToAdd) {
//                    if (ingredient.getIngredient().equals(maxIngredient.getMainIngredient())) {
//                        cntPerDosage += drawer.getCountPerDosage();
//                        cntPerDay  += drawer.getCountPerDay();
//                        // item sequence, 하루 복용 개수 map
//                        if (ingredientAndCount.containsKey(drawer.getPillItemSequence())) {
//                            int newCnt = ingredientAndCount.get(drawer.getPillItemSequence()) + cntPerDay * cntPerDosage;
//                            ingredientAndCount.replace(drawer.getPillItemSequence(), newCnt);
//                        } else {
//                            ingredientAndCount.put(drawer.getPillItemSequence(), cntPerDay * cntPerDosage);
//                        }
//                    }
//                }
//            }
        //itemSequenceList.add(drawer.getPillItemSequence());
        //}

        // 추가하려는 약 성분 중에서 허용량 제한이 있는 성분과 약 서합에 들어있는 약들의 모든 성분 중에서 겹치는 성분 찾기
//        for (Long sequence : itemSequenceList) {
//            ingredientsToAdd.clear();
//            ingredientsToAdd = ingredientRepository.findIngredientByPillItemSequence(sequence);
//            for (Ingredient ingredient : ingredientsToAdd) {
//                for (MaxIngredient maxIngredient : maxIngredientsToAdd) {
//                    if (ingredient.getIngredient().equals(maxIngredient.getMainIngredient())) {
//                    }
//                }
//            }
//        }

//        for (Ingredient ingredient : ingredientsToAdd) {
//            String ingredientName = ingredient.getIngredient();
//
//            ingredientsToAdd.add(maxIngredientRepository.findMaxIngredientByMainIngredient(ingredientName));
//
//        }

        pillGetMaxAllowedResponse.setData(maxAllowedList);
        return pillGetMaxAllowedResponse;
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

                boolean isTurnedOn = false;
                List<Alarm> searchedAlarms = alarmRepository.findAlarmListByUserIdAndPillItemSequence(userId, searchedDrawer.getPillItemSequence());
                for (Alarm alarm : searchedAlarms) {
                    if (alarm.isTurnedOn()) {
                        isTurnedOn = true;
                        break;
                    }
                }
                pillGetDrawerListResponseDto.setAlarmTurnedOn(isTurnedOn);
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
            //Alarm searchedAlarm = alarmRepository.findAlarmByUserIdAndPillItemSequence(userId, pillItemSequence);
            List<Alarm> searchedAlarmList = alarmRepository.findAlarmListByUserIdAndPillItemSequence(userId, pillItemSequence);
            Pill searchedPill = pillRepository.findPillByPillItemSequence(pillItemSequence);
            PillImage searchedPillImage = pillImageRepository.findByPillItemSequence(pillItemSequence);
            pillGetPillInDrawerResponseDto.setPillName(searchedPill.getPillName());
            pillGetPillInDrawerResponseDto.setImageLink(searchedPillImage.getImageLink());
            SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
            List<String> alarmList = new ArrayList<>();
            for (Alarm alarm : searchedAlarmList) {
                alarmList.add(timeFormatter.format(alarm.getTakeTime()));
            }
            //pillGetPillInDrawerResponseDto.setAlarm(timeFormatter.format(searchedAlarm.getTakeTime()));
            pillGetPillInDrawerResponseDto.setAlarm(alarmList);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            pillGetPillInDrawerResponseDto.setFinalDate(dateFormatter.format(searchedDrawer.getFinalDate()));
            if (searchedDrawer.getDosageCycle().equals("EVERY_DAY")) {
                pillGetPillInDrawerResponseDto.setDosageCycle("매일");
            } else {
                String dosageCycle = "";
                if (searchedDrawer.isMonday()) {
                    dosageCycle += "월요일,";
                }
                if (searchedDrawer.isTuesday()) {
                    dosageCycle += "화요일,";
                }
                if (searchedDrawer.isWednesday()) {
                    dosageCycle += "수요일,";
                }
                if (searchedDrawer.isThursday()) {
                    dosageCycle += "목요일,";
                }
                if (searchedDrawer.isFriday()) {
                    dosageCycle += "금요일,";
                }
                if (searchedDrawer.isSaturday()) {
                    dosageCycle += "토요일,";
                }
                if (searchedDrawer.isSunday()) {
                    dosageCycle += "일요일,";
                }
                dosageCycle = dosageCycle.substring(0, dosageCycle.length() - 1);
                pillGetPillInDrawerResponseDto.setDosageCycle(dosageCycle);
            }
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
        List<Drawer> searchedDrawerList = drawerRepository.findDrawerByUserId(userId);

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

        String searchedAtcCode = searchedPill.getAtcCode();
        List<String> duplicatedPills = new ArrayList<>();
        for (Drawer drawer : searchedDrawerList) {
            Pill searchedPillInDrawer = pillRepository.findPillByPillItemSequence(drawer.getPillItemSequence());
            if (searchedPillInDrawer.getAtcCode().equals(searchedAtcCode)) {
                duplicatedPills.add(searchedPillInDrawer.getPillName());
            }
        }
        pillGetPillDetailInfoResponseDto.setDuplicatedPills(duplicatedPills);
        return pillGetPillDetailInfoResponseDto;
    }

    public PillGetClosestResponseDto getClosest(String jwtToken) {
        PillGetClosestResponseDto pillGetClosestResponseDto = new PillGetClosestResponseDto();

        JwtProvider jwtProvider = new JwtProvider();
        String userId = jwtProvider.getUserIdFromToken(jwtToken.replace("Bearer ", ""));

        List<Alarm> searchedAlarmList = alarmRepository.findAlarmByUserIdAndIsTurnedOnOrderByTakeTimeAsc(userId, true);
        if (!searchedAlarmList.isEmpty()) {
            LocalTime curTime = LocalTime.now();
            SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
            for (Alarm searchedAlarm : searchedAlarmList) {
                String takeTime = timeFormatter.format(searchedAlarm.getTakeTime());
                LocalTime timeFromInput = LocalTime.parse(takeTime, DateTimeFormatter.ofPattern("HH:mm"));
                if (curTime.isBefore(timeFromInput)) {
                    Pill searchedPill = pillRepository.findPillByPillItemSequence(searchedAlarm.getPillItemSequence());
                    pillGetClosestResponseDto.setPillName(searchedPill.getPillName());
                    pillGetClosestResponseDto.setTakeTime(takeTime);
                    break;
                }
            }
        }
        return pillGetClosestResponseDto;
    }
}
