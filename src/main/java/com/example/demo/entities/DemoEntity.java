package com.example.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "demo")
public class DemoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wrapper_id")
    private Long wrapperId;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWrapperId() {
        return wrapperId;
    }

    public void setWrapperId(Long wrapperId) {
        this.wrapperId = wrapperId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DemoEntity{" +
                "id=" + id +
                ", wrapperId=" + wrapperId +
                ", name='" + name + '\'' +
                '}';
    }

    public DemoEntity(Long wrapperId, String name) {
        this.wrapperId = wrapperId;
        this.name = name;
    }

    public DemoEntity() {
    }
}
