package ru.gb.zettro.lesson5;

import javax.persistence.*;

@Entity
@Table(name = "sample_entities")
public class SampleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public SampleEntity() {
    }

    public SampleEntity(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "SampleEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
