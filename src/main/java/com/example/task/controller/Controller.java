package com.example.task.controller;

import com.example.task.model.Candidates;
import com.example.task.model.ExportDto;
import com.example.task.model.UsersWithDep;
import com.example.task.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("/candidates/{company}/{keyword}/{limit}/{page}")
    public List<Candidates> getCandidates(@PathVariable String company,@PathVariable String keyword,
    @PathVariable int limit,@PathVariable int page) {
        if (company != null) {
            return service.getAllCandidates();
        } else {
            return service.getCandidatesByCompany(company,keyword,limit,page);
        }

    }

    @GetMapping("/users/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<ExportDto> dataToExport = service.getDataToExport();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"company id", "company name", "JOB TITLE","CANDIDATE LAST NAME"};
        String[] nameMapping = {"companyId", "companyName", "jobTitle","candidateLastName"};

        csvWriter.writeHeader(csvHeader);

        for (ExportDto user : dataToExport) {
            csvWriter.write(user, nameMapping);
        }

        csvWriter.close();

    }

}
