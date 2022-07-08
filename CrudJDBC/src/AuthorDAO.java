import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class AuthorDAO {
    public List<Author> getAuthorList() {
        return authorList;
    }

    List<Author> authorList = new LinkedList<>();

    public void fillList() throws SQLException {
        ConnectionDB connectionDB = new ConnectionDB();
        Statement stmt = connectionDB.connect().createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from author");
        while (resultSet.next())
            authorList.add(new Author(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4)));
        stmt.close();

    }

    public void print() {
        for (Author author : authorList) {
            System.out.println(author);
        }
    }

    public void insert(Author author) throws SQLException {
        ConnectionDB connectionDB = new ConnectionDB();
        Statement stmt = connectionDB.connect().createStatement();
        String name = author.getName();
        String surname = author.getSurname();
        Date date = author.getBirthday();
        String query = "INSERT INTO author VALUES(NULL,' " + name + " ','" + surname + "','" + date + "')";
        System.out.println(query);
        stmt.executeUpdate(query);
        stmt.close();
    }


    public void update(Author author, int id) throws SQLException {//Obj id
        ConnectionDB connectionDB = new ConnectionDB();
        Statement stmt = connectionDB.connect().createStatement();
        String name = author.getName();
        String surname = author.getSurname();
        Date date = author.getBirthday();


        String query = "UPDATE author SET name = '" + name + "' WHERE aut_id = '" + id + "'";
        stmt.executeUpdate(query);
        query = "UPDATE author set surname='" + surname + "' WHERE aut_id = '" + id + "'";
        stmt.executeUpdate(query);
        query = "UPDATE author SET birthday = '" + date + "' WHERE aut_id = '" + id + "'";

        System.out.println(query);
        stmt.executeUpdate(query);
        stmt.close();
    }

    public void delete(int del) throws SQLException {
        ConnectionDB connectionDB = new ConnectionDB();
        Statement stmt = connectionDB.connect().createStatement();
        String query = "DELETE FROM author WHERE aut_id= '" + del + "'";
        System.out.println(query);
        stmt.executeUpdate(query);
        stmt.close();
    }

    public Author getRow(int id) throws SQLException {
        ConnectionDB connectionDB = new ConnectionDB();
        Statement stmt = connectionDB.connect().createStatement();
        String query = "SELECT name, surname, birthday FROM author WHERE aut_id= '" + id + "'";
        ResultSet rs = stmt.executeQuery(query);
        Author retrived = null;
        String name = null;
        String surname = null;
        Date date = null;
        while (rs.next()) {
            name = rs.getString("name");
            surname = rs.getString("surname");
            date = rs.getDate(3);
            retrived = new Author(0, name, surname, date);
        }
        System.out.println(name + " " + surname + " " + date);
        stmt.close();
        return retrived;
    }


}
