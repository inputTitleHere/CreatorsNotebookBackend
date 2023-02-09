package com.creators.notebook.backend.page.model.data;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Location implements Serializable {

    private UUID thingUuid;

    private double x;
    private double y;

    // maybe some extra information of style or ect.

}
