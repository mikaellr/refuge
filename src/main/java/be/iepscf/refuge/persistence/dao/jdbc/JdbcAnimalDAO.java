package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.persistence.entitybean.Animal;
import be.iepscf.refuge.persistence.dao.AnimalDAO;
import be.iepscf.refuge.persistence.entitybean.Color;
import be.iepscf.refuge.persistence.entitybean.Race;
import be.iepscf.refuge.persistence.entitybean.Species;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcAnimalDAO extends JdbcGenericDAO<Animal, Long> implements AnimalDAO {


    //Requêtes préparées :

    // De recherche
/*    private static final String SELECT = "SELECT animals.id AS id, animals.name, description, birth_year, sex, sterilized, adoptable, photo_content," +
            "species.id AS species_id, species.name AS species_name, " +
            "races.id AS race_id, races.name AS race_name," +
            "colors.id AS color_id, colors.name AS color_name" +
            "FROM animals " +
            "LEFT OUTER JOIN species AS sp ON animal.fk_species = sp.id " +
            "LEFT OUTER JOIN races AS ra ON animals.fk_races = ra.id " +
            "LEFT OUTER JOIN colors AS co ON animals.fk_color = co.id ";*/
    private static final String SELECT = "SELECT animals.id AS id, animals.name, description, birth_year, sex, sterilized, adoptable, photo_content,species.id AS species_id, species.name AS species_name,races.id AS race_id, races.name AS race_name, colors.id, colors.name FROM animals LEFT JOIN species ON animals.fk_species = species.id LEFT JOIN races ON animals.fk_race = races.id LEFT JOIN colors ON animals.fk_color = colors.id";
    private static final String FIND_BY_ID = SELECT + " WHERE animals.id = ?";
    private static final String FIND_BY_SPECIES = SELECT + " WHERE animals.fk_species=?";// OK
    private static final String FIND_BY_RACE = SELECT + " WHERE fk_race=?";// OK
    private static final String FIND_IF_NOT_ADOPTED = SELECT + " WHERE adoptable = true";// OK
    private static final String FIND_ALL = SELECT + " ORDER BY animals.id DESC";// OK


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
            "`photo_content_type` = ?, " +
            "`photo_content_length` = ?, " +
            "`photo_content` = ?, " +
            "`fk_species` = ?, " +
            "`fk_race` = ?, " +
            "`fk_color` = ? " +
            "WHERE `id` = ?";

    private static final String DISABLE = "UPDATE animals SET adoptable = false WHERE id =?";

    private static final String DELETE = "DELETE FROM animals WHERE id =?";

    @Override
    public long save(Animal animal){
        long affectedRows = -1;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setString(2, animal.getDescription());
            preparedStatement.setInt(3, animal.getBirthYear());
            preparedStatement.setString(4, Character.toString(animal.getSex()));
            preparedStatement.setBoolean(5, animal.isSterilized());
            //preparedStatement.setBoolean(6, animal.isAdoptable());
            preparedStatement.setString(6, animal.getPhotoContentType());
            preparedStatement.setInt(7, animal.getPhotoContentLength());
            preparedStatement.setBytes(8, animal.getPhoto());
            if(animal.getSpecies() == null ){
                Long nullLong = null;
                preparedStatement.setLong(9, nullLong);
            }else{
                preparedStatement.setLong(9, animal.getSpecies().getId());
            }
            if(animal.getRace() == null ){
                Long nullLong = null;
                preparedStatement.setLong(10, nullLong);
            }else{
                preparedStatement.setLong(10, animal.getRace().getId());
            }
            preparedStatement.setLong(11, animal.getColor().getId());
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
    public long update(Animal animal){
        long affectedRows = -1;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setString(2, animal.getDescription());
            preparedStatement.setInt(3, animal.getBirthYear());
            preparedStatement.setString(4, Character.toString(animal.getSex()));
            preparedStatement.setBoolean(5, animal.isSterilized());
            preparedStatement.setBoolean(6, animal.isAdoptable());
            preparedStatement.setString(7, animal.getPhotoContentType());
            preparedStatement.setInt(8, animal.getPhotoContentLength());
            preparedStatement.setBytes(9, animal.getPhoto());
            preparedStatement.setLong(10, animal.getSpecies().getId());
            preparedStatement.setLong(11, animal.getRace().getId());
            preparedStatement.setLong(12, animal.getColor().getId());
            affectedRows = preparedStatement.executeUpdate();
            //if (affectedRows > 0) {
            //}
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }
    // A ne pas utiliser!! Voir disableAnimal(id)
    @Override
    public long delete(Animal animal){
        int affectedRows = -1;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setLong(1, animal.getId());
            affectedRows = preparedStatement.executeUpdate();
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    public long disableAnimal(Long id){
        int affectedRows = -1;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DISABLE);
            preparedStatement.setLong(1, id);
            affectedRows = preparedStatement.executeUpdate();
            //if (affectedRows > 0) {
            //response = true ;
            //}
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    public Animal fetch(ResultSet resultSet)throws SQLException{
        Animal animal = new Animal();
        animal.setId(Long.parseLong(resultSet.getString("id")));
        animal.setName(resultSet.getString("name"));
        animal.setDescription(resultSet.getString("description"));
        animal.setBirthYear(resultSet.getInt("birth_year"));
        animal.setSex((resultSet.getString("sex")).charAt(0));
        animal.setSterilized(resultSet.getBoolean("sterilized"));
        animal.setAdoptable(resultSet.getBoolean("adoptable"));
        animal.setPhotoContentType(resultSet.getString("photo_content_type"));
        animal.setPhotoContentLength(resultSet.getInt("photo_content_length"));
        animal.setPhoto(resultSet.getBytes("photo_content"));
        Species species = new Species();
        species.setId(resultSet.getLong("species_id"));
        species.setName(resultSet.getString("species_name"));
        animal.setSpecies(species);
        Race race = new Race();
        race.setId(resultSet.getLong("race_id"));
        race.setName(resultSet.getString("race_name"));
        animal.setRace(race);
        Color color = new Color();
        color.setId(resultSet.getLong("color_id"));
        color.setName(resultSet.getString("color_name"));
        animal.setColor(color);

        return animal;
    }

    @Override
    public Animal find(Long id) {
        Animal animal = null;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                animal = fetch(resultSet);
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animal;
    }

    @Override
    public List<Animal> findAll() {
        List<Animal> animals = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Animal animal = fetch(resultSet);
                animals.add(animal);
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    @Override
    public List<Animal> findBySpecies(Species species) {
        List<Animal> animals = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_SPECIES);
            preparedStatement.setLong(1, species.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Animal animal = fetch(resultSet);
                animals.add(animal);
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    @Override
    public List<Animal> findByRace(Race race) {
        List<Animal> animals = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_RACE);
            preparedStatement.setLong(1, race.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Animal animal = fetch(resultSet);
                animals.add(animal);
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    @Override
    public List<Animal> findAllAdoptable() {
        List<Animal> animals = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_IF_NOT_ADOPTED);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Animal animal = fetch(resultSet);
                animals.add(animal);
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }


}