package com.sumkin.hw3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Entity(name = "users")
@Schema(description = "Класс пользователей")
public class User {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Id")
    private Long id;
    @Schema(description = "Имя")
    private String name;
    @Schema(description = "Email")
    private String email;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH
    )
    @Schema(accessMode = Schema.AccessMode.READ_ONLY,
            description = "List of orders")
    @JsonIgnoreProperties("user")
    private List<Order> orders;

    public User(String name, String mail) {
        this.name = name;
        this.email = mail;
    }

    public User(long l, String alice, String mail) {
        this.id = l;
        this.name = alice;
        this.email = mail;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
