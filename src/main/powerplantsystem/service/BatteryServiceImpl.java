
package com.powerplantsystem.service;

import com.powerplantsystem.dto.BatteryStat;
import com.powerplantsystem.dto.CapacityStat;
import com.powerplantsystem.entity.Battery;
import com.powerplantsystem.repository.BatteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BatteryServiceImpl implements BatteryService {
    @Autowired
    private BatteryRepository batteryRepository;

    public List<Battery> saveBatteries(List<Battery> batteries) {
        List<Battery> savedList = new ArrayList<>();

        batteries.forEach(battery -> {

            Optional<Battery> existingBatteryOptional= batteryRepository.findByName(battery.getName());

            if (existingBatteryOptional.isPresent()){
                Battery existingBattery = existingBatteryOptional.get();
                existingBattery.setCapacity(battery.getCapacity());
                existingBattery.setPostcode(battery.getPostcode());
                existingBattery = batteryRepository.save(existingBattery);
                savedList.add(existingBattery);

            }
            else{
                Battery newBattery = new Battery();
                newBattery.setName(battery.getName());
                newBattery.setPostcode(battery.getPostcode());
                newBattery.setCapacity(battery.getCapacity());
                newBattery = batteryRepository.save(newBattery);
                savedList.add(newBattery);
            }

        });

        return savedList;
    }
    public BatteryStat getBatteriesBetweenPostcodeRanges(Long postCodeStart, Long postCodeEnd) {

        Sort sort = Sort.by("name").ascending();
        List<Battery> batteries = batteryRepository.findByPostcodeBetween(postCodeStart, postCodeEnd, sort);
        BatteryStat batteryStat = new BatteryStat();
        if (batteries != null) {

            batteryStat.setBatteryList(batteries);

            Double averageCapacity = batteries.stream()
                    .collect(Collectors.averagingLong(Battery::getCapacity));
            Long sumCapacity = batteries.stream().mapToLong(Battery::getCapacity).sum();

            CapacityStat capacityStat = new CapacityStat();
            capacityStat.setAverage(averageCapacity);
            capacityStat.setTotal(sumCapacity);

            batteryStat.setCapacityStat(capacityStat);
        }
        return batteryStat;
    }
}