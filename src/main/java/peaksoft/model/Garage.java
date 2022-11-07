package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "garages")
@Getter
@Setter
@NoArgsConstructor
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean isEmpty = true;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;

    public Garage(String name) {
        this.name = name;
    }

    public Garage(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getCarName() {
        if (this.car != null) {
            return this.car.getMark();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Garage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isEmpty='" + isEmpty + '\'' +
                ", car=" + getCarName() +
                '}';
    }
}
