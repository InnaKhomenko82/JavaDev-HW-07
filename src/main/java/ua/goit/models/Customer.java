package ua.goit.models;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"projects"})
@ToString(exclude = {"projects"})
@Entity
@Table(name = "customers")
public class Customer implements BaseEntity<Long>{

    private static final long serialVersionUID = 536576277262101673L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "category", length = 45)
    private String category;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "customers_projects",
            joinColumns = {@JoinColumn (name = "customer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "id")}
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Project> projects;

    public Customer(String ... parameters){
        this.id = Long.valueOf(parameters[0]);
        this.name = parameters[1];
        this.category = parameters[2];
    }
}
