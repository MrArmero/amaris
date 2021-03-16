package com.amaris.masa.inditex.repositories;

import com.amaris.masa.inditex.datamodel.Price;
import com.amaris.masa.inditex.utils.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long>  {

    @Query(Constants.QUERY_TO_GET_PRICE_BY_DATE_BRAND_AND_PRODUCT)
    List<Price> getPriceByDateProductAndBrand(Date date, int productId, int brandId);

}
