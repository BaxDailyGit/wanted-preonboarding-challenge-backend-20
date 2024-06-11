package com.example.WantedMarket.controller;

import com.example.WantedMarket.domain.dto.JoinDto;
import com.example.WantedMarket.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

    @Autowired
    private JoinService joinService;


    @GetMapping("/join")
    public String joinPage() {

        return "join";
    }


    @PostMapping("/joinProc")
    public String joinProcess(JoinDto joinDto) {

        System.out.println(joinDto.getUsername());

        joinService.joinProcess(joinDto);


        return "redirect:/login";
    }
}