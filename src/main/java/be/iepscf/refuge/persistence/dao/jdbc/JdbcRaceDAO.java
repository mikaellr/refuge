package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.persistence.dao.RaceDAO;
import be.iepscf.refuge.persistence.entitybean.Race;
import be.iepscf.refuge.persistence.entitybean.Species;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcRaceDAO extends JdbcGenericDAO<Race, Long> implements RaceDAO {

    private static final String SELECT = "SELECT ra.id, ra.name, fk_species, sp.name AS species_name FROM races AS ra LEFT OUTER JOIN species AS sp ON ra.fk_species=sp.id";
    private static final String FIND_BY_ID = SELECT + " WHERE ra.id=?";
    private static final String FIND_ALL = SELECT + " ORDER BY fk_species, name";

    public Race fetch(ResultSet resultSet) throws SQLException {
        Race race = new Race();
        race.setId(resultSet.getLong("id"));
        race.setName(resultSet.getString("name"));
        Species species = new Species();
        species.setId(resultSet.getLong("fk_species"));
        species.setName(resultSet.getString("species_name"));
        race.setSpecies(species);
        return race;
    }


    @Override
    public Race find(Long id){
        try {
            Connection connection = getConnection();
            String SQL = FIND_BY_ID;
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Race Race = fetch(resultSet);
                return Race;
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    };

    @Override
    public List<Race> findAll() {
        List<Race> Races = new ArrayList<Race>();
        try {
            Connection connection = getConnection();
            String SQL = FIND_ALL;
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Race Race = fetch(resultSet);
                if (Race != null) {
                    Races.add(Race);
                }
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Races;
    };

}