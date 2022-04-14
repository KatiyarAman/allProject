package com.example.redis.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pla_feed")
public class PlaFeed {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String msn;
    private String gtin;
    @Column(name = "identifier_exists")
    private String identifierExists;
    @Column(name = "custom_label2")
    private String customLabel2;
    @Column(name = "custom_label3")
    private String customLabel3;
    @Column(name = "custom_label4")
    private String customLabel4;
    @Column(name = "promotion_id")
    private String promotionId;
    @Column(name = "is_google_active")
    private String isGoogleActive;
    @Column(name = "is_facebook_active")
    private String isFacebookActive;
    @Column(name = "is_criteo_active")
    private String isCriteoActive;
    @Column(name = "availability")
    private String availability;
}
