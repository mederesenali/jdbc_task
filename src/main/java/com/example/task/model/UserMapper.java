package com.example.task.model;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UsersWithDep> {
    @Override
    public UsersWithDep mapRow(ResultSet rs, int rowNum) throws SQLException {
        UsersWithDep usersWithDep=new UsersWithDep();
        usersWithDep.setUserFirstName(rs.getString("user_first_name"));
        usersWithDep.setUserFirstName(rs.getString("user_last_name"));
        usersWithDep.setUserFirstName(rs.getString("department_name"));
        return usersWithDep;
    }
}
