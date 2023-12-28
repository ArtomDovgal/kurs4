package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BrandDTO extends EntityDTO{

    String name;
    List<Long> productsId;
}
