package com.java.web.api.web.controller.dto;


import com.java.domain.ItemCat;
import lombok.Data;

@Data
public class ItemCatDTO {
    private Long id;
    private String name;
    private ItemCatDTO parentItemCat;
}
