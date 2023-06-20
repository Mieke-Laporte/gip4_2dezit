package ucll.gip.gip4_2dezit.dto;

import java.util.Date;

public class AuthorDTO {
    private int id;
    private String name;
    private Date birthDate;
    private String biography;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public AuthorDTO(String name, Date birthDate, String biography) {
        setId(id);
        setName(name);
        setBirthDate(birthDate);
        setBiography(biography);
    }
}
