package com.example.exitmedserver.pill.controller;

import com.example.exitmedserver.pill.dto.PillAddDrawerRequestDto;
import com.example.exitmedserver.pill.dto.PillAddDrawerResponseDto;
import com.example.exitmedserver.pill.service.PillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PillController {
    private final PillService pillService;
    @PostMapping("/auth/pill/add-to-drawer")
    public PillAddDrawerResponseDto addDrawer(@RequestHeader("Authorization") String jwtToken, @RequestBody PillAddDrawerRequestDto pillAddDrawerRequestDto) {
        return pillService.addDrawer(jwtToken, pillAddDrawerRequestDto);
    }
}
