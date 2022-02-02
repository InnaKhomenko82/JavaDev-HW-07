package ua.goit.models.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;


@Data
public class TestDTO{

    @Column(name = "start")
    private Date projectStart;

    @SerializedName("name")
    private String name;

    @Column(name = "quantity")
    private Integer quantityDevs;
}
