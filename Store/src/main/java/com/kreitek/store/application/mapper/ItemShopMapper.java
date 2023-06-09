package com.kreitek.store.application.mapper;


import org.mapstruct.Mapper;
import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.dto.ItemShopDTO;
import com.kreitek.store.domain.entity.Item;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ItemShopMapper extends EntityMapper<ItemShopDTO, Item> {
}
