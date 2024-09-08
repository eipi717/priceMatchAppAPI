package com.eipi717.pricematchapi.controller;

import com.eipi717.pricematchapi.dtoConvertor.PriceDTOConvertor;
import com.eipi717.pricematchapi.dtos.PriceDTO;
import com.eipi717.pricematchapi.entity.Price;
import com.eipi717.pricematchapi.response.SelfDefinedResponse;
import com.eipi717.pricematchapi.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/prices")
public class PriceController {
    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }


    @GetMapping(value = "/getAllPrices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllPrice(
            @RequestParam(name = "sortBy", defaultValue = "priceId") String sortBy,
            @RequestParam(name = "orderBy", defaultValue = "DESC") String orderBy
    ) {
        List<Price> pricesList = priceService.getAllPrice(sortBy, orderBy);
        List<PriceDTO> priceDTOList = new ArrayList<>();

        for (Price price : pricesList) {
            priceDTOList.add(PriceDTOConvertor.convert(price));
        }

        SelfDefinedResponse<List<PriceDTO>> selfDefinedResponse = new SelfDefinedResponse();
        selfDefinedResponse.setCount(priceDTOList.size());
        selfDefinedResponse.setData(priceDTOList);
        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/getByProductId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByProductId(
            @RequestParam Long productId,
            @RequestParam(name = "sortBy", defaultValue = "priceId") String sortBy,
            @RequestParam(name = "orderBy", defaultValue = "DESC") String orderBy
    ) {
        List<Price> pricesList = priceService.getByProductId(productId, sortBy, orderBy);
        List<PriceDTO> priceDTOList = new ArrayList<>();

        for (Price price : pricesList) {
            priceDTOList.add(PriceDTOConvertor.convert(price));
        }

        SelfDefinedResponse<List<PriceDTO>> selfDefinedResponse = new SelfDefinedResponse();
        selfDefinedResponse.setData(priceDTOList);
        selfDefinedResponse.setCount(priceDTOList.size());
        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/getByProductName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByProductName(
            @RequestParam String productName,
            @RequestParam(name = "sortBy", defaultValue = "priceId") String sortBy,
            @RequestParam(name = "orderBy", defaultValue = "DESC") String orderBy
    ) {
        List<Price> pricesList = priceService.getByProductName(productName, sortBy, orderBy);
        List<PriceDTO> priceDTOList = new ArrayList<>();

        for (Price price : pricesList) {
            priceDTOList.add(PriceDTOConvertor.convert(price));
        }

        SelfDefinedResponse<List<PriceDTO>> selfDefinedResponse = new SelfDefinedResponse();
        selfDefinedResponse.setData(priceDTOList);
        selfDefinedResponse.setCount(priceDTOList.size());
        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/getByStoreId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByStoreId(
            @RequestParam Long storeId,
            @RequestParam(name = "sortBy", defaultValue = "priceId") String sortBy,
            @RequestParam(name = "orderBy", defaultValue = "DESC") String orderBy
    ) {
        List<Price> pricesList = priceService.getByStoreId(storeId, sortBy, orderBy);
        List<PriceDTO> priceDTOList = new ArrayList<>();

        for (Price price : pricesList) {
            priceDTOList.add(PriceDTOConvertor.convert(price));
        }

        SelfDefinedResponse<List<PriceDTO>> selfDefinedResponse = new SelfDefinedResponse();
        selfDefinedResponse.setData(priceDTOList);
        selfDefinedResponse.setCount(priceDTOList.size());
        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/getByStoreName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByStoreName(
            @RequestParam String storeName,
            @RequestParam(name = "sortBy", defaultValue = "priceId") String sortBy,
            @RequestParam(name = "orderBy", defaultValue = "DESC") String orderBy
    ) {
        List<Price> pricesList = priceService.getByStoreName(storeName, sortBy, orderBy);
        List<PriceDTO> priceDTOList = new ArrayList<>();

        for (Price price : pricesList) {
            priceDTOList.add(PriceDTOConvertor.convert(price));
        }

        SelfDefinedResponse<List<PriceDTO>> selfDefinedResponse = new SelfDefinedResponse();
        selfDefinedResponse.setData(priceDTOList);
        selfDefinedResponse.setCount(priceDTOList.size());
        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/createPrice", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> insertPrice(
            @RequestBody List<PriceDTO> priceDTOList
    ) {
        priceDTOList.forEach(priceService::createPrice);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/deleteById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteById(
            @PathVariable Long priceId
    ) {
        priceService.deletePriceById(priceId);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
