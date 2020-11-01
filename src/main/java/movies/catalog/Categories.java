package movies.catalog;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private long id;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Movies> movies = new ArrayList<>();

    public long getId() {
        return id;
    }

    public Categories setId(long id) {
        this.id = id;
        return this;
    }

    public String getName(){
        return name;
    }

    public Categories setName(){
        this.name=name;
        return this;
    }

    public List<Movies> getMovies() {
        return movies;
    }

    public Categories setMovies(List<Movies> movies) {
        this.movies = movies;
        return this;
    }

}
