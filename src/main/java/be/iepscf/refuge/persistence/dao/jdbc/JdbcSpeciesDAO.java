package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.persistence.entitybean.Species;
import be.iepscf.refuge.persistence.dao.SpeciesDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcSpeciesDAO extends JdbcGenericDAO<Species, Long> implements SpeciesDAO {

    //Requêtes préparées :

    // De recherche
    private static final String SELECT = "SELECT id, name FROM species";
    private static final String FIND_BY_ID = SELECT + " WHERE id=?";
    private static final String FIND_BY_NAME = SELECT + " WHERE name=?";
    private static final String FIND_ALL = SELECT + " ORDER BY name";

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
    public long save(Species item){
        long affectedRows = -1;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, item.getName());
            affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Long lastInsertId = generatedKeys.getLong(1);
                        item.setId(lastInsertId);
                        affectedRows = lastInsertId;
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    @Override
    public long update(Species item) {
        long affectedRows = -1;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setLong(2, item.getId());
            affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                //response = true ;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }



    public Species fetch(ResultSet resultSet) throws SQLException {
        Species item = new Species();
        item.setId(Long.parseLong(resultSet.getString("id")));
        item.setName(resultSet.getString("name"));
        return item;
    }


    @Override
    public Species find(Long id){
        Species item = null;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                item = fetch(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    };

    @Override
    public List<Species> findAll() {
        List<Species> items = new ArrayList<Species>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Species item = fetch(resultSet);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    };


    @Override
    public Species findByName(String name) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return fetch(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public long delete(Species item) {
        int affectedRows = -1;
        String sql = String.format("DELETE FROM %s WHERE %s=?", "species", "id");
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, item.getId());
            affectedRows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

}