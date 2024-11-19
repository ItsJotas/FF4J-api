package com.example.ff4j_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "feature_flag")
public class FeatureFlag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "FEATURE_KEY", unique = true, nullable = false)
    private String featureKey;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "ID_PHASE", nullable = false)
    private Phase phase;

    @ManyToOne
    @JoinColumn(name = "ID_APLICACAO", nullable = false)
    private Application application;
}
