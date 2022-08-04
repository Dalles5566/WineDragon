package com.dallas.controllers;

import com.dallas.exceptions.BizException;
import com.dallas.service.WineService;
import com.dallas.vo.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/wines/")
public class WineController {

    //  8/1/22 D-Note: get connection with WineService
    @Autowired
    WineService wineService;

    //  Frontend called http://localhost:8080/WineDragon_war/wine/ with a "PUT" request will "trigger" this
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Wine>> getWineList() {
        //  8/1/22 D-Note: use getWineList() and retreat a list
        List<Wine> wineList = wineService.getWineList();
        //  if the list is Empty and  equalToNull, tell front end that NO_CONTENT
        if(wineList.isEmpty()&& wineList == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        //  if the list has something(means it found objects from database, it will set the wineList, and an OK_Status to the frontend.
        return new ResponseEntity<>(wineList,HttpStatus.OK);
    }

    //  Frontend called http://localhost:8080/WineDragon_war/ with a "POST" request will "trigger" this
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Wine> addWine(@RequestBody Wine wine) {
        //  simple check if the frontend has provided a wine object. (to save a few steps if wine
        if (wine == null) {
            throw new BizException("Wine object can't be null");
        }
        Wine isSaved = wineService.addWine(wine);
        if (isSaved == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(isSaved, HttpStatus.OK);
    }

    //  Frontend called http://localhost:8080/WineDragon_war/wine/ with a "PUT" request will "trigger" this
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<Wine> updateWine(@RequestBody Wine wine){
        if (wine == null){
            throw new BizException("Wine object can't be null");
        }
        Wine isUpdated = wineService.updateWine(wine);
        if (isUpdated == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(isUpdated, HttpStatus.OK);
    }

    //  Frontend called http://localhost:8080/WineDragon_war/wine/{id} with a "Delete" request will "trigger" this
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Wine> deleteWine(@PathVariable("id") String wineId){
        wineService.removeWineById(wineId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
