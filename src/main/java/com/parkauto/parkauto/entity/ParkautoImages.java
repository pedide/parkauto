package com.parkauto.parkauto.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="PARKAUTOIMAGES")
public class ParkautoImages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    @Column(name="DESCRIPTIF")
    private String descriptif;
    @Column(name="URL")
    private String url;

    @ManyToOne
    @JoinColumn(name="vehiculeImage_id")
    private Vehicule vehicule;

}
