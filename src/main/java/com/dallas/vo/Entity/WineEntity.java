package com.dallas.vo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WineEntity {

    public String id;

    public String name;

    public String description;

    public String country;

    public Integer quantity;

    public Boolean visibility;

    public Boolean deleted;
    
    
    

}
