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
                                <a href="/api/v1/admin/info-update?maTk=${item.taiKhoan.maTk}"><i class="fa-solid fa-pen-to-square "></i><span class="ml-2">Cập nhật</span></a>
                                <br>
                                <a href="" onclick="deleteDocGia(${item.taiKhoan.maTk})"><i class="fa-solid fa-trash "></i><span class="ml-2">Xóa</span></a>
                                <br>
                                <a href="#" onclick="lichSuMuonTra(${item.taiKhoan.maTk})"><i class="fa-solid fa-clock-rotate-left"></i><span class="ml-2">Lịch sử mượn trả</span></a>
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
                                <td>${stt}</td>
                                <td>${item.maPhieuMuon}</td>
                                <td>${formatDate(item.ngayMuon)}</td>
                                <td>${item.maPhieuTra}</td>
                                <td>${formatDate(item.ngayTra)}</td>
                                <td>${sacMuon.maSach}</td>
                                <td>${sacMuon.tenSach}</td>
                                <td>${sacMuon.giaTien}</td>
                                <td>${sacMuon.theLoai.tenTheLoai}</td>
                                <td>${sacMuon.nhaXuatBan.tenNhaXuatBan}</td>
                                <td>${sacMuon.tacGia.ten}</td>
                                <td>${daTra}</td>
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

var urlParams = new URLSearchParams(window.location.search);
var maTk = urlParams.get('maTk');
function capNhatThongTinUser() {
    $('form').submit(function(event) {
        event.preventDefault(); // Ngăn chặn việc submit form mặc định

        var hoVaTen = $('#hoVaTen').val();
        var diaChi = $('#diaChi').val();
        var ngaySinh = $('#ngaySinh').val();
        var email = $('#email').val();
        var soDienThoai = $('#soDienThoai').val();

        var userData = {
            ten: hoVaTen,
            ngaySinh: ngaySinh,
            sdt: soDienThoai,
            email: email,
            diaChi: diaChi
        };

        var jsonData = JSON.stringify(userData)

        // Gửi yêu cầu AJAX POST
        $.ajax({
            url: '/api/v1/cap-nhat-thong-tin?maTk=' + maTk,
            method: 'PUT',
            contentType: 'application/json',
            data: jsonData,
            dataType: 'json',
            success: function(response) {
                alert("Cập nhât thành công")
                window.location.href = '/api/v1/admin/info-update?maTk=' + maTk
            },
            error: function(xhr, status, error) {
                console.error(error);
            }
        });
    });
}

function hienThiThongTinUser() {
    var result = ``
    $.ajax({
        url: '/api/v1/lay-thong-tin?maTk=' + maTk,
        method: 'GET',
        success: function(response) {
            var userInfoBody = $("#userInfo")
            userInfoBody.empty()
            result = `<li class="name">NGƯỜI DÙNG
                                <label class="label label-info"> ${response.ten}</label>
                            </li>
                            <li class="activity">DỊA CHỈ: ${response.diaCHi}</li>
                            <li class="activity">NGÀY SINH: ${ convertToDateTimeFormatted(response.ngaySinh) }</li>
                            <li class="activity">SỐ ĐIỆN THOẠI: ${response.sdt}</li>
                            <li class="activity">EMAIL: ${response.email}</li>
                            `
            userInfoBody.append(result)
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

hienThiThongTinUser()

function convertToDateTimeFormatted(dateTimeString) {
    var dateTime = new Date(dateTimeString);
    var day = dateTime.getDate();
    var month = dateTime.getMonth() + 1;
    var year = dateTime.getFullYear();

    // Định dạng ngày-tháng-năm
    var formattedDateTime = day + '-' + month + '-' + year;

    return formattedDateTime;
}


