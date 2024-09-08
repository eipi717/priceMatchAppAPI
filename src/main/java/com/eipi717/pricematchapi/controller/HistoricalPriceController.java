package com.eipi717.pricematchapi.controller;

import com.eipi717.pricematchapi.dtoConvertor.HistoricalDataDTOConvertor;
import com.eipi717.pricematchapi.dtos.HistoricalDataDTO;
import com.eipi717.pricematchapi.entity.HistoricalData;
import com.eipi717.pricematchapi.response.SelfDefinedResponse;
import com.eipi717.pricematchapi.service.HistoricalPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/historicalPrice")
public class HistoricalPriceController {
    private final HistoricalPriceService historicalPriceService;

    @Autowired
    public HistoricalPriceController(HistoricalPriceService historicalPriceService) {
        this.historicalPriceService = historicalPriceService;
    }

    @GetMapping(value = "/getAllHistoricalPrices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllHistoricalPrices(
            @RequestParam(name = "sortBy", defaultValue = "dataId") String sortBy,
            @RequestParam(name = "orderBy", defaultValue = "DESC") String orderBy
    ) {
        List<HistoricalData> historicalDataList = historicalPriceService.findAllHistoricalData(sortBy, orderBy);
        List<HistoricalDataDTO> historicalDataDTOList = new ArrayList<>();
        for (HistoricalData historicalData : historicalDataList) {
            historicalDataDTOList.add(HistoricalDataDTOConvertor.convert(historicalData));
        }

        SelfDefinedResponse<List<HistoricalDataDTO>> selfDefinedResponse = new SelfDefinedResponse();
        selfDefinedResponse.setData(historicalDataDTOList);

        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/getByProductId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByProductId(
            @RequestParam Long productId,
            @RequestParam(name = "sortBy", defaultValue = "dataId") String sortBy,
            @RequestParam(name = "orderBy", defaultValue = "DESC") String orderBy
    ) {
        System.out.println(productId);
        List<HistoricalData> historicalDataList = historicalPriceService.getByProductId(productId, sortBy, orderBy);
        List<HistoricalDataDTO> historicalDataDTOList = new ArrayList<>();

        for (HistoricalData historicalData : historicalDataList) {
            historicalDataDTOList.add(HistoricalDataDTOConvertor.convert(historicalData));
        }

        SelfDefinedResponse<List<HistoricalDataDTO>> selfDefinedResponse = new SelfDefinedResponse();
        selfDefinedResponse.setData(historicalDataDTOList);
        selfDefinedResponse.setCount(historicalDataDTOList.size());

        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/getByProductName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByProductName(
            @RequestParam String productName,
            @RequestParam(name = "sortBy", defaultValue = "priceId") String sortBy,
            @RequestParam(name = "orderBy", defaultValue = "DESC") String orderBy
    ) {
        List<HistoricalData> historicalDataList = historicalPriceService.getByProductName(productName, sortBy, orderBy);
        List<HistoricalDataDTO> historicalDataDTOList = new ArrayList<>();

        for (HistoricalData historicalData : historicalDataList) {
            historicalDataDTOList.add(HistoricalDataDTOConvertor.convert(historicalData));
        }

        SelfDefinedResponse<List<HistoricalDataDTO>> selfDefinedResponse = new SelfDefinedResponse();
        selfDefinedResponse.setData(historicalDataDTOList);
        selfDefinedResponse.setCount(historicalDataDTOList.size());

        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/getByStoreId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByStoreId(
            @RequestParam Long storeId,
            @RequestParam(name = "sortBy", defaultValue = "priceId") String sortBy,
            @RequestParam(name = "orderBy", defaultValue = "DESC") String orderBy
    ) {
        List<HistoricalData> historicalDataList = historicalPriceService.getByStoreId(storeId, sortBy, orderBy);
        List<HistoricalDataDTO> historicalDataDTOList = new ArrayList<>();

        for (HistoricalData historicalData : historicalDataList) {
            historicalDataDTOList.add(HistoricalDataDTOConvertor.convert(historicalData));
        }

        SelfDefinedResponse<List<HistoricalDataDTO>> selfDefinedResponse = new SelfDefinedResponse();
        selfDefinedResponse.setData(historicalDataDTOList);
        selfDefinedResponse.setCount(historicalDataDTOList.size());

        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/getByStoreName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByStoreName(
            @RequestParam String storeName,
            @RequestParam(name = "sortBy", defaultValue = "priceId") String sortBy,
            @RequestParam(name = "orderBy", defaultValue = "DESC") String orderBy
    ) {
        List<HistoricalData> historicalDataList = historicalPriceService.getByStoreName(storeName, sortBy, orderBy);
        List<HistoricalDataDTO> historicalDataDTOList = new ArrayList<>();

        for (HistoricalData historicalData : historicalDataList) {
            historicalDataDTOList.add(HistoricalDataDTOConvertor.convert(historicalData));
        }

        SelfDefinedResponse<List<HistoricalDataDTO>> selfDefinedResponse = new SelfDefinedResponse();
        selfDefinedResponse.setData(historicalDataDTOList);
        selfDefinedResponse.setCount(historicalDataDTOList.size());

        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/createHistoricalPrice", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createHistoricalPrice(
            @RequestBody List<HistoricalDataDTO> historicalDataDTOList
    ) {
        historicalDataDTOList.forEach(historicalPriceService::createHistoricalData);

        return new ResponseEntity<>("", HttpStatus.CREATED);
    }
}
