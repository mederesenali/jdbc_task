package com.example.task.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
@Slf4j
public class Shelduler {
    @Autowired
    com.example.task.service.Service service;

    @Scheduled(fixedDelay = 10000)
    public void getUsers(){
        log.info("Export Data"+service.getDataToExport());
       // log.info("All users"+service.getAllUsers().toString());
       // log.info("The Hire Talent TESTING -candidates"+service.getCandidatesByCompany("CMM Construction","test",10,1).toString());

    }
}
