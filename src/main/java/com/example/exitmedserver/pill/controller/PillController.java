package com.example.exitmedserver.pill.controller;

import com.example.exitmedserver.pill.dto.PillAddDrawerRequestDto;
import com.example.exitmedserver.pill.dto.PillAddDrawerResponseDto;
import com.example.exitmedserver.pill.dto.PillGetDrawerListResponse;
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
}
