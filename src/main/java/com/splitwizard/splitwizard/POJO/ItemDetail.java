package com.splitwizard.splitwizard.POJO;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Getter @Setter
@Table(name = "item_detail")
public class ItemDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer memberId;
    @Column(name = "item_id")
    private Integer itemId;
    private BigDecimal amount;
    private Boolean payer;
    @Column(name = "created_time",updatable = false)
    private Timestamp createdTime = new Timestamp(System.currentTimeMillis());
    @Column(name = "update_time")
    private Timestamp updateTime = new Timestamp(System.currentTimeMillis());

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    Item item;
}
