package com.learn.resource.part13_hibernate_ORM;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;
    String brand;
    int ram;

    @OneToOne(mappedBy = "laptop", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    Owner owner;

}
