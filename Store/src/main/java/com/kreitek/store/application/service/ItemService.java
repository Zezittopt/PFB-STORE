package com.kreitek.store.application.service;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.dto.ItemShopDTO;
import com.kreitek.store.domain.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<ItemDTO> getAllItems();
    List<ItemShopDTO> getAllItemsByCategory(Long categoryId);
    Optional<ItemDTO> getItemById(Long itemId);
    ItemDTO saveItem(ItemDTO item);
    void deleteItem(Long itemId);
    Page<ItemDTO> getItemByCriteriaStringPaged(Pageable pageable, String filter);
}
