package com.kreitek.store.infrastructure.rest;

import com.kreitek.store.application.dto.ItemDto;
import com.kreitek.store.application.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemRestController {
    private ItemService itemService;

    @Autowired
    public ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @CrossOrigin
    @GetMapping(value = "/items-old")
    public ResponseEntity<List<ItemDto>> getAllItems() {
        List<ItemDto> itemDtos = this.itemService.getAllItems();
        return new ResponseEntity<>(itemDtos, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/categories/{idCategory}/items")
    public ResponseEntity<List<ItemDto>> getAllItemsFromCategory(@PathVariable Long idCategory) {
        List<ItemDto> itemDtos = this.itemService.getAllItemsByCategory(idCategory);
        return new ResponseEntity<>(itemDtos, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/items", produces = "application/json")
    public ResponseEntity<Page<ItemDto>> getItemsByCriteriaPage(@RequestParam(required = false,value = "filter") String filter, Pageable pageable) {
        Page<ItemDto> items = this.itemService.getItemsByCriteriaStringPaged(pageable, filter);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/items", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ItemDto> insertItem(@RequestBody ItemDto itemDto) {
        ItemDto itemSaved = this.itemService.saveItem(itemDto);
        return new ResponseEntity<>(itemSaved, HttpStatus.CREATED);
    }

    @CrossOrigin
    @PatchMapping(value = "/items", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ItemDto> updateItem(@RequestBody ItemDto itemDto) {
        ItemDto itemUpdated = this.itemService.saveItem(itemDto);
        return new ResponseEntity<>(itemUpdated, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/items/{itemId}")
    public ResponseEntity<?> deleteItemById(@PathVariable Long itemId) {
        this.itemService.deleteItem(itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/items/{itemId}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable Long itemId) {
        Optional<ItemDto> itemDto = this.itemService.getItemById(itemId);

        if (itemDto.isPresent()) {
            return new ResponseEntity<>(itemDto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
