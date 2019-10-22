package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.persistence.dao.ContactRequestDAO;
import be.iepscf.refuge.persistence.entitybean.Animal;
import be.iepscf.refuge.persistence.entitybean.ContactRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JdbcContactRequestDAO extends JdbcGenericDAO<ContactRequest, Long> implements ContactRequestDAO {

    private static final String SELECT = "SELECT cr.id AS id, first_name, last_name, email, phone, message, date, treated, fk_animal, an.name, an.description, an.birth_year, sex, an.sterilized, an.adoptable FROM contact_requests AS cr LEFT OUTER JOIN animals AS an ON cr.fk_animal=an.id";
    private static final String FIND_BY_ID = SELECT + " WHERE cr.id=?";
    private static final String FIND_BY_ANIMAL = SELECT + " WHERE fk_animal=? ORDER BY id DESC";
    private static final String FIND_ALL = SELECT + " ORDER BY id DESC";

    // De Creation/Update/Obsolescence :
    private static final String INSERT = "INSERT INTO contact_requests " +
            "(first_name, last_name, email, phone, message, date, treated, fk_animal) " +
            "VALUES (?,?,?,?,?,?,?,?);";

    private static final String UPDATE = "UPDATE contact_requests SET " +
            "`first_name` = ?, " +
            "`last_name` = ?, " +
            "`email` = ?, " +
            "`phone` = ?, " +
            "`message` = ?, " +
            "`date` = ?, " +
            "`treated` = ?, " +
            "`fk_animal` = ? " +
            "WHERE `id` = ?;";




    @Override
    public long save(ContactRequest user){
        long affectedRows = -1;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getMessage());
            preparedStatement.setTimestamp(6, new Timestamp(user.getDate().getTime()));
            preparedStatement.setBoolean(7, user.isTreated());
            preparedStatement.setLong(8, user.getAnimal().getId());
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
    public long update(ContactRequest user) {
        long affectedRows = -1;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getMessage());
            preparedStatement.setTimestamp(6, new Timestamp(user.getDate().getTime()));
            preparedStatement.setBoolean(7, user.isTreated());
            preparedStatement.setLong(8, user.getAnimal().getId());
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




    public ContactRequest fetch(ResultSet resultSet) throws SQLException {
        ContactRequest item = new ContactRequest();
        item.setId(resultSet.getLong("id"));
        item.setFirstName(resultSet.getString("first_name"));
        item.setLastName(resultSet.getString("last_name"));
        item.setEmail(resultSet.getString("email"));
        item.setPhone(resultSet.getString("phone"));
        item.setMessage(resultSet.getString("message"));
        item.setDate(resultSet.getDate("date"));
        item.setTreated(resultSet.getBoolean("treated"));
        Animal animal = new Animal();
        animal.setId(resultSet.getLong("fk_animal"));
        animal.setName(resultSet.getString("name"));
        animal.setDescription(resultSet.getString("description"));
        animal.setBirthYear(resultSet.getInt("birth_year"));
        animal.setSex(resultSet.getString(("sex")).toLowerCase().equals("f") ? 'f' : 'm');
        animal.setSterilized(resultSet.getBoolean("sterilized"));
        animal.setAdoptable(resultSet.getBoolean("adoptable"));
        item.setAnimal(animal);
        return item;
    }


    @Override
    public ContactRequest find(Long id){
        ContactRequest item = null;
        try {
            Connection connection = getConnection();
            String SQL = FIND_BY_ID;
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                item = fetch(resultSet);
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    };

    @Override
    public List<ContactRequest> findAll() {
        List<ContactRequest> items = new ArrayList<ContactRequest>();
        try {
            Connection connection = getConnection();
            String SQL = FIND_ALL;
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ContactRequest user = fetch(resultSet);
                items.add(user);
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    };


    @Override
    public List<ContactRequest> findByAnimal(Animal animal) {
        List<ContactRequest> items = new ArrayList<ContactRequest>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ANIMAL);
            preparedStatement.setLong(1, animal.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ContactRequest item = fetch(resultSet);
                items.add(item);
            }
            preparedStatement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

}