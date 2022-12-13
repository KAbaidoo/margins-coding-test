package io.codingtest.springboot.models.users;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role(Long id, ERole name) {
        this.id = id;
        this.name = name;
    }

    public Role() {
    }

    public void setName(ERole name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public ERole getName() {
        return name;
    }

}
