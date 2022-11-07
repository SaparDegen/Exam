package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mark;

    private String producedCountry;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "car", fetch = FetchType.EAGER)
    private Garage garage;


    public Car(String mark, String producedCountry) {
        this.mark = mark;
        this.producedCountry = producedCountry;
    }

    public Car(Long id, String mark, String producedCountry) {
        this.id = id;
        this.mark = mark;
        this.producedCountry = producedCountry;
    }

    public String getPersonName() {
        if (this.person != null) {
            return this.person.getName();
        }
        return null;
    }

    public String getGarageName() {
        if (this.garage != null) {
            return this.garage.getName();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", producedCountry='" + producedCountry + '\'' +
                ", person=" + getPersonName() +
                ", garage=" + getGarageName() +
                '}';
    }
}
