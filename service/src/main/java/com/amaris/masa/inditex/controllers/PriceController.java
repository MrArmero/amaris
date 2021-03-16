package com.amaris.masa.inditex.controllers;

import com.amaris.masa.inditex.datamodel.Price;
import com.amaris.masa.inditex.dtos.PriceDTO;
import com.amaris.masa.inditex.exceptions.RecordNotFoundException;
import com.amaris.masa.inditex.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Este controller provee los precios
 */
@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    /**
     * Obtiene un precio con una llamada GET
     * @param date
     * @param productId
     * @param brandId
     * @return
     * @throws RecordNotFoundException
     */
    @GetMapping(value = "/find/{date}/{productId}/{brandId}", produces = "application/json")
    public ResponseEntity<PriceDTO> getPriceByGet(@PathVariable(value = "date") @DateTimeFormat(pattern="yyyy-MM-dd-hh.mm.ss") Date date,
                                             @PathVariable(value = "productId") Integer productId,
                                             @PathVariable(value = "brandId") Integer brandId) throws RecordNotFoundException {

        return new ResponseEntity<PriceDTO>(priceService.getPriceByDateProductAndBrand(date, productId, brandId), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Obtiene un precio con una llamada POST
     * @param date
     * @param productId
     * @param brandId
     * @return
     * @throws RecordNotFoundException
     */
    @PostMapping(value = "/find", produces = "application/json")
    public ResponseEntity<PriceDTO> getPriceByPost(@RequestParam(value = "date") @DateTimeFormat(pattern="yyyy-MM-dd-hh.mm.ss") Date date,
                                                   @RequestParam(value = "productId") Integer productId,
                                                   @RequestParam(value = "brandId") Integer brandId) throws RecordNotFoundException {

        return new ResponseEntity<PriceDTO>(priceService.getPriceByDateProductAndBrand(date, productId, brandId), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * @return la lista de precios almacenados en la base de datos.
     */
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<PriceDTO>> getPrices() {
        return new ResponseEntity<List<PriceDTO>>(priceService.getAll(), new HttpHeaders(), HttpStatus.OK);
    }

}
