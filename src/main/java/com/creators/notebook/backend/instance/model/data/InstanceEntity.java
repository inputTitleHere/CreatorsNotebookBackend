package com.creators.notebook.backend.instance.model.data;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

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

}
