package com.CryptoMarket.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "CryptoInfo")
@Data
public class CryptoBriefInfo {
    @Id
    private Integer id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column (name = "tag",nullable = false)
    private String tag;
    @OneToMany(mappedBy = "crypto")
    private List<Quote> quotes;
}
