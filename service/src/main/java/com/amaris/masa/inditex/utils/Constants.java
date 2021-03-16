package com.amaris.masa.inditex.utils;

public class Constants {

    /**
     * HQL para obtener un precio  dado un brand, product y fecha.
     * Devolviendo primero aquellos que tienen una prioridad más alta
     */
    public static final String QUERY_TO_GET_PRICE_BY_DATE_BRAND_AND_PRODUCT = "SELECT p FROM Price p " +
            "WHERE p.product.id=?2 AND p.brand.id=?3 and p.startDate<=?1 and p.endDate>=?1 " +
            "ORDER BY p.priority DESC";

}
