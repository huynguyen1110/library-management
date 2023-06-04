var urlParams = new URLSearchParams(window.location.search);
var maSach = urlParams.get('maSach');
var maTk = urlParams.get('maTk');
function hienThiChiTietSachTrongCart () {
    var tableBody = $(".table-cart-body")
    tableBody.empty()
    var result = ``
    var tongTienBody = $(".tong-tien")
    var tongTien = 0
    var tongTienContent = ``
    tongTienBody.empty()
    $.ajax({
        url: '/api/v1/lay-gio-hang?maSach=' + maSach + '&maTk=' + maTk,
        type: 'GET',
        success: function(response) {
            response.sachList.forEach(function (item) {
                result = `<tr>
                            <td class="product-name"><a href="/api/v1/single-product?maSach=${maSach}&maTk=${maTk}">${item.tenSach}</a></td>
                            <td class="product-price"><span class="amount">${formatTienMat(item.giaTien)}</span></td>
<!--                            <td class="product-remove"><button type="button" onclick="xoaSanPhamKhoiGioHang()">X</button></td>-->
                            <td class="product-remove"><i class="fa-regular fa-xmark xoaBtn" onclick="xoaSanPhamKhoiGioHang(${item.maSach})"></i></td>
                     </tr>`
                tongTien += item.giaTien
                tableBody.append(result);
            })
            tongTienContent = `<ul class="cart__total__list">
                                <li>Tổng tiền</li>
                            </ul>
                            <ul class="cart__total__tk">
                                <li>${formatTienMat(tongTien)}</li>
                            </ul>`
            tongTienBody.append(tongTienContent)
        },
        error: function(xhr, status, error) {
            console.log('Error sending POST request');
            console.log(error);
        }
    });
}

function formatTienMat(tien) {

    var formattedAmount = tien.toLocaleString('vi-VN', {
        style: 'currency',
        currency: 'VND'
    });
    return formattedAmount
}

hienThiChiTietSachTrongCart()


function xoaSanPhamKhoiGioHang(maSachItem) {

    $.ajax({
        url: '/api/v1/xoa-sach-khoi-gio-hang?maSach=' + maSachItem + '&maTk=' + maTk,
        type: 'DELETE',
        success: function(response) {
            alert("Đã xóa sách khỏi giỏ hàng")
            hienThiChiTietSachTrongCart()
        },
        error: function(xhr, status, error) {
            console.log('xóa sách không thành công');
            console.log(error);
        }
    });
}


function thanhToan() {
    var confirmation = confirm("Bạn có chắc chắn muốn mượn cuốn sách này?");
    if (confirmation) {
        $.ajax({
            url: `/api/v1/tao-phieu-muon-tra?maTk=` + maTk,
            method: 'POST',
            success: function (response) {
                alert("Mượn thành công thành công");
            },
            error: function () {
                console.log('Đã xảy ra lỗi khi gọi API.');
            }
        });
    }
}

var urlParams = new URLSearchParams(window.location.search);
var maTk = urlParams.get('maTk');

function thongTinMuonTraBtn() {
    window.location.href = '/api/v1/thong-tin-muon-tra?maTk=' + maTk;
}

function dangXuatBtn() {
    window.location.href = '/api/v1/login'
}

