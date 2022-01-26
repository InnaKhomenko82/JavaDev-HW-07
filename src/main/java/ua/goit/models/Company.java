package ua.goit.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data()
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"developers", "projects"})
@ToString(exclude = {"developers","projects"})
@Entity
@Table (name = "companies")
public class Company implements BaseEntity<Long>{

    private static final long serialVersionUID = 7990325554609608506L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "quantity_staff")
    private Long quantityStaff;

    public Company(String ... parameters){
        this.id = Long.valueOf(parameters[0]);
        this.name = parameters[1];
        this.quantityStaff = Long.valueOf(parameters[2]);
    }

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Developer> developers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "companies_projects",
            joinColumns = {@JoinColumn (name = "company_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "id")}
    )
    private Set<Project> projects;

   /* @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ",\"name\":\"" + name +
                "\"}";
    }*/
}
