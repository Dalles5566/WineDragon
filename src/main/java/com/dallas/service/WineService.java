package com.dallas.service;

import com.dallas.vo.Wine;

import java.util.List;

public interface WineService {
    List<Wine> getWineList();

    Wine addWine(Wine wine);

    Wine updateWine(Wine wine);

    Boolean removeWineById(String id);
}
