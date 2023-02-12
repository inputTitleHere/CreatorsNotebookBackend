package com.creators.notebook.backend.instance.controller;

import com.creators.notebook.backend.exception.NoItemException;
import com.creators.notebook.backend.instance.model.data.InstanceDto;
import com.creators.notebook.backend.instance.model.data.InstanceEntity;
import com.creators.notebook.backend.instance.model.service.InstanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/instance")
@RequiredArgsConstructor
public class InstanceController {
  private InstanceService instanceService;

  /**
   * Create
   */
  @PostMapping("/new")
  public ResponseEntity<?> createInstance(@RequestBody InstanceDto instanceDto) {
    try {
      InstanceEntity instanceEntity = instanceService.save(new InstanceEntity(instanceDto));
      return ResponseEntity.ok().body(instanceEntity);
    } catch (NoItemException e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().build();
    }
  }


  /**
   * Read
   */
  @GetMapping("/{instanceUuid}")
  public ResponseEntity<?> getInstance(@PathVariable("instanceUuid") UUID instanceUuid ){
    InstanceEntity instanceEntity = instanceService.findById(instanceUuid);
    // TODO -> Entity to DTO
    return ResponseEntity.ok().build(); // TODO -> Return DTO
  }

  /**
   * Update
   */

  /**
   * Delete
   */

}
