
package com.powerplantsystem.controller;

import com.powerplantsystem.dto.BatteryStat
import com.powerplantsystem.entity.Battery;
import com.powerplantsystem.service.BatteryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/powerplant")
public class PowerPlantController {
    @Autowired
    private BatteryServiceImpl batteryService;

    @PostMapping("/battery/add")
    public ResponseEntity<List<Battery>> saveBatteries(@RequestBody List<Battery> batteries) {
        List<Battery> savedBatteryList = batteryService.saveBatteries(batteries);
        return new ResponseEntity<>(savedBatteryList, HttpStatus.OK);
    }

    @GetMapping("/battery/range/postcode")
    public BatteryStat getBatteriesBetweenPostcodeRanges(@RequestParam Long postCodeStart, @RequestParam Long postCodeEnd){
       return batteryService.getBatteriesBetweenPostcodeRanges(postCodeStart,postCodeEnd);
    }
}