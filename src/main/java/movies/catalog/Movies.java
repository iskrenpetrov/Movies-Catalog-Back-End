package movies.catalog;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="movies")
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="category_id")
    private Categories category;

    public long getId() {
        return id;
    }

    public Movies setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Movies setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Movies setDescription(String description) {
        this.description = description;
        return this;
    }

    public Categories getCategory() {
        return category;
    }

    public Movies setCategory(Categories category) {
        this.category = category;
        return this;
    }

//    @Override
//    public String toString() {
//        return "Movies {" +
//                "id=" + id +
//                ", name='" + name +
//                ", description='" + description + '\'' +
//                '}';
//    }

}
