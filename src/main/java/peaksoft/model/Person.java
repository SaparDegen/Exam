package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Gender;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persons")
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25)
    private String name;

    private Integer age;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private Gender gender;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "person-socialmedia",
            joinColumns=@JoinColumn(name = "person_id"),
            inverseJoinColumns=@JoinColumn(name = "socialmedia_id"))
    private List<Socialmedia> socialmedia;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "person", fetch = FetchType.EAGER)
    private List<Car> cars;


    public Person(String name, Integer age, String email, Gender gender) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.gender = gender;
    }

    public Person(Long id, String name, Integer age, String email, Gender gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.gender = gender;
    }

    public void addSocialMediaToList(Socialmedia socialmedia) {
        if (this.socialmedia == null) {
            this.socialmedia = new ArrayList<>();
        }
        this.socialmedia.add(socialmedia);
    }

    public void addCarToList(Car car) {
        if (this.cars == null) {
            this.cars = new ArrayList<>();
        }
        this.cars.add(car);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", company=" + company.getCompanyName() +
                ", socialMedia=" + socialmedia.stream().map(x -> x.getSocialMedia()).toList() +
                ", car=" + cars.stream().map(x -> x.getMark()).toList() +
                '}';
    }
}
