package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.business.util.Logger;
import be.iepscf.refuge.persistence.entitybean.Animal;
import be.iepscf.refuge.persistence.dao.AnimalDAO;
import be.iepscf.refuge.persistence.entitybean.Color;
import be.iepscf.refuge.persistence.entitybean.Race;
import be.iepscf.refuge.persistence.entitybean.Species;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcAnimalDAO extends JdbcGenericDAO<Animal, Long> implements AnimalDAO {

    // De recherche
/*    private static final String SELECT = "SELECT animals.id AS id, animals.name, description, birth_year, sex, sterilized, adoptable, photo_content," +
            "species.id AS species_id, species.name AS species_name, " +
            "races.id AS race_id, races.name AS race_name," +
            "colors.id AS color_id, colors.name AS color_name" +
            "FROM animals " +
            "LEFT OUTER JOIN species AS sp ON animal.fk_species = sp.id " +
            "LEFT OUTER JOIN races AS ra ON animals.fk_races = ra.id " +
            "LEFT OUTER JOIN colors AS co ON animals.fk_color = co.id ";*/

    private static final String SELECT = "SELECT animals.id AS id, animals.name, description, birth_year, sex, sterilized, adoptable, photo_content, photo_content_length, photo_content_type, species.id AS species_id, species.name AS species_name,races.id AS race_id, races.name AS race_name, colors.id AS color_id, colors.name AS color_name FROM animals LEFT JOIN species ON animals.fk_species = species.id LEFT JOIN races ON animals.fk_race = races.id LEFT JOIN colors ON animals.fk_color = colors.id";

    // l'animal, qu'il soit adoptable ou non :
    private static final String FIND_BY_ID = SELECT + " WHERE animals.id = ?";

    // uniquement utiles sur les adoptables :
    private static final String FIND_BY_SPECIES = SELECT + " WHERE adoptable IS TRUE AND animals.fk_species=?";// OK
    private static final String FIND_BY_RACE = SELECT + " WHERE adoptable IS TRUE AND fk_race=?";// OK
    private static final String FIND_ALL = SELECT + " WHERE adoptable IS TRUE ORDER BY animals.id DESC";// OK
    //private static final String FIND_MULTIPLE_PARAMETERS = SELECT + " WHERE animals.fk_species = ? AND animals.fk_race = ? AND adoptable = ? ORDER BY animals.id [?] LIMIT ? OFFSET ?";

    // tous les non adoptables, uniquement pour consultation de l'historique :
    private static final String FIND_NON_ADOPTABLE = SELECT + " WHERE adoptable IS FALSE ORDER BY id DESC";// OK


    // De Creation/Update/Obsolescence :
    private static final String INSERT = "INSERT INTO animals " +
            "(name, description, birth_year, sex, sterilized, adoptable, photo_content_type, photo_content_length,photo_content, fk_species, fk_race, fk_color) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";

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
            preparedStatement.setBoolean(6, animal.isAdoptable());
            preparedStatement.setString(7, animal.getPhotoContentType());
            preparedStatement.setInt(8, animal.getPhotoContentLength());
            preparedStatement.setBytes(9, animal.getPhoto());
            if (animal.getSpecies() == null ) {
                Long nullLong = null;
                preparedStatement.setLong(10, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setLong(10, animal.getSpecies().getId());
            }
            if (animal.getRace() == null ) {
                Long nullLong = null;
                preparedStatement.setNull(11, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setLong(11, animal.getRace().getId());
            }
            if (animal.getColor() == null) {
                preparedStatement.setNull(12, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setLong(12, animal.getColor().getId());
            }
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
            if (animal.getSpecies() == null) {
                preparedStatement.setNull(10, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setLong(10, animal.getSpecies().getId());
            }
            if (animal.getRace() == null) {
                preparedStatement.setLong(11, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setLong(11, animal.getRace().getId());
            }
            if (animal.getColor() == null) {
                preparedStatement.setLong(12, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setLong(12, animal.getColor().getId());
            }
            preparedStatement.setLong(13, animal.getId());
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
        Long speciesId = resultSet.getLong("species_id");
        if (! resultSet.wasNull()) {
            Species species = new Species();
            species.setId(speciesId);
            species.setName(resultSet.getString("species_name"));
            animal.setSpecies(species);
        }
        Long raceId = resultSet.getLong("race_id");
        if (! resultSet.wasNull()) {
            Race race = new Race();
            race.setId(raceId);
            race.setName(resultSet.getString("race_name"));
            animal.setRace(race);
        }
        Long colorId = resultSet.getLong("color_id");
        if (! resultSet.wasNull()) {
            Color color = new Color();
            color.setId(colorId);
            color.setName(resultSet.getString("color_name"));
            animal.setColor(color);
        }
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
            while (resultSet.next()) {
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
        List<Animal> animals = new ArrayList<Animal>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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
    public List<Animal> findBySpecies(Long id) {
        List<Animal> animals = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_SPECIES);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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
    public List<Animal> findByRace(Long id) {
        List<Animal> animals = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_RACE);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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
    public List<Animal> findNonAdoptable() {
        List<Animal> animals = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_NON_ADOPTABLE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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
    public List<Animal> findMultiParameters(Long species, Long race, Boolean adoptable, Boolean all, Boolean last, Long limit, Long offset) {
        if (last == null) last = true;
        //if (adoptable == null) adoptable = true;
        if (all == null) all = false;
        if (limit == null || limit < 0) limit = 20L;
        if (offset == null || offset < 0) offset = 0L;

        String whereClause = " WHERE 1 ";
        if (adoptable != null) {
            whereClause += adoptable ? " AND adoptable IS TRUE " : " AND adoptable IS FALSE ";
        }
        if (species != null && species > 0) {
            whereClause += String.format(" AND animals.fk_species=%d", species);
        }
        if (race != null && race > 0) {
            whereClause += String.format(" AND animals.fk_race=%d", race);
        }

        String orderClause = last ? " ORDER BY id DESC" : " ORDER BY id ASC";
        String limitClause = all ? "" : String.format(" LIMIT %d, %d", offset, limit);

        String fullWhereClause = whereClause + orderClause + limitClause;
        String sql = SELECT + fullWhereClause;

        Logger.getLogger().debug("JdbcAnimalDAO findQuery : fullWhereClause = " + fullWhereClause);
        Logger.getLogger().debug("JdbcAnimalDAO findQuery : sql = " + sql);


        List<Animal> animals = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

//            PreparedStatement preparedStatement = connection.prepareStatement(FIND_MULTIPLE_PARAMETERS);
//            preparedStatement.setLong(1, species);
//            preparedStatement.setLong(2, race);
//            preparedStatement.setBoolean(3, adoptable);
//            if (last){
//                String descendant = "DESC";
//                preparedStatement.setString(4, descendant);
//            }
//            else{
//                String ascendant = "ASC";
//                preparedStatement.setString(4, ascendant);
//            }
//            if(all){
//                String tout = "ALL";
//                preparedStatement.setString(5, tout);
//                preparedStatement.setLong(6, 0);
//            }
//            else{
//                if(offset != null && limit != null){
//                    preparedStatement.setLong(5, limit);
//                    preparedStatement.setLong(6, offset);
//                }
//                else if(offset == null && limit != null){
//                    preparedStatement.setLong(5, limit);
//                    preparedStatement.setLong(6, 0);
//                }
//                else if(offset != null && limit == null){
//                    preparedStatement.setLong(5, 20);
//                    preparedStatement.setLong(6, offset);
//                }
//                else {
//                    preparedStatement.setLong(5, 0);
//                    preparedStatement.setLong(6, 20);
//                }
//            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Animal animal = fetch(resultSet);
                animals.add(animal);
            }
            // En dessous OK
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }


}