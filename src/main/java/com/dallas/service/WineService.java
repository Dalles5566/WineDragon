package com.dallas.service;

import com.dallas.vo.Wine;

import java.util.List;

public interface WineService {
    List<Wine> getWineList();
    
    Wine getWineById(String id);

    void addWine(Wine wine);

    void updateWine(Wine wine);

    void removeWineById(String id);
}
