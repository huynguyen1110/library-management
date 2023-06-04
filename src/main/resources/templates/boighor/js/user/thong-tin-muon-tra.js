var urlParams = new URLSearchParams(window.location.search);
var maTk = urlParams.get('maTk');
function hienThiThonTinPhieuMuonTra() {
    var result = ``
    var stt = 0
    $.ajax({
        url: '/api/v1/lay-phieu-muon-tra?maTk=' + maTk,
        method: 'GET',
        success: function (response) {
            var phieuMuonTraBody = $(".phieuMuonTraBody")
            phieuMuonTraBody.empty()
            response.forEach(function (item) {
                stt += 1
                result = `<tr>
                <th scope="row">${stt}</th>
                <td>${item.maPhieuMuon}</td>
                <td>${item.maPhieuTra}</td>
                <td>${ convertToDateTimeFormatted(item.ngayMuon)}</td>
                <td>${ convertToDateTimeFormatted(item.ngayTra)}</td>
                <td>${item.soLuong}</td>
                <td>${item.tongTien}</td>
                <td>${item.trangThaiMuon}</td>
                <td>${item.trangThaiTra}</td>
                <td ><a type="button" onclick="xemChiTietBtn(${item.maPhieuMuon}, '${item.maPhieuTra}')">Xem chi tiết</a></td>
                </tr>`
                phieuMuonTraBody.append(result)
            })
        },
        error: function () {
            console.log('Đã xảy ra lỗi khi gọi API.');
        }
    });
}

hienThiThonTinPhieuMuonTra()

function convertToDateTimeFormatted(dateTimeString) {
    var dateTime = new Date(dateTimeString);
    var day = dateTime.getDate();
    var month = dateTime.getMonth() + 1;
    var year = dateTime.getFullYear();

    // Định dạng ngày-tháng-năm
    var formattedDateTime = day + '-' + month + '-' + year;

    return formattedDateTime;
}

function thongTinCaNhanBtn() {
    window.location.href = '/api/v1/info-update?maTk=' + maTk
}

function troLaiTrangChu() {
    window.location.href = '/api/v1/index?id-nguoi-dung=' + maTk;
}

function lichSuMuonTraBtn() {
    window.location.href = '/api/v1/thong-tin-muon-tra?maTk=' + maTk;
}

function capNhatTrangThai() {
    window.location.href = '/api/v1/thong-tin-muon-tra?maTk=' + maTk
}

function xemChiTietBtn(maPhieuMuon, maPhieuTra) {
    var result = ``
    $.ajax({
        url: '/api/v1/lay-phieu-muon-tra?maTk=' + maTk,
        method: 'GET',
        success: function (response) {
            var thongTinSachBody = $(".thongTinSachBody")
            thongTinSachBody.empty()
            response.forEach(function (item) {
                if (maPhieuMuon == item.maPhieuMuon && maPhieuTra == item.maPhieuTra) {
                    item.sachMuon.forEach(function (sachMuon) {
                        result = `<tr>
                                <td>${item.maPhieuMuon}</td>
                                <td>${sachMuon.maSach}</td>
                                <td>${sachMuon.tenSach}</td>
                                <td>${sachMuon.giaTien}</td>
                                <td>${sachMuon.theLoai.tenTheLoai}</td>
                                <td>${sachMuon.nhaXuatBan.tenNhaXuatBan}</td>
                                <td>${sachMuon.tacGia.ten}</td>
                              </tr>`
                        thongTinSachBody.append(result)
                    })
                }
            })
        },
        error: function () {
            console.log('Đã xảy ra lỗi khi gọi API.');
        }
    });
    $(".modal-content").show()
}

function closeChiTietSach() {
    $(".modal-content").hide()
}

