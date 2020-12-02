package com.yrgo.projectcar.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank
    @Column(name = "name")
    private String name;
    @NotBlank
    @Column(name = "model")
    private String model;
    @NotBlank
    @Column(name = "year")
    private long year;
    @NotBlank
    @Column(name = "price")
    private long price;

    public void setName(String name) {
        this.name = name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Car() { }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }


     public long getYear() {
        return year;
    }
    public long getPrice() {
        return price;
    }

    public Car(@JsonProperty("id") long id,
               @JsonProperty("name") String name,
               @JsonProperty("model") String model,
               @JsonProperty("year") long year,
               @JsonProperty("price") long price)
               {
        this.id = id;
        this.name = name;
        this.model = model;
        this.year = year;
        this.price = price;
    }
}
