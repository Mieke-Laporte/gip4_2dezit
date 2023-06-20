package ucll.gip.gip4_2dezit.dtos;

import ucll.gip.gip4_2dezit.model.Author;

import java.time.LocalDate;

public class AuthorDTO {
    private String name;
    private String birthDate;
    private String biography;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Author toAuthor(){
        Author author = new Author();
        author.setName(this.getName());
        if (this.getBirthDate() != null && !this.getBirthDate().isEmpty()){
            author.setBirthDate(LocalDate.parse(birthDate));
        }
        author.setBiography(this.getBiography());
        return author;
    }
}
