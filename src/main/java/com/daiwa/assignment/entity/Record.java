package com.daiwa.assignment.entity;

import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "RECORD")
public class Record {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;
    @Column(name = "CREATED")
    private LocalDateTime created;
    @Column(name = "NAME", unique = true)
    private String name;
    @Column(name = "PRICE")
    private Float price;
    @Column(name = "GENRE")
    private Genre genre;
}