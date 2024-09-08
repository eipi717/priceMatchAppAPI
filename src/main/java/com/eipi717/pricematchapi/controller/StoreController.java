package com.eipi717.pricematchapi.controller;

import com.eipi717.pricematchapi.dtoConvertor.StoreDTOConvertor;
import com.eipi717.pricematchapi.dtos.StoreDTO;
import com.eipi717.pricematchapi.entity.Store;
import com.eipi717.pricematchapi.response.SelfDefinedResponse;
import com.eipi717.pricematchapi.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stores")
public class StoreController {
    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping(value = "/getAllStores", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllStores(
            @RequestParam(name = "sortBy", defaultValue = "storeId") String sortBy,
            @RequestParam(name = "orderBy", defaultValue = "DESC") String orderBy
    ) {

        SelfDefinedResponse<List<StoreDTO>> selfDefinedResponse = new SelfDefinedResponse();
        List<StoreDTO> storeDTOList = new ArrayList<>();
        List<Store> storeList = storeService.findAllStore(sortBy, orderBy);
        for (Store store: storeList) {
            storeDTOList.add(StoreDTOConvertor.convert(store));
        }
        selfDefinedResponse.setData(storeDTOList);
        selfDefinedResponse.setCount(storeList.size());
        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }

    @GetMapping("/findStoreByStoreName")
    public ResponseEntity<?> getAllStores(
            @RequestParam String storeName
    ) {
        return new ResponseEntity<>(storeService.findByStoreName(storeName), HttpStatus.OK);
    }

    @PostMapping("/createStore")
    public ResponseEntity<?> createStore(
            @RequestBody StoreDTO storeDTO
    ) {
        storeService.createStore(storeDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteStore/{storeId}")
    public ResponseEntity<?> deleteStore(
            @PathVariable Long storeId
    ) {
        storeService.deleteStore(storeId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
