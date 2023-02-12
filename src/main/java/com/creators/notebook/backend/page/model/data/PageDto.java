package com.creators.notebook.backend.page.model.data;

import com.creators.notebook.backend.instance.model.data.InstanceDto;
import com.creators.notebook.backend.project.model.data.ProjectDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Slf4j
public class PageDto {
  private UUID pageUuid;
  private String pageTitle;
  private ProjectDto projectDto;
  private List<InstanceDto> instanceDtos;


}
