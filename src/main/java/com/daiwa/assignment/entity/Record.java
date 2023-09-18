package com.daiwa.assignment.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Setter@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    @Override
    public Object clone() {
        return Record.builder()
                .id(this.getId())
                .name(this.getName())
                .genre(this.getGenre())
                .created(this.getCreated())
                .price(this.getPrice())
                .build();
    }
}