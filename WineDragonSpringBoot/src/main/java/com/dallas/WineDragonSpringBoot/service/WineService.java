package com.dallas.WineDragonSpringBoot.service;

import com.dallas.WineDragonSpringBoot.vo.Wine;

import java.util.List;

public interface WineService {
    List<Wine> getWineList();
    
    Wine getWineById(String id);

    Wine addWine(Wine wine);

    Wine updateWine(Wine wine);

    void removeWineById(String id);
}
