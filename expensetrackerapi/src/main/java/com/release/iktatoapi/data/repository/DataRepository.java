package com.release.iktatoapi.data.repository;

import com.release.iktatoapi.data.entity.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {

    @Query(value = "select * from tbl_data s where s.data_hlyrsz like %:keyword% or s.data_irsz like %:keyword% or s.data_varos like %:keyword% or s.data_megbizo like %:keyword% or s.ig_category like %:keyword%", nativeQuery = true)
    List<Data> findByKeyword(@Param("keyword") String hszNum);

    Page<Data> findByUserId(Long userId, Pageable page);
    Optional<Data> findByUserIdAndId(Long userId, Long expenseId);
}
