package com.example.library.code.repositories;

import com.example.library.code.models.entities.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Integer> {

    @Query("select g from GioHang g where g.docGia.maDocGia = :id")
    GioHang findGioHangByMaDocGia(@Param("id")int id);

    @Query("select g from GioHang g left join fetch g.sachs s join fetch g.docGia d  where d.taiKhoan.maTk = :id")
    GioHang findGioHangByMaTK(@Param("id") int id);

    @Modifying
    @Query(value = "DELETE FROM gio_hang_sach " +
            "WHERE ma_gio_hang  IN ( " +
            "    SELECT gh.ma_gio_hang " +
            "    FROM gio_hang gh " +
            "    INNER JOIN doc_gia dg ON gh.doc_gia_ma_doc_gia = dg.ma_doc_gia " +
            "    WHERE dg.tai_khoan_ma_tk = :taiKhoanId " +
            ") " +
            "AND ma_sach = :sachId ", nativeQuery = true)
    void removeSachFromGioHangByMaSach(@Param("sachId") int sachId, @Param("taiKhoanId") int taiKhoanId);

    @Modifying
    @Query("DELETE FROM GioHang gh WHERE gh.docGia.maDocGia = :maDocGia")
    void deleteByMaDocGia(int maDocGia);
}
