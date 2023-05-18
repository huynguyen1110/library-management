package com.example.library.code.repositories;

import com.example.library.code.models.entities.TacGia;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TacGiaRepository extends JpaRepository<TacGia, Integer> {

    @Query("SELECT tg FROM TacGia tg WHERE tg.maTacGia=:id")
    TacGia timTacGiaTheoMa(@Param("id") int id);
}
