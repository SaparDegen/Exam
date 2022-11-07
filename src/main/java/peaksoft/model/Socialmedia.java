package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "socialmedias")
@Getter
@Setter
@NoArgsConstructor
public class Socialmedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, columnDefinition = "TEXT default 'Not provided'")
    private String socialMedia;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "person-socialmedia",
            joinColumns=@JoinColumn(name = "socialmedia_id"),
            inverseJoinColumns=@JoinColumn(name = "person_id"))
    private List<Person> people;


    public Socialmedia(String socialMedia) {
        this.socialMedia = socialMedia;
    }

    public Socialmedia(Long id, String socialMedia) {
        this.id = id;
        this.socialMedia = socialMedia;
    }

    public void addPersonToList(Person person) {
        if (this.people == null) {
            this.people = new ArrayList<>();
        }
        this.people.add(person);
    }

    @Override
    public String toString() {
        return "Socialmedia{" +
                "id=" + id +
                ", socialMedia='" + socialMedia + '\'' +
                ", people=" + people.stream().map(x -> x.getName()).toList() +
                '}';
    }
}
