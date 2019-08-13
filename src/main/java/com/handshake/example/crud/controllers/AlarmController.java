package com.handshake.example.crud.controllers;

import com.handshake.example.crud.models.Alarm;
import com.handshake.example.crud.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AlarmController {

    public void setAlarmService(AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @Autowired
    AlarmService alarmService;

    @GetMapping("/index")
    public String getAllRecords(Model model) {
        List<Alarm> alarms = alarmService.findAll();
        model.addAttribute("alarms", alarms);
        // return template name
        return "main";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Alarm> addAlarm(@RequestBody Alarm alarm) {
        alarmService.save(alarm);
        return new ResponseEntity<>(alarm, HttpStatus.CREATED);
    }

}
