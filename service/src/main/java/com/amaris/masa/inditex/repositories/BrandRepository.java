package com.amaris.masa.inditex.repositories;

import com.amaris.masa.inditex.datamodel.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
