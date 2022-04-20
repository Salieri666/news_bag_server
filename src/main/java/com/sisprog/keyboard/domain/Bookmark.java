package com.sisprog.keyboard.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bookmark")
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "url")
    private String url;

    @Column(name = "title")
    private String title;

    @Column( name = "description")
    private String description;

    @Column( name = "img")
    private String img;

    @Column( name = "source")
    private String source;

    @Column( name = "source_date")
    private Long sourceDate;

    @Column( name = "created_date")
    private Long createdDate;

}
