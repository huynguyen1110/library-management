package com.example.library.code.controllers;

import com.example.library.code.data.sach.GetChiTietSachDto;
import com.example.library.code.data.sach.ThemSachDto;
import com.example.library.code.models.entities.NhaXuatBan;
import com.example.library.code.models.entities.Sach;
import com.example.library.code.models.entities.TacGia;
import com.example.library.code.models.entities.TheLoai;
import com.example.library.code.services.serviceimp.NhaXuatBanService;
import com.example.library.code.services.serviceimp.SachService;
import com.example.library.code.services.serviceimp.TacGiaService;
import com.example.library.code.services.serviceimp.TheLoaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/")
public class SachController {

    private static String UPLOADED_FOLDER = "D:\\Libary Management\\aa\\library-management\\src\\main\\resources\\static\\image\\";
    private static String FILE_EXTENSION = ".jpg";

    @Autowired
    private SachService sachService;

    @Autowired
    private TheLoaiService theLoaiService;

    @Autowired
    private NhaXuatBanService nhaXuatBanService;

    @Autowired
    private TacGiaService tacGiaService;

    @GetMapping("sach-moi")
    public List<Sach> sachMoi() {
        LocalDate ngayKetThuc = LocalDate.now();
        LocalDate ngayBatDau = ngayKetThuc.minusDays(10);
        return  sachService.timSachMoi(ngayBatDau);
    }

    @GetMapping("tat-ca")
    public List<Sach> tatCaSach() {
        return sachService.timTatCa();
    }

    @GetMapping("sach")
    public List<Sach> sachTheoTheLoai(@RequestParam("the-loai") String theLoai) {
        return sachService.timTheLoaiTruyenNgan(theLoai);
    }

    @GetMapping("chi-tiet-sach")
    public GetChiTietSachDto timSachTheoId(@RequestParam("id") int id) {
            return sachService.timSachTheoId(id);
    }

    @GetMapping("them-sach")
    public ModelAndView formThemSach(){
        ModelAndView modelAndView = new ModelAndView("/books/addBook");
        List<TheLoai> theLoais = theLoaiService.danhSachTheLoai();
        List<NhaXuatBan> nhaXuatBans = nhaXuatBanService.danhSachNhaXuatBan();
        List<TacGia> tacGias = tacGiaService.danhSachTacGia();
        Sach sach = new Sach();
        modelAndView.addObject("sach", sach);
        modelAndView.addObject("theLoais", theLoais);
        modelAndView.addObject("nhaxuatBans", nhaXuatBans);
        modelAndView.addObject("tacGias", tacGias);
        return modelAndView;
    }

    @PostMapping("them-sach")
    public ModelAndView themSach(
            @RequestParam("image") MultipartFile image,
            @RequestParam("tenSach") String tenSach,
            @RequestParam("tacGia") int maTacGia,
            @RequestParam("giaTien") double giaSach,
            @RequestParam("soLuong") int soLuong,
            @RequestParam("ngayXuatBan") String ngayXuatBan,
            @RequestParam("nhaXuatBan") int maNhaXuatBan,
            @RequestParam("theLoai") int maTheLoai) throws IOException {

        TheLoai theLoai = theLoaiService.timTheLoaiTheoMa(maTheLoai);
        NhaXuatBan nhaXuatBan = nhaXuatBanService.timNhaXuatBanTheoMa(maNhaXuatBan);
        TacGia tacGia = tacGiaService.timTacGiaTheoMa(maTacGia);
        String imagePath = saveUploadedFiles(image);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ngayXuatBanLD = LocalDate.parse(ngayXuatBan, formatter);

        ModelAndView modelAndView = new ModelAndView("/books/addBook");
        List<TheLoai> theLoais = theLoaiService.danhSachTheLoai();
        List<NhaXuatBan> nhaXuatBans = nhaXuatBanService.danhSachNhaXuatBan();
        List<TacGia> tacGias = tacGiaService.danhSachTacGia();
        Sach sach = new Sach();
        ThemSachDto themSachDto = new ThemSachDto(tenSach, soLuong, ngayXuatBanLD, giaSach, theLoai, nhaXuatBan, imagePath, tacGia);
        sachService.themSach(themSachDto);
        modelAndView.addObject("sach", sach);
        modelAndView.addObject("theLoais", theLoais);
        modelAndView.addObject("nhaxuatBans", nhaXuatBans);
        modelAndView.addObject("tacGias", tacGias);
        return modelAndView;
    }

    private String saveUploadedFiles(MultipartFile file) throws IOException, IOException {
        Random rand = new Random();
        int ranNum = rand.nextInt();
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getName() + ranNum + FILE_EXTENSION);
            Files.write(path, bytes);
//            return file.getOriginalFilename();
            return path.getFileName().toString();
        } else return null;
    }
}
