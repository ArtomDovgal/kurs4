package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MaterialDTO extends EntityDTO{

    private String name;

    private List<Long> productsId;
}
