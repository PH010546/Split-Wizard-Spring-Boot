package com.splitwizard.splitwizard.POJO;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Getter @Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Integer id;
    private String name;
    private Integer amount;
    private String itemTime;
    @Column(name = "created_time", insertable = false, updatable = false)
    private Timestamp createdTime;
    @Column(name = "update_time", insertable = false)
    private Timestamp updateTime;
    @Column(name = "group_id")
    private Integer groupId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "item_fk"), insertable = false, updatable = false)
    Group group;

    @OneToMany(mappedBy = "item", cascade = {CascadeType.REMOVE})
    List<ItemDetail> itemDetails;

}
