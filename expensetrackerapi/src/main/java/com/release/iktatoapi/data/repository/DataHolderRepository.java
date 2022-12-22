package com.release.iktatoapi.data.repository;



import com.release.iktatoapi.data.entity.DataHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface DataHolderRepository extends JpaRepository<DataHolder, Long> {

    Page<DataHolder> findAll(Pageable pageable);
}
