package com.dallas.service.impl;

import com.dallas.dao.WineDAO;
import com.dallas.exceptions.BizException;
import com.dallas.service.WineService;
import com.dallas.vo.Entity.WineEntity;
import com.dallas.vo.Wine;
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
    public void addWine(Wine wine) {
        WineEntity wineEntity = this.convertToEntity(wine);
        String wineId = UUID.randomUUID().toString();
        wineEntity.setId(wineId);
        wineDAO.addWine(wineEntity);
    }

    @Override
	@Transactional
    public void updateWine(Wine wine) {
        WineEntity wineEntity = this.convertToEntity(wine);
        WineEntity wineSaved = wineDAO.selectWineById(wine.getId());
        if (wineSaved == null) {
            throw new BizException("Can't fine wine");
        }
    	System.out.println("found wine");
    	System.out.println(wineEntity);
    	System.out.println(wineSaved);
        wineDAO.updateWine(wineEntity);
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
