package com.example.demo.repository;

import com.example.demo.entities.DemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DemoRepository extends JpaRepository<DemoEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "update demo set wrapper_id=null where wrapper_id=:wrapperId ", nativeQuery = true)
    void deleteWrapperIdByWrapperId(Long wrapperId);

    @Query(value = "select *from demo where wrapper_id is null", nativeQuery = true)
    List<DemoEntity> getNullWrapperEnteries();

    @Query(value = "select * from demo where id not in (:ids)", nativeQuery = true)
    List<DemoEntity> getAllDemo(@Param("ids") List<Integer> ids);

    @Modifying
    @Transactional
    @Query(value = "update demo set wrapper_id = wrapper_id + 1 where id " +
            "in :Ids", nativeQuery = true)
    void incrementExamCatOrdersByOneByExamCategoryIds(@Param("Ids") List<Long> Ids);

    @Modifying
    @Transactional
    @Query(value = "delete from demo where wrapper_id=10", nativeQuery = true)
    void delete();
}
