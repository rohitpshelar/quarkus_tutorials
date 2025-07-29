package com.learn.resource.part13_hibernate_ORM;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LaptopRepository implements PanacheRepository<Laptop> {
}
