package com.powerplantsystem.dto;

import com.powerplantsystem.entity.Battery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatteryStat {

    private List<Battery> batteryList;
    private CapacityStat capacityStat;
}
