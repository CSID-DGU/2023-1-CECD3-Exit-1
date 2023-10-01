package com.example.exitmedserver.pill.controller;

import com.example.exitmedserver.pill.dto.*;
import com.example.exitmedserver.pill.service.PillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PillController {
    private final PillService pillService;

    @PostMapping("/auth/pill/add-to-drawer")
    public PillAddDrawerResponseDto addDrawer(@RequestHeader("Authorization") String jwtToken, @RequestBody PillAddDrawerRequestDto pillAddDrawerRequestDto) {
        return pillService.addDrawer(jwtToken, pillAddDrawerRequestDto);
    }

    @GetMapping("/auth/pill/drawer")
    public PillGetDrawerListResponse getDrawerList(@RequestHeader("Authorization") String jwtToken) {
        return pillService.getDrawerList(jwtToken);
    }

    @GetMapping("/auth/pill/drawer/{pillItemSequence}")
    public PillGetPillInDrawerResponseDto getPillInDrawer(@RequestHeader("Authorization") String jwtToken, @PathVariable Long pillItemSequence) {
        return pillService.getPillInDrawer(jwtToken, pillItemSequence);
    }

    @GetMapping("/auth/pill/toggle-alarm/{pillItemSequence}")
    public PillToggleAlarmResponseDto toggleAlarm(@RequestHeader("Authorization") String jwtToken, @PathVariable Long pillItemSequence) {
        return pillService.toggleAlarm(jwtToken, pillItemSequence);
    }

    @GetMapping("/auth/pill/get-alarm")
    public PillGetAlarmListResponse getAlarmList(@RequestHeader("Authorization") String jwtToken) {
        return pillService.getAlarmList(jwtToken);
    }

    @GetMapping("/auth/pill/get-info/{pillItemSequence}")
    public PillGetPillDetailInfoResponseDto getPillDetailInfo(@RequestHeader("Authorization") String jwtToken, @PathVariable Long pillItemSequence) {
        return pillService.getPillDetailInfo(jwtToken, pillItemSequence);
    }
}
