package com.example.library.code.repositories;

import com.example.library.code.models.entities.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Integer> {

    @Query("select g from GioHang g where g.docGia.maDocGia = :id")
    GioHang findGioHangByMaDocGia(@Param("id")int id);

    @Query("select g from GioHang g left join fetch g.sachs s join fetch g.docGia d  where d.taiKhoan.maTk = :id")
    GioHang findGioHangByMaTK(@Param("id") int id);
}
