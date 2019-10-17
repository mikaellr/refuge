package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.persistence.entitybean.Animal;
import be.iepscf.refuge.persistence.dao.AnimalDAO;
import be.iepscf.refuge.persistence.entitybean.Species;

import java.sql.*;
import java.util.List;

public class JdbcAnimalDAO extends JdbcGenericDAO<Animal, Long> implements AnimalDAO {


    //Requêtes préparées :

    // De recherche
    private static final String SELECT = "SELECT animal.id AS id, animal.name, description, birth_year, sex, sterilized, adoptable, photo_content," +
            "sp.name AS specy_name, ra.name AS race_name" +
            "FROM animals AS animal " +
            "LEFT OUTER JOIN species AS sp ON animal.fk_species = sp.id" +
            "LEFT OUTER JOIN races AS ra ON animal.fk_race = ra.id";
    private static final String FIND_BY_SPECY = SELECT + " WHERE fk_specy=?";// OK
    private static final String FIND_ALL = SELECT + " ORDER BY id DESC";// OK

    // De Creation/Update/Obsolescence :
    private static final String INSERT = "INSERT INTO animals " +
            "(name, description, birth_year, sex, sterilized, adoptable, photo_content_type, photo_content_length,photo_content, fk_species, fk_race, fk_color) " +
            "VALUES (?,?,?,?,?,true,?,?,?,?,?,?);";

    private static final String UPDATE = "UPDATE `animals` SET " +
            "`name` = ?, " +
            "`description` = ?, " +
            "`birth_year` = ?, " +
            "`sex` = ?, " +
            "`sterilized` = ?, " +
            "`adoptable` = ?, " +
            "`photo_content_type` = ? " +
            "`photo_content_length` = ? " +
            "`photo_content` = ? " +
            "`fk_species` = ?, " +
            "`fk_race` = ? " +
            "`fk_color` = ? " +
            "WHERE `id` = ?;";

    private static final String DISABLE = "UPDATE animals SET adoptable = false WHERE id =?";

    @Override
    public long save(Animal animal){
        long affectedRows = -1;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            settingPreparedStatement(animal, preparedStatement);
            affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Long lastInsertId = generatedKeys.getLong(1);
                        animal.setId(lastInsertId);
                        affectedRows = lastInsertId;
                    } else {
                        throw new SQLException("Creating animal failed, no ID obtained.");
                    }
                }
            }
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }
    @Override
    public long update(Animal animal){}
    @Override
    public long delete(Animal animal){}

    private void settingPreparedStatement(Animal animal, PreparedStatement preparedStatement) throws SQLException {
        // Similaire à User : attention aux Type Race Et Species!!
    }

    public long disableAnimal(Animal animal){}

    public Animal fetch(ResultSet resultSet)throws SQLException{}

    @Override
    public Animal find(Long id) {}

    @Override
    public List<Animal> findAll() {}

    @Override
    public List<Animal> findBySpecy(Species specy) {}


}