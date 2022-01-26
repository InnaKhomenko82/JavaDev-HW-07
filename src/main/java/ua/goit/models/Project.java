package ua.goit.models;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TimeZone;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"developers", "companies", "customers"})
@ToString(exclude = {"developers", "companies", "customers"})
@Entity
@Table(name = "projects")
public class Project implements BaseEntity<Long> {

    private static final long serialVersionUID = -8831832807197146954L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "start")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp projectStart;

    @Column(name = "cost")
    //@Type(type = "big_decimal")
    private Integer cost;

    @ManyToMany(mappedBy = "projects")
    private Set<Developer> developers;

    @ManyToMany(mappedBy = "projects")
    private Set<Company> companies;

    @ManyToMany(mappedBy = "projects")
    private Set<Customer> customers;

    @SneakyThrows
    public Project(String ... parameters){
        this.id = Long.valueOf(parameters[0]);
        this.name = parameters[1];

        TimeZone utcTimeZone = TimeZone.getTimeZone("UTC");
        TimeZone.setDefault(utcTimeZone);

        DateTimeFormatter formatForDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String timestampAsString = parameters[2];
        Timestamp timestamp = Timestamp.valueOf(LocalDate.
                parse(timestampAsString, formatForDate).atStartOfDay());
        this.projectStart = timestamp;

        this.cost = Integer.valueOf(parameters[3]);
    }
}
