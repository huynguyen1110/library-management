package com.example.library.code.repositories;

import com.example.library.code.models.entities.TacGia;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacGiaRepository extends JpaRepository<TacGia, Integer> {
}
