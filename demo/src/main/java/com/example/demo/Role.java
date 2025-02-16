package com.example.demo;


import lombok.Data;
import jakarta.persistence.*;
import java.util.Set;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name; // e.g., ADMIN, MANAGER, EMPLOYEE

    @ManyToMany(mappedBy = "roles")
    private Set<Employee> employees;
}
