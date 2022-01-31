package ua.goit.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"developers"})
@ToString(exclude = {"developers"})
@Entity
@Table(name = "skills")
public class Skill implements BaseEntity<Long> {

    private static final long serialVersionUID = 7586817179994468707L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "field", length = 45)
    private String skillsField;

    @Column(name = "level", length = 45)
    private String skillsLevel;

    @ManyToMany(mappedBy = "skills", fetch = FetchType.EAGER)
    private Set<Developer> developers;

    public Skill(String ... parameters){
        this.id = Long.valueOf(parameters[0]);
        this.skillsField = parameters[1];
        this.skillsLevel = parameters[2];
    }
}
