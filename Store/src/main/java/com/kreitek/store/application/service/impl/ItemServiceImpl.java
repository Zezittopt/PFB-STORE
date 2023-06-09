package com.kreitek.store.application.service.impl;

import com.kreitek.store.application.dto.ItemDto;
import com.kreitek.store.application.mapper.ItemMapper;
import com.kreitek.store.application.service.ItemService;
import com.kreitek.store.domain.entity.Item;
import com.kreitek.store.domain.persistence.ItemPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemPersistence persistence;
    private final ItemMapper mapper;

    @Autowired
    public ItemServiceImpl(ItemPersistence persistence, ItemMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    public List<ItemDto> getAllItems() {
        List<Item> items = this.persistence.getAllItems();
        return this.mapper.toDto(items);
    }

    @Override
    public List<ItemDto> getAllItemsByCategory(Long categoryId) {
        List<Item> items = this.persistence.getAllItemsByCategory(categoryId);
        return this.mapper.toDto(items);
    }

    @Override
    public Optional<ItemDto> getItemById(Long itemId) {
        return this.persistence.getItemById(itemId).map(this.mapper::toDto);
    }

    @Override
    public ItemDto saveItem(ItemDto itemDto) {
        Item itemSaved = this.persistence.saveItem(this.mapper.toEntity(itemDto));
        return this.mapper.toDto(itemSaved);
    }

    @Override
    public void deleteItem(Long itemId) {
        this.persistence.deleteItem(itemId);
    }

    @Override
    public Page<ItemDto> getItemsByCriteriaStringPaged(Pageable pageable, String filter) {
        Page<Item> itemPage = this.persistence.findAll(pageable, filter);
        return itemPage.map(mapper::toDto);
    }
}
