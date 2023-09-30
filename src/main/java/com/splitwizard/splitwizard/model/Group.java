package com.splitwizard.splitwizard.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Getter @Setter
@Table(name = "`group`")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Boolean status;
    private Boolean redirect;
    private Boolean archive;
    private Timestamp created_time;
    private Timestamp update_time;

}
