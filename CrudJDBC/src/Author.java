import java.sql.Date;

public class Author {
    private int autId;
    private String name;
    private String surname;
    private Date birthday;

    public Author() {
    }

    public Author(int autId, String name, String surname, Date birthday) {
        this.autId = autId;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }


    public int getAutId() {
        return autId;
    }

    public void setAutId(int autId) {
        this.autId = autId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Author{" +
                "autId=" + autId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                '}';
    }

}