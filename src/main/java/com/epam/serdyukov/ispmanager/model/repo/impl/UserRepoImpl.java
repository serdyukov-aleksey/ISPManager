package com.epam.serdyukov.ispmanager.model.repo.impl;
import com.epam.serdyukov.ispmanager.model.dao.builders.TariffQueryBuilder;
import com.epam.serdyukov.ispmanager.model.dao.builders.UserQueryBuilder;
import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.pool.DBManager;
import com.epam.serdyukov.ispmanager.model.dao.builders.QueryBuilder;
import com.epam.serdyukov.ispmanager.model.repo.IUserRepo;

import java.util.List;

public class UserRepoImpl implements IUserRepo {
    private static final String GET_ALL = "SELECT id, login, password, role_id, user_details_id, is_blocked, account_id FROM users";
    private static final String GET_BY_ID = "SELECT id, login, password, role_id, user_details_id, is_blocked, account_id FROM users WHERE id = ?";
    private static final String GET_BY_LOGIN = "SELECT id, login, password, role_id, user_details_id, is_blocked, account_id FROM users WHERE login = ?";
    private static final String CREATE = "INSERT INTO users (login, password, role_id, user_details_id, is_blocked, account_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE users SET login = ?, password = ?, role_id = ?, user_details_id=?, is_blocked=?, account_id=? WHERE id = ?";
    private static final String DELETE = "DELETE FROM users WHERE id = ?";

    private static final String ADD_LINK_USERS_HAS_TRAFFICS = "INSERT INTO users_has_tariffs (users_id, tariffs_id) VALUES (?, ?)";
    private static final String GET_LINK_USERS_HAS_TRAFFICS = "SELECT t.id, t.name, t.description, t.price, t.services_id FROM tariffs AS t JOIN users_has_tariffs AS uht ON t.id = uht.tariffs_id AND uht.users_id = (SELECT id FROM users WHERE id = ?)";
    private static final String DELETE_LINK_USERS_HAS_TRAFFICS = "DELETE FROM users_has_tariffs WHERE users_id = ?";

    private static final String GET_NEXT_AUTO_INCREMENT = "SELECT max(id)+1 FROM users";

    private DBManager instance = DBManager.getInstance();
    private QueryBuilder queryBuilder = new UserQueryBuilder();



    @Override
    public List<User> getAll() {
        return queryBuilder.executeAndReturnList(instance, GET_ALL);
    }

    @Override
    public User getById(long id) {
        return (User) queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
    }

    @Override
    public void create(User user) {
        long id = queryBuilder.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT);
        queryBuilder.execute(instance, CREATE, user.getLogin(), user.getPassword(), user.getRoleId(),
            id, user.isBlocked(), user.getAccount().getId());
    }

    @Override
    public void update(User user) {
        queryBuilder.execute(instance, UPDATE, user.getLogin(), user.getPassword(), user.getDetails().getFirstName(),
            user.getDetails().getLastName(), user.isBlocked(), user.getRoleId(), user.getId());
    }

    @Override
    public void delete(long id) {
        queryBuilder.execute(instance, DELETE, id);
    }

    @Override
    public User getByLogin(String login) {
        return (User) queryBuilder.executeAndReturn(instance, GET_BY_LOGIN, login);
    }

    @Override
    public List<Tariff> getTariffs(User user) {
        QueryBuilder queryBuilder = new TariffQueryBuilder();
        return queryBuilder.executeAndReturnList(instance, GET_LINK_USERS_HAS_TRAFFICS, user.getId());
    }

    @Override
    public void addLinksUsersHasTariffs(User user, String[] tariffsId) {
        User tmp = getByLogin(user.getLogin());
        QueryBuilder queryBuilder = new TariffQueryBuilder();
        for (String id : tariffsId) {
            queryBuilder.execute(instance, ADD_LINK_USERS_HAS_TRAFFICS, tmp.getId(), id);
        }
    }

    @Override
    public void deleteLinksUsersHasTariffs(User user) {
        queryBuilder.execute(instance, DELETE_LINK_USERS_HAS_TRAFFICS, user.getId());
    }
}
