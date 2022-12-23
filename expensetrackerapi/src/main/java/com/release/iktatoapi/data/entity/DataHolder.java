package com.release.iktatoapi.data.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_dataHolder")
public class DataHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dataHolder")
    private List<Data> dataStack;
    private String iktNumFront;
    private String iktNumEnd;
    private String iktNum;
    private int countNum;

}
