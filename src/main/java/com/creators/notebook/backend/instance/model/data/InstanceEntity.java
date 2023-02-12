package com.creators.notebook.backend.instance.model.data;

import com.creators.notebook.backend.Item.model.data.ItemDto;
import com.creators.notebook.backend.Item.model.data.ItemEntity;
import com.creators.notebook.backend.exception.NoItemException;
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
@Getter
@Setter
@Builder
@TypeDef(name = "json", typeClass = JsonType.class)
public class InstanceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long instanceId;


  /**
   * 어떤 item을 인스턴스화 한 것인지.
   */
  @OneToOne
  @JoinColumn(name = "item_uuid", referencedColumnName = "item_uuid")
  private ItemEntity itemEntity;

  // 좌표 정보는 instanceData으로 다루도록 한다. -> x,y 좌표가 없는 group 소속의 인스턴스 대용.
//    @Column(name = "xpos", columnDefinition = "integer")
//    private int xpos;
//    @Column(name = "ypos", columnDefinition = "integer")
//    private int ypos;

  /**
   * 어떤 페이지에 배정된 instance인지
   */
  @ManyToOne
  @JsonBackReference(value = "page_uuid")
  @JoinColumn(name = "page_uuid", referencedColumnName = "page_uuid")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private PageEntity pageEntity;

  @Type(type = "json")
  @Column(name = "instance_data", columnDefinition = "jsonb")
  private Map<String, Object> instanceData;

  public InstanceEntity(InstanceDto instanceDto) throws NoItemException{
    this.instanceId = instanceDto.getInstanceId();
    this.itemEntity = new ItemEntity(instanceDto.getItemDto());
    this.pageEntity = new PageEntity(instanceDto.getPageDto());
    this.instanceData = instanceDto.getInstanceData();
  }

}
