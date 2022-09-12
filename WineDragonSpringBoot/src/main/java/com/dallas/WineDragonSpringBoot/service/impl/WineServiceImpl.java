package com.dallas.WineDragonSpringBoot.service.impl;

import com.dallas.WineDragonSpringBoot.dao.WineDAO;
import com.dallas.WineDragonSpringBoot.exceptions.BizException;
import com.dallas.WineDragonSpringBoot.service.WineService;
import com.dallas.WineDragonSpringBoot.vo.Entity.WineEntity;
import com.dallas.WineDragonSpringBoot.vo.Wine;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import javax.transaction.Transactional;

@Service
public class WineServiceImpl implements WineService {

    @Autowired
    private WineDAO wineDAO;

    @Override
    @Transactional
    public List<Wine> getWineList() {
        List<WineEntity> WineEntityList = wineDAO.selectWineList();
        List<Wine> wineList = new ArrayList();
        for (WineEntity wineEntity : WineEntityList) {
            wineList.add(this.convertToVO(wineEntity));
        }
        //  this might not work
//        List<Wine> collect = WineEntityList.stream().map(this::convertToVO).collect(Collectors.toList());
        return wineList;
    }

    @Override
	@Transactional
    public Wine getWineById(String id) {
        WineEntity wineSaved = wineDAO.selectWineById(id);
        if (wineSaved == null) {
            throw new BizException("Can't fine wine");
        }
        return convertToVO(wineSaved);
    }

    @Override
	@Transactional
    public Wine addWine(Wine wine) {
        WineEntity wineEntity = this.convertToEntity(wine);
        String wineId = UUID.randomUUID().toString();
        wineEntity.setId(wineId);
        wineDAO.addWine(wineEntity);
        return this.getWineById(wineId);
    }

    @Override
	@Transactional
    public Wine updateWine(Wine wine) {
        WineEntity wineEntity = this.convertToEntity(wine);
        WineEntity wineSaved = wineDAO.selectWineById(wine.getId());
        if (wineSaved == null) {
            throw new BizException("Can't fine wine");
        }
        wineDAO.updateWine(wineEntity);
        return this.getWineById(wineEntity.getId());
    }

    @Override
	@Transactional
    public void removeWineById(String id) {
        wineDAO.removeWineById(id);
    }

    // ----------------
    private Wine convertToVO(WineEntity wineEntity) {
        if (wineEntity == null) {
            throw new BizException("Wine is not found");
        }
        Wine VO = new Wine();
        BeanUtils.copyProperties(wineEntity, VO);
        return VO;
    }

    private WineEntity convertToEntity(Wine wine) {
        if (wine == null) {
            throw new BizException("Wine is not found");
        }
        WineEntity wineEntity = new WineEntity();
        BeanUtils.copyProperties(wine, wineEntity);
        return wineEntity;
    }

}
