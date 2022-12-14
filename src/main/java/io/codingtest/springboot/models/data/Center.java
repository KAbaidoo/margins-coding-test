package io.codingtest.springboot.models.data;

import javax.persistence.*;

@Entity
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    public Center(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Center() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
