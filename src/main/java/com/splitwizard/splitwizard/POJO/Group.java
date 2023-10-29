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
    // need to check if we still want this column or not.
    private Boolean status = false;
    private Boolean redirect = false;
    private Boolean archive = false;
    @Column(name = "created_time", updatable = false)
    private Timestamp createdTime = new Timestamp(System.currentTimeMillis());
    @Column(name = "update_time")
    private Timestamp updateTime = new Timestamp(System.currentTimeMillis());

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
