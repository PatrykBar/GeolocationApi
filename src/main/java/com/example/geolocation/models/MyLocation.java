package com.example.geolocation.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class MyLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private int deviceId;
    private int latitiude;
    private int longitude;
}
