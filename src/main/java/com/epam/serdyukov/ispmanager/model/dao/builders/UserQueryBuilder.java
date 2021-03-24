package com.epam.serdyukov.ispmanager.model.dao.builders;


import com.epam.serdyukov.ispmanager.model.entity.Account;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.repo.impl.UserDetailsRepoImpl;
import com.epam.serdyukov.ispmanager.model.repo.IUserDetailsRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserQueryBuilder extends QueryBuilder<User> {

    private IUserDetailsRepo contactDetails = new UserDetailsRepoImpl();

    public List<User> getListOfResult(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            setUser(rs, user);
            users.add(user);
        }
        return users;
    }

    @Override
    public User getResult(ResultSet rs) throws SQLException {
        User user = new User();
        while (rs.next()) {
            setUser(rs, user);
        }
        return user;
    }

    private void setUser(ResultSet rs, User user) throws SQLException {
        user.setId(rs.getLong("id"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setBlocked(rs.getBoolean("is_blocked"));
        user.setRoleId(rs.getInt("role_id"));
        user.setDetails(contactDetails.getById(rs.getLong("user_details_id")));
        user.setAccount(new Account(rs.getLong("account_id")));
    }

}