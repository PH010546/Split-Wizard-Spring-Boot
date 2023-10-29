package com.splitwizard.splitwizard.POJO;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Getter @Setter
@Table(name = "`group`")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Integer id;

    private String name;
    // TODO: need to check if we still want this column or not.
    @Column(insertable = false, columnDefinition = "boolean default false")
    private Boolean status;
    @Column(insertable = false, columnDefinition = "boolean default false")
    private Boolean redirect;
    @Column(insertable = false, columnDefinition = "boolean default false")
    private Boolean archive;
    @Column(name = "created_time",insertable = false, updatable = false, columnDefinition = "timestamptz default now()")
    private Timestamp createdTime;
    @Column(name = "update_time", insertable = false, columnDefinition = "timestamptz default now()")
    private Timestamp updateTime;

    @ManyToMany
    @JoinTable(
            name = "member_group_conn",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    Set<Member> members;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;
}
