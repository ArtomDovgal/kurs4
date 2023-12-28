package com.example.demo.entity;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Payment extends com.example.demo.entity.Entity {

    private Float amount;
    @Enumerated
    private Type type;
    private LocalDateTime dateTime;
    private String cardNumber;
    private Boolean status;

    enum Type{
        CARD,CASH
    }
}
