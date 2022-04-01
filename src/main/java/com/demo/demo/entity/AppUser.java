package com.demo.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AppUser {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique=true) @NotNull
    private String username;

    @JsonIgnore
    private String password;

    @ElementCollection
    @Enumerated(EnumType.STRING) @JsonIgnore
    private Set<Role> roleSet;

    @OneToMany(mappedBy = "seller") @JsonIgnore
    private List<Product> products;
}
