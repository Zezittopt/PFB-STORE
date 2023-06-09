package com.kreitek.store.application.service;

import com.kreitek.store.application.dto.ItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<ItemDto> getAllItems();
    List<ItemDto> getAllItemsByCategory(Long categoryId);
    Optional<ItemDto> getItemById(Long itemId);
    ItemDto saveItem(ItemDto itemDto);
    void deleteItem(Long itemId);
    Page<ItemDto> getItemsByCriteriaStringPaged(Pageable pageable, String filter);
}
