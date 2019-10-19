package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.persistence.dao.ColorDAO;
import be.iepscf.refuge.persistence.entitybean.Color;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcColorDAO extends JdbcGenericDAO<Color, Long> implements ColorDAO {

    private static final String SELECT = "SELECT id, name FROM colors";
    private static final String FIND_BY_ID = SELECT + " WHERE id=?";
    private static final String FIND_BY_NAME = SELECT + " WHERE name=?";
    private static final String FIND_ALL = SELECT + " ORDER BY name";

    public Color fetch(ResultSet resultSet) throws SQLException {
        Color color = new Color();
        color.setId(resultSet.getLong("id"));
        color.setName(resultSet.getString("name"));
        return color;
    }


    @Override
    public Color find(Long id){
        try {
            Connection connection = getConnection();
            String SQL = FIND_BY_ID;
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Color color = fetch(resultSet);
                return color;
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    };

    @Override
    public List<Color> findAll() {
        List<Color> colors = new ArrayList<Color>();
        try {
            Connection connection = getConnection();
            String SQL = FIND_ALL;
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Color color = fetch(resultSet);
                if (color != null) {
                    colors.add(color);
                }
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colors;
    };

    @Override
    public Color findByName(String name) {
        try {
            Connection connection = getConnection();
            String SQL = FIND_BY_NAME;
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Color color = fetch(resultSet);
                return color;
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}