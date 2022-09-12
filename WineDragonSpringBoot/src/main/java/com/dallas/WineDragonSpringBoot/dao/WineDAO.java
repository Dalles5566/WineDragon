package com.dallas.WineDragonSpringBoot.dao;

import java.util.List;

import com.dallas.WineDragonSpringBoot.vo.Wine;
import com.dallas.WineDragonSpringBoot.vo.Entity.WineEntity;

public interface WineDAO {
    List<WineEntity> selectWineList();
    
    WineEntity selectWineById(String id);

    void addWine(WineEntity wine);

    void updateWine(WineEntity wine);

    void removeWineById(String id);
}
