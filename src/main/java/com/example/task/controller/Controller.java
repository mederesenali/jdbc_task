package com.example.task.controller;

import com.example.task.model.Candidates;
import com.example.task.model.UsersWithDep;
import com.example.task.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class Controller {

    @Autowired
    Service service;

    @GetMapping("/usersWithDep")
    public List<UsersWithDep> getUsersWithDep() {
        log.info("Request for getting all users with department");
        return service.getAllUsers();
    }

    @GetMapping("/candidates/{company}")
    public List<Candidates> getCandidates(@PathVariable String company) {
        if (company != null) {
            return service.getAllCandidates();
        } else {
            return service.getCandidatesByCompany(company);
        }

    }

}
