package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.persistence.dao.RoleDAO;
import be.iepscf.refuge.persistence.entitybean.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcRoleDAO extends JdbcGenericDAO<Role, Long> implements RoleDAO {

    private static final String SELECT = "SELECT id, name, description FROM roles";
    private static final String FIND_BY_ID = SELECT + " WHERE id=?";
    private static final String FIND_ALL = SELECT + " ORDER BY name";

    public Role fetch(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getLong("id"));
        role.setName(resultSet.getString("name"));
        role.setDescription(resultSet.getString("description"));
        return role;
    }


    @Override
    public Role find(Long id){
        try {
            Connection connection = getConnection();
            String SQL = FIND_BY_ID;
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Role role = fetch(resultSet);
                return role;
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    };

    @Override
    public List<Role> findAll() {
        List<Role> Roles = new ArrayList<Role>();
        try {
            Connection connection = getConnection();
            String SQL = FIND_ALL;
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = fetch(resultSet);
                if (role != null) {
                    Roles.add(role);
                }
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Roles;
    };

}