package com.dallas.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wine {

    public String id;

    public String name;

    public String description;

    public String country;

    public Integer quantity;

    public Boolean deleted;
}
