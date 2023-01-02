package com.release.iktatoapi.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Date;



@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_data")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "data_hlyrsz")
    private String hszNum;
    @Column(name = "data_irsz")
    private String irNum;
    @Column(name = "data_varos")
    private String city;
    @Column(name = "data_cim")
    private String address;
    @Column(name = "data_utca")
    private String street;
    @Column(name = "data_hsz")
    private String streetNum;
    @Column(name = "data_em")
    private String floorNum;
    @Column(name = "data_door")
    private String doorNum;
    @Column(name = "data_megbizo")
    private String principal;
    @Column(name = "data_megbizkep")
    private String principalDelegate;
    private String description;
    @Column(name = "data_dij")
    private Integer amount;
    private String ig_category;
    private String va_category;
    private Date date;
    @Column(name="created_at",nullable=false,updatable=false)
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name="updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name="user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
    private Boolean isDone;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dataHolder_id")
    private DataHolder dataHolder;
    @Column(name="iktNum")
    private String dataIktNum;
    @Lob
    private byte[] data;
    private String type;
    private String name;
}
