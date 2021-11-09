package com.example.task.service;

import com.example.task.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.supercsv.cellprocessor.constraint.IsElementOf;

import java.util.List;

@org.springframework.stereotype.Service
@Slf4j
public class Service {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<UsersWithDep> getAllUsers() {

        var query = "select user_first_name,user_last_name,cd.department_name from  \"user\"\n" +
                "inner join user_myaccount um on \"user\".user_id = um.user_id\n" +
                "inner join company_department cd on um.company_id = cd.company_id";

        return jdbcTemplate.query(query, new UserMapper());

    }

    public List<Candidates> getAllCandidates(){
        var query="select ccd.first_name,ccd.last_name,c.company_name from company_jobposting_candidate ccd\n" +
                "inner join company_jobposting cj on ccd.company_jobposting_id = cj.company_jobposting_id\n" +
                "inner join company c on cj.company_id = c.company_id order by first_name asc ";
        return jdbcTemplate.query(query, new CandidateMapper());

    }

    public  List<Candidates> getCandidatesByCompany(String companyName,String keyword,int limit,int page) {



        var query="select  ccd.first_name,ccd.last_name,c.company_name from company_jobposting_candidate ccd\n" +
                "inner join company_jobposting cj on ccd.company_jobposting_id = cj.company_jobposting_id\n" +
                "inner join company c on cj.company_id = c.company_id\n" +
                "where company_name=?   order by first_name asc limit ? ";

        return jdbcTemplate.query(query,new Object[]{companyName,limit},
                BeanPropertyRowMapper.newInstance(Candidates.class));


    }

    public List<ExportDto> getDataToExport(){

        var query="select cj.company_id, company_name,cj.jobpost_title,cjc.last_name from  company\n" +
                "inner join company_jobposting cj on company.company_id = cj.company_id\n" +
                "inner join company_jobposting_candidate cjc on cj.company_jobposting_id = cjc.company_jobposting_id";

       return jdbcTemplate.query(query,new ExportDtoMapper());
    }
}
