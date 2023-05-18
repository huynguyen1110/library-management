package com.example.library.code.repositories;

import com.example.library.code.models.entities.NhaXuatBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NhaXuatBanRepository extends JpaRepository<NhaXuatBan, Integer> {

    @Query("SELECT nxb FROM NhaXuatBan nxb WHERE nxb.maNhaXuatBan =:id")
    NhaXuatBan timNhaXuatBanTheoMa(@Param("id") Integer id);
}
