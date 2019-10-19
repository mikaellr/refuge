package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.persistence.entitybean.Role;
import be.iepscf.refuge.persistence.entitybean.User;
import be.iepscf.refuge.persistence.dao.UserDAO;

import java.sql.*;
import java.util.*;

public class JdbcUserDAO extends JdbcGenericDAO<User, Long> implements UserDAO {

    //Requêtes préparées :

    // De recherche
    private static final String SELECT = "SELECT us.id AS id, first_name, last_name, email, phone, hash, salt, active, ro.id AS role_id, ro.name AS role_name, ro.description AS role_description FROM users AS us LEFT OUTER JOIN roles AS ro ON us.fk_role=ro.id";
    private static final String FIND_BY_ID = SELECT + " WHERE us.id=?";
    private static final String FIND_BY_EMAIL = SELECT + " WHERE us.email=?";
    private static final String FIND_ALL = SELECT + " ORDER BY last_name, first_name";

    // De Creation/Update/Obsolescence :
    private static final String INSERT = "INSERT INTO users " +
            "(first_name, last_name, email, phone, hash, salt, `active`, fk_role) " +
            "VALUES (?,?,?,?,?,?,?,?);";

    private static final String UPDATE = "UPDATE `users` SET " +
            "`first_name` = ?, " +
            "`last_name` = ?, " +
            "`email` = ?, " +
            "`phone` = ?, " +
            "`hash` = ?, " +
            "`salt` = ?, " +
            "`active` = ?, " +
            "`fk_role` = ? " +
            "WHERE `id` = ?;";

    private static final String DISABLE = "UPDATE users SET active = false WHERE id =?";



    @Override
    public long save(User user){
        long affectedRows = -1;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            settingPreparedStatement(user, preparedStatement);
            affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Long lastInsertId = generatedKeys.getLong(1);
                        user.setId(lastInsertId);
                        affectedRows = lastInsertId;
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    @Override
    public long update(User user) {
        long affectedRows = -1;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            settingPreparedStatement(user, preparedStatement);
            preparedStatement.setLong(9, user.getId());
            affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                //response = true ;
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    private void settingPreparedStatement(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPhone());
        preparedStatement.setString(5, user.getHash());
        preparedStatement.setString(6, user.getSalt());
        preparedStatement.setBoolean(7, user.isActive());
        preparedStatement.setLong(8, user.getRole().getId());
    }

    @Override
    public long delete(User user) {
        int affectedRows = -1;
        String sql = String.format("DELETE FROM %s WHERE %s=?", "users", "id");
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, user.getId());
            affectedRows = preparedStatement.executeUpdate();
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }


    /**
     * (pas forcément obligatoire dans le DAO, il peut suffire de la méthode update sur l'objet User dans son ensemble)
     *
     * @param id
     */
    public long disableUser(int id) {
        int affectedRows = -1;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DISABLE);
            preparedStatement.setInt(1, id );
            affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                //response = true ;
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }



    public User fetch(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(Long.parseLong(resultSet.getString("id")));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPhone(resultSet.getString("phone"));
        user.setHash(resultSet.getString("hash"));
        user.setSalt(resultSet.getString("salt"));
        user.setActive(resultSet.getBoolean("active"));
        Role role = new Role();
        role.setId(resultSet.getLong("role_id"));
        role.setName(resultSet.getString("role_name"));
        role.setDescription(resultSet.getString("role_description"));
        user.setRole(role);
        return user;
    }


    @Override
    public User find(Long id){
        User user = null;
        try {
            Connection connection = getConnection();
            String SQL = FIND_BY_ID;
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = fetch(resultSet);
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    };

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        try {
            Connection connection = getConnection();
            String SQL = SELECT + " ORDER BY last_name, first_name";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = fetch(resultSet);
                users.add(user);
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    };


    @Override
    public User findByEmail(String email) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return fetch(resultSet);
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}