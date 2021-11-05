package com.example.task.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CandidateMapper implements RowMapper<Candidates> {

    @Override
    public Candidates mapRow(ResultSet rs, int rowNum) throws SQLException {
        Candidates candidates=new Candidates();
        candidates.setFirstName(rs.getString("first_name"));
        candidates.setFirstName(rs.getString("last_name"));
        candidates.setFirstName(rs.getString("company_name"));
        return candidates;
    }
}
