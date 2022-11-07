package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 30)
    private String companyName;

    private String location;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "company", fetch = FetchType.EAGER)
    private List<Person> people;


    public Company(String companyName, String location) {
        this.companyName = companyName;
        this.location = location;
    }

    public Company(Long id, String companyName, String location) {
        this.id = id;
        this.companyName = companyName;
        this.location = location;
    }

    public void addPersonToList(Person person) {
        if (this.people == null) {
            this.people = new ArrayList<>();
        }
        this.people.add(person);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", location='" + location + '\'' +
                ", people=" + people.stream().map(x -> x.getName()).toList() +
                '}';
    }
}
