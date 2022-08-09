package com.dallas.service.impl;

import com.dallas.exceptions.BizException;
import com.dallas.service.WineService;
import com.dallas.vo.Entity.WineEntity;
import com.dallas.vo.Wine;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WineServiceImpl implements WineService {

    private static HashMap<String, WineEntity> theWineList;

    static{
            theWineList = new HashMap<>();
            String wineOneUUID = UUID.randomUUID().toString();
            theWineList.put(wineOneUUID, new WineEntity(wineOneUUID, "wineOne", "First Wine", "USA", 100, true, false));
            String wineTwoUUID = UUID.randomUUID().toString();
            theWineList.put(wineTwoUUID, new WineEntity(wineTwoUUID, "wineTwo", "", "USA", 100, true, false));
            String wineThreeUUID = UUID.randomUUID().toString();
            theWineList.put(wineThreeUUID, new WineEntity(wineThreeUUID, "wineThree", "", "USA", 100, true, false));
            String wineFourUUID = UUID.randomUUID().toString();
            theWineList.put(wineFourUUID, new WineEntity(wineFourUUID, "wineFour", "Forth Wine", "USA", 100, true, false));
    }

    @Override
    public List<Wine> getWineList() {
        List<Wine> wineList = new ArrayList<>();
        for (String key : theWineList.keySet()) {
            wineList.add(convertToVO(theWineList.get(key)));
        }

        return wineList;
    }

    @Override
    public Wine addWine(Wine wine) {
        WineEntity wineEntity = this.convertToEntity(wine);
        wineEntity.setId(UUID.randomUUID().toString());
        theWineList.put(wineEntity.getId(), wineEntity);
        return convertToVO(wineEntity);
    }

    @Override
    public Wine updateWine(Wine wine) {
        WineEntity wineEntity = this.convertToEntity(wine);
        WineEntity wineSaved = theWineList.get(wineEntity.getId());
        if (wineSaved == null){
             throw new BizException("Can't fine wine");
        }
        theWineList.put(wineEntity.getId(), wineEntity);
        return convertToVO(wineEntity);
    }

    @Override
    public Boolean removeWineById(String id) {
        //  In task 2, I will change the "deleted" properties from false to true instead of remove the object from database.
        theWineList.remove(id);
        return true;
    }


    //----
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
