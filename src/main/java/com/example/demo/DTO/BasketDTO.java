package com.example.demo.DTO;

import com.example.demo.entity.OrderItem;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

public class BasketDTO extends EntityDTO{

    Integer totalSum;

    Long customerId;

    List<Long> orderItemsId;


    public BasketDTO(Integer totalSum,Long customerId,List<Long> orderItemsId){

        this.totalSum = totalSum;
        this.customerId=customerId;
        this.orderItemsId=orderItemsId;

    }


}
