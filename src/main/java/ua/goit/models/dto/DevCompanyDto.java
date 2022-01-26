package ua.goit.models.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import ua.goit.models.BaseEntity;

import java.util.Set;

@Data
public class DevCompanyDto implements BaseEntity<Long>{

    private static final long serialVersionUID = -6504139281285180388L;

    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("age")
    private Integer age;

    @SerializedName("salary")
    private Integer salary;

    @SerializedName("company")
    private Long company;

    @SerializedName("skills")
    private Set<Long> skils;
}
