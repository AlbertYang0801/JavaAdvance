package com.albert.redis.controller;

import com.albert.redis.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * set集合实战 - 景点预约
 * @author yangjunwei
 * @date 2021/7/18 9:53 下午
 */
@RestController("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/add")
    public void addReservation(@RequestParam("AttractionName") String attractionName,
                               @RequestParam("userId") String userId){
        reservationService.add(attractionName,userId);
    }




}
