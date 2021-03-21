package com.amaris.masa.inditex.repositories;

import com.amaris.masa.inditex.datamodel.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long>  {

    /**
     * HQL para obtener una lista de precios dado un brand, product y fecha.
     * Devolviendo primero aquellos que tienen una prioridad más alta.
     */
    @Query("SELECT p FROM Price p WHERE p.productId=?2 AND p.brandId=?3 and p.startDate<=?1 and p.endDate>=?1 ORDER BY p.priority DESC")
    List<Price> getPriceByDateProductAndBrand(LocalDateTime date, int productId, int brandId);

    /**
     * Devuelve una lista de precios activos a partir de la fecha dada, para el producto y cadena requeridos.
     * @param date fecha a partir de la cual devuelve los precios activos
     * @param productId producto
     * @param brandId cadena
     * @return lista de precios válidos
     */
    @Query("SELECT p FROM Price p WHERE p.productId=?2 AND p.brandId=?3 and p.endDate>=?1")
    List<Price> getNextPrices(LocalDateTime date, int productId, int brandId);
}
