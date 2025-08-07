package com.learn.resource.part37_mapstruct.part13_hibernate_ORM;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CitizenMapper {

    @Mapping(target = "fullName" , expression = "java(citizenDTO.getFirstName()+\" \"+citizenDTO.lastName)")
    @Mapping(target = "country" , expression = "java(citizenDTO.address.split(\"-\")[0])")
    @Mapping(target = "pinCode" , expression = "java(citizenDTO.address.split(\"-\")[1])")
    @Mapping(target = "id", ignore = true)
    Citizen toDao(CitizenDTO citizenDTO);

    @Mapping(target = "address" , expression = "java(citizen.getCountry()+\"-\"+citizen.getPinCode())")
    @Mapping(target = "firstName" , expression = "java(citizen.getFullName().split(\" \")[0])")
    @Mapping(target = "lastName" , expression = "java(citizen.getFullName().split(\" \")[1])")
    CitizenDTO toDto(Citizen citizen);
}
