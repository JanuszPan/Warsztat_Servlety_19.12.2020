package pl.coderslab.utils;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class UserDao {
//    zapytania CRUD (create, read, update, delete) + findAll + deleteAll

    //    zapytanie tworzące użytkownika (create)
    private static final String CREATE_USER_QUERY = "INSERT INTO users(username,email,password) VALUES (?,?,?);";
    //    zapytanie wczytujące wszystkie dane użytkownika (read)
    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id=?;";
    //    zapytanie edytujące dane użytkownika (update)
    private static final String UPDATE_USER_QUERY = "UPDATE users set username=?, email=?, password=? WHERE id=?;";
    //    zapytanie czytujące wszystkich użytkowników
    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users;";
    //    zapytanie usuwające użytkownika (delete)
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id=?;";


//    private static final String RETURN_GENERATED_KEYS ="SELECT id FROM users WHERE id=?;";


    //    tworzenie użytkownika
    public User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
//        trzeba jeszcze dodać identyfikator wiersza
            PreparedStatement statement = conn.prepareStatement(CREATE_USER_QUERY);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
//trzeba zahaszować hasło http://www.mindrot.org/projects/jBCrypt/
            statement.setString(3, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User read(int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, userId);
//                tworzymy obiekt, który zawiera wynik zapytania
            ResultSet resultSet = statement.executeQuery();
//                metoda next() zwraca wartość boolean, która informuje czy są jeszcze jakieś elementy
            if (resultSet.next()) {
//                    tworzymy nowy obiekt klasy User
                User user = new User();
//                    uzupełniamy obiekt danymi z bazy
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
//                    zwracamy uzupełniony obiekt
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //        metoda służąca do odczytania użytkownika dla zadanego identyfikatora.
//        W ramach metody należy zmienić dane w bazie na podstawie danych z obiektu
    public void update(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //        metoda służąca do pobrania wszystkich obiektów klasy User
    public User[] findAll() {
        try (Connection conn = DbUtil.getConnection()) {
            User[] users = new User[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
//                trzeba umieścić obiekty w tablicy users
                users = Arrays.copyOf(users, users.length + 1);
                users[users.length - 1] = user;
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //    metoda usuwająca użytkownika z bazy
    public void delete(int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

