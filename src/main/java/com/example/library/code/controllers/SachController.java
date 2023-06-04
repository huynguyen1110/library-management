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
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    private static String UPLOADED_FOLDER = "E:\\Tai_lieu\\Ky_hai_nam_ba\\cong_nghe_phan_mem\\do_an\\code\\src\\main\\resources\\static\\image\\";

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

    @GetMapping("sach-theo-the-loai")
    public List<Sach> sachTheoTheLoai(@RequestParam("the-loai") String theLoai) {
        return sachService.timTheLoaiTruyenNgan(theLoai);
    }

    @GetMapping("chi-tiet-sach")
    public GetChiTietSachDto timSachTheoId(@RequestParam("id") int id) {
            return sachService.timSachTheoId(id);
    }

    @GetMapping("admin/them-sach")
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

    @PostMapping("admin/them-sach")
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
            return path.getFileName().toString();
        } else return null;
    }

    @GetMapping("admin/danh-sach")
    private ModelAndView danhSach(){
        ModelAndView model = new ModelAndView("books/listBook");
        List<Sach> books = sachService.adminTimTatCaSach();
        model.addObject("books", books);
        return model;
    }

    @GetMapping ("admin/xoa-sach/{id}")
    private ModelAndView xoaSach(@PathVariable("id") int id){
        Sach sach = sachService.xoaSach(id);
        ModelAndView model = new ModelAndView("books/listBook");
        List<Sach> books = sachService.timTatCa();
        model.addObject("books", books);
        return model;
    }

    @GetMapping("admin/capnhap-sach/{id}")
    private ModelAndView giaoDienCapNhap(@PathVariable("id") int id){
        GetChiTietSachDto sach = sachService.timSachTheoId(id);
        List<TheLoai> theLoais = theLoaiService.danhSachTheLoai();
        List<NhaXuatBan> nhaXuatBans = nhaXuatBanService.danhSachNhaXuatBan();
        List<TacGia> tacGias = tacGiaService.danhSachTacGia();
        ModelAndView model = new ModelAndView("books/updateBook");
        model.addObject("sach", sach);
        model.addObject("theLoais", theLoais);
        model.addObject("nhaxuatBans", nhaXuatBans);
        model.addObject("tacGias", tacGias);
        return model;
    }

    @PostMapping("admin/capnhap-sach/{id}")
    private ModelAndView capNhapSach(
            @PathVariable("id") int id,
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
        ThemSachDto themSachDto = new ThemSachDto(tenSach, soLuong, ngayXuatBanLD, giaSach, theLoai, nhaXuatBan, imagePath, tacGia);
        sachService.capNhap(id, themSachDto);
        ModelAndView model = new ModelAndView("redirect:/api/v1/admin/danh-sach");
        List<Sach> books = sachService.timTatCa();
        model.addObject("books", books);
        return model;
    }

    @GetMapping("lay-sach-phantrang")
    public Page<Sach> laySachCoPhanTrang(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "tenSach") String orderBy) {
        return sachService.timTatCaSachCoPhanTrang(pageNumber, orderBy);
    }

    @GetMapping("lay-sach-phantrang-theo-theloai")
    public Page<Sach> laySachCoPhanTrangTheoTheLoai(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "tenSach") String orderBy, @RequestParam String theLoai) {
        return sachService.laySachTheoTheLoaiCoPhanTrang(pageNumber, theLoai, orderBy);
    }

    @GetMapping("admin/them-the-loai")
    public ModelAndView theTheLoaiPage() {
        ModelAndView modelAndView = new ModelAndView("/categories/addCategori");
        return modelAndView;
    }
}
