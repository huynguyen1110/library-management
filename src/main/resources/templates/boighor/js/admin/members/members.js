function hienThiListMembers() {
    var listMembersBody = $(".list-of-members")
    var result = ``
    $.ajax({
        url: '/api/v1/admin/find-all-doc-gia',
        method: 'GET',
        success: function(response) {
            listMembersBody.empty()
            response.forEach(function (item) {
                if (item.taiKhoan.daXoa != true) {
                    result = `<tr>
                            <td>${item.maDocGia}</td>
                            <td>${item.ten}</td>
                            <td>${item.diaCHi}</td>
                            <td>${convertToDateTimeFormatted(item.ngaySinh)}</td>
                            <td>${item.sdt}</td>
                            <td>${item.taiKhoan.maTk}</td>
                            <td>
                                <a href="/api/v1/info-update?maTk=${item.taiKhoan.maTk}">Cập nhập</a>
                                <br>
                                <a href="" onclick="deleteDocGia(${item.taiKhoan.maTk})">Xóa</a>
                                <br>
                                <a href="#" onclick="lichSuMuonTra(${item.taiKhoan.maTk})">Lịch sử mượn trả</a>
                            </td>
                           </tr>`
                    listMembersBody.append(result)
                }
            })

            console.log(response);
        },
        error: function(error) {
            console.log(error);
        }
    });
}

hienThiListMembers()

function convertToDateTimeFormatted(dateTimeString) {
    var dateTime = new Date(dateTimeString);
    var day = dateTime.getDate();
    var month = dateTime.getMonth() + 1;
    var year = dateTime.getFullYear();

    // Định dạng ngày-tháng-năm
    var formattedDateTime = day + '-' + month + '-' + year;

    return formattedDateTime;
}

function deleteDocGia(maTk) {
    if (confirm('Bạn có chắc chắn muốn xóa độc giả này không?')) {
        $.ajax({
            url: '/api/v1/admin/xoa-doc-gia?maTk=' + maTk,
            method: 'PUT',
            success: function(response) {
                console.log(response);
            },
            error: function(error) {
                console.log(error);
            }
        });
    }
}

function lichSuMuonTra(maTk) {
    var result = ``
    var stt = 0
    $.ajax({
        url: '/api/v1/lay-phieu-muon-tra?maTk=' + maTk,
        method: 'GET',
        success: function (response) {
            var thongTinSachMuonTra = $(".thongTinSachBody")
            thongTinSachMuonTra.empty()
            response.forEach(function (item) {
                item.sachMuon.forEach(function (sacMuon) {
                    var daTra = "Chưa Trả"
                    item.sachTra.forEach(function (sachTra) {
                         if (sachTra.maSach == sacMuon.maSach) {
                             daTra = "Đã trả"
                         }
                    })
                    stt += 1
                    result = `<tr>
                                <th scope="row">${stt}</th>
                                <th>${item.maPhieuMuon}</th>
                                <th>${formatDate(item.ngayMuon)}</th>
                                <th>${item.maPhieuTra}</th>
                                <th>${formatDate(item.ngayTra)}</th>
                                <th>${sacMuon.maSach}</th>
                                <th>${sacMuon.tenSach}</th>
                                <th>${sacMuon.giaTien}</th>
                                <th>${sacMuon.theLoai.tenTheLoai}</th>
                                <th>${sacMuon.nhaXuatBan.tenNhaXuatBan}</th>
                                <th>${sacMuon.tacGia.ten}</th>
                                <th>${daTra}</th>
                               </tr>`
                    thongTinSachMuonTra.append(result)
                })
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

function formatDate(dateTimeString) {
    var dateTime = new Date(dateTimeString);

    var date = dateTime.getDate();
    var month = dateTime.getMonth() + 1; // Tháng trong JavaScript đếm từ 0 đến 11
    var year = dateTime.getFullYear();

    var hours = dateTime.getHours();
    var minutes = dateTime.getMinutes();
    var seconds = dateTime.getSeconds();

    var formattedDate = (date < 10 ? '0' : '') + date;
    var formattedMonth = (month < 10 ? '0' : '') + month;
    var formattedYear = year;

    var formattedHours = (hours < 10 ? '0' : '') + hours;
    var formattedMinutes = (minutes < 10 ? '0' : '') + minutes;
    var formattedSeconds = (seconds < 10 ? '0' : '') + seconds;

    var formattedDateTime = formattedDate + '/' + formattedMonth + '/' + formattedYear + ' ' +
        formattedHours + ':' + formattedMinutes + ':' + formattedSeconds;

    return formattedDateTime;
}

