package com.creators.notebook.backend.instance.model.data;

import com.creators.notebook.backend.Item.model.data.ItemEntity;
import com.creators.notebook.backend.page.model.data.PageEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "instance")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@Builder
@TypeDef(name = "json",typeClass = JsonType.class)
public class InstanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long instanceId;


    @OneToOne
    @JoinColumn(name = "item_uuid",referencedColumnName = "item_uuid")
    private ItemEntity itemEntity;

    @Column(name = "xpos", columnDefinition = "integer")
    private int xpos;
    @Column(name = "ypos", columnDefinition = "integer")
    private int ypos;

    @ManyToOne
    @JsonBackReference(value = "page_uuid")
    @JoinColumn(name = "page_uuid", referencedColumnName = "page_uuid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PageEntity pageEntity;

    @Type(type = "json")
    @Column(name = "instance_data",columnDefinition = "jsonb")
    private Map<String, Object> instanceData;



}
