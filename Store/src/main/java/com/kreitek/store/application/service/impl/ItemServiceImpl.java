package com.kreitek.store.application.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.dto.ItemShopDTO;
import com.kreitek.store.application.mapper.ItemMapper;
import com.kreitek.store.application.mapper.ItemShopMapper;
import com.kreitek.store.application.service.ItemService;
import com.kreitek.store.domain.entity.Item;
import com.kreitek.store.domain.persistance.ItemPersistance;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemPersistance itemPersistance;
    private final ItemMapper itemMapper;
    private final ItemShopMapper itemShopMapper;

    public ItemServiceImpl(ItemPersistance itemPersistance, ItemMapper itemMapper, ItemShopMapper itemShopMapper) {
        this.itemPersistance = itemPersistance;
        this.itemMapper = itemMapper;
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> items = this.itemPersistance.getAllItems();
        return this.itemMapper.toDto(items);
    }

    @Override
    public List<ItemShopDTO> getAllItemsByCategory(Long categoryId) {
        List<Item> items = this.itemPersistance.getAllItemsByCategory(categoryId);
        return this.itemShopMapper.toDto(items);
    }

    @Override
    public Optional<ItemDTO> getItemById(Long itemId) {
        return this.itemPersistance.getItemById(itemId).map(itemMapper::toDto);
    }

    @Override
    public ItemDTO saveItem(ItemDTO itemDTO) {
        Item itemSaved = this.itemPersistance.saveItem(this.itemMapper.toEntity(itemDTO));
        return this.itemMapper.toDto(itemSaved);
    }

    @Override
    public void deleteItem(Long itemId) {
        this.itemPersistance.deleteItem(itemId);
    }

    @Override
    public Page<ItemDTO> getItemByCriteriaStringPaged(Pageable pageable, String filter) {
        Page<Item> itemPage = this.itemPersistance.findAll(pageable, filter);
        return itemPage.map(itemMapper::toDto);
    }
}
