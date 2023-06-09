package com.example.course.Product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int price;
    private int count;
    private  String title;
    private  String opis;
    private String img;
    public Product(String title, String opis) {
        this.title = title;
        this.opis = opis;
    }


    public Product(long l, int i, int i1, String s, String s1) {
    }

    public Product(Long aLong, int i, int i1, String new_product, String opis) {
    }
}
