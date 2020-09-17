package database.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @NotNull
    public Employee employee;

    @EqualsAndHashCode.Exclude
    @NotEmpty
    private String name;

    @EqualsAndHashCode.Exclude
    @NotEmpty
    private String model;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @NotNull
    private LocalDate registrationDate;

}
