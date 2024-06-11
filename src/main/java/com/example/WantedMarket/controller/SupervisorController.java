package com.example.WantedMarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SupervisorController {
    @GetMapping("/supervisor")
    public String supervisorPage() {

        return "supervisor";
    }
}
