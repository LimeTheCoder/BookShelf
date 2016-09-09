package com.limethecoder.data.repository;

import com.limethecoder.data.domain.ZipCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipCodeRepository extends JpaRepository<ZipCode, Integer> {
    ZipCode findByCity(String city);
}
