package ua.goit.models;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"company", "projects", "skills"})
@ToString(exclude = {"company", "projects", "skills"})
@Entity
@Table(name = "developers")
public class Developer implements BaseEntity<Long> {

    private static final long serialVersionUID = -120117064732753687L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "salary")
    //@Type(type = "big_decimal")
    private Integer salary;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "developers_skills",
            joinColumns = {@JoinColumn (name = "developer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id", referencedColumnName = "id", nullable = false)})
    private Set <Skill> skills;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "developers_projects",
            joinColumns = {@JoinColumn (name = "developer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "id")})
    private Set <Project> projects;

    public Developer(String ... parameters){
        this.id = Long.valueOf(parameters[0]);
        this.name = parameters[1];
        this.age = Integer.valueOf(parameters[2]);
        this.salary = Integer.valueOf(parameters[3]);
    }
}
