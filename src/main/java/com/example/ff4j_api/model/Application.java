package com.example.ff4j_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "APPLICATION_NAME", nullable = false, unique = true)
    private String applicationName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "APPLICATION_ONLINE", nullable = false)
    private Boolean isApplicationOnline = true;

    @Column(name = "URL", nullable = false)
    private String url;
}
