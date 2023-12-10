package com.powerplantsystem.service;

import com.powerplantsystem.dto.BatteryStat;
import com.powerplantsystem.entity.Battery;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface BatteryService {
    List<Battery> saveBatteries(List<Battery> batteries);
    BatteryStat getBatteriesBetweenPostcodeRanges(Long postCodeStart, Long postCodeEnd);
}
