package com.learn.resource.part37_mapstruct.part13_hibernate_ORM;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotBlank(message = "Name Should not be blank")
    @Length(min = 3,max = 20)
    String fullName;

    @Pattern(regexp = "[MF]", message = "Gender must match M or F ")
    String gender;

    @Length(min = 12,max = 12, message = "Aadhar length must be between 12 and 12")
    String aadharNo;

    @Min(value = 10 , message = "Age must be greater than or equal to 10")
    @Max(value = 60 , message = "Age must be less than or equal to 60")
    int age;

    String country;

    @Digits(integer = 6, fraction = 0)
    String pinCode;

//    @OneToOne(mappedBy = "laptop", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JsonManagedReference
//    Owner owner;

}
