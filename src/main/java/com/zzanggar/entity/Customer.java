package com.zzanggar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zzanggar.constants.Length;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    @Column
    private String businessCategory;

    @Column
    private String companyName;

    @Column
    private String licenseNumber;

    @Column
    private String companyAddress;

    @Column
    private String managerName;

    @Column
    private String position;

    @Column
    private String department;

    @Column
    private String telNumber;

    @Column
    private String phoneNumber;

    @Column
    private String faxNumber;

    @Column(unique = true, length = Length.EMAIL)
    private String email;

    @Column
    private String finalCreator;

    @Column
    private String memo;

    @Column
    private Date createdTime;

    @Column
    private Date updatedTime;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Setter
    private List<Counsel> counselList;

    @PrePersist
    protected void onCreate() {
        createdTime = updatedTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTime = new Date();
    }
}
