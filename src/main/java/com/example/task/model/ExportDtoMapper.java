package com.example.task.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExportDtoMapper implements RowMapper<ExportDto> {
    @Override
    public ExportDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExportDto exportDto=new ExportDto();
        exportDto.setCompanyId(rs.getInt("company_id"));
        exportDto.setCompanyName(rs.getString("company_name"));
        exportDto.setCandidateLastName(rs.getString("last_name"));
        exportDto.setJobTitle(rs.getString("jobpost_title"));
        return exportDto;
    }
}
