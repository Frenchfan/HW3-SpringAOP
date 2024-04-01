package com.sumkin.hw3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Entity(name = "orders")
@Schema(description = "Класс заказов")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Schema(description = "Пользователь")
    @JsonIgnoreProperties("orders")
    private User user;

    @Schema(description = "Описание")
    private String description;
    @Schema(description = "Статус")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Order(String updatedDescription) {
        this.description = updatedDescription;
    }
}
