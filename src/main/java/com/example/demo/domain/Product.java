package com.example.demo.domain;

import com.example.demo.validators.ValidEnufParts;
import com.example.demo.validators.ValidProductPrice;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Products")
@ValidProductPrice
@ValidEnufParts
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 0, message = "Price must be at least 0")
    private double price;

    @Min(value = 0, message = "Inventory must be at least 0")
    private int inv;

    @Min(value = 0, message = "Min inventory must be at least 0")
    private int min;

    @Min(value = 0, message = "Max inventory must be at least 0")
    private int max;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "product_part",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id")
    )
    private Set<Part> parts = new HashSet<>();

    public Product() {}

    public Product(String name, double price, int inv, int min, int max) {
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.min = min;
        this.max = max;
    }

    public Product(long id, String name, double price, int inv, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.min = min;
        this.max = max;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getInv() { return inv; }
    public void setInv(int inv) { this.inv = inv; }

    public int getMin() { return min; }
    public void setMin(int min) { this.min = min; }

    public int getMax() { return max; }
    public void setMax(int max) { this.max = max; }

    public Set<Part> getParts() { return parts; }
    public void setParts(Set<Part> parts) { this.parts = parts; }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
