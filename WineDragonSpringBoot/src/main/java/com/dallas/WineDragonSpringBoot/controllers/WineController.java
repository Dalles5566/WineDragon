package com.dallas.WineDragonSpringBoot.controllers;

import com.dallas.WineDragonSpringBoot.exceptions.BizException;
import com.dallas.WineDragonSpringBoot.service.WineService;
import com.dallas.WineDragonSpringBoot.vo.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wines")
public class WineController {

    @Autowired
    WineService wineService;

    @GetMapping("")
    public ResponseEntity<List<Wine>> getWineList() {
        List<Wine> wineList = wineService.getWineList();
        return new ResponseEntity<>(wineList, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Wine> addWine(@RequestBody Wine wine) {
        if (wine == null) {
            throw new BizException("Wine object can't be null");
        }
        return ResponseEntity.status(HttpStatus.OK).body(wineService.addWine(wine));
    }

    @PutMapping("")
    public ResponseEntity<Wine> updateWine(@RequestBody Wine wine) {
        if (wine == null) {
            throw new BizException("Wine object can't be null");
        }
        return ResponseEntity.status(HttpStatus.OK).body(wineService.updateWine(wine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Wine> deleteWine(@PathVariable("id") String wineId) {
        wineService.removeWineById(wineId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
