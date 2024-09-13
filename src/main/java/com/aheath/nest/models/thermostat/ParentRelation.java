package com.aheath.nest.models.thermostat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentRelation {
    private String parent;
    private String displayName;
}
