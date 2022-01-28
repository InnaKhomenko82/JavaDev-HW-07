package ua.goit.models.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import ua.goit.models.BaseEntity;
import ua.goit.models.Skill;


@Data
public class TestDTO{
    @SerializedName("skills")
    private Skill skills;
}
