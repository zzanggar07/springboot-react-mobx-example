package com.zzanggar.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table
public class Counsel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    @Column(unique = true)
    private String filePath;

    @Column
    private String filename;

    @Column
    private String title;

    @Column
    private Date createdTime;

    @Column(columnDefinition = "TEXT")
    private String stt;

    @ManyToOne
    @JoinColumn(name = "customerIdx")
    private Customer customer;

    @PrePersist
    protected void onCreate() {
        createdTime = new Date();
    }
}
