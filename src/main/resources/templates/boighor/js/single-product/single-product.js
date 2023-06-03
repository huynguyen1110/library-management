function hienThiChiTieSach() {
    var urlParams = new URLSearchParams(window.location.search);
    var maSach = urlParams.get('maSach');
    var maTk = urlParams.get('maTk');
    $.ajax({
            url: "/api/v1/chi-tiet-sach?id="+ maSach,
            method: "GET",
            success: function(item) {

                var chiTietSanPhamBody = $(".chi-tiet-san-pham")
                chiTietSanPhamBody.empty()
                var result = ``
                    result = `<div class="col-lg-6 col-12">
                                <div class="wn__fotorama__wrapper">
                                    <div class="fotorama wn__fotorama__action" data-nav="thumbs">
                                        <img src="/image/${item.image}" alt="product image" style="width: 249px; height: 313.55px">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 col-12">
                                <div class="product__info__main">
                                    <h1>${item.tenSach}</h1>
                                    <div class="product-reviews-summary d-flex">
                                        <ul class="rating-summary d-flex">
                                            <li><i class="zmdi zmdi-star-outline"></i></li>
                                            <li><i class="zmdi zmdi-star-outline"></i></li>
                                            <li><i class="zmdi zmdi-star-outline"></i></li>
                                            <li class="off"><i class="zmdi zmdi-star-outline"></i></li>
                                            <li class="off"><i class="zmdi zmdi-star-outline"></i></li>
                                        </ul>
                                    </div>
                                    <div class="price-box">
                                        <span>${formatTienMat(item.giaTien) }</span>
                                    </div>
                                    <div class="box-tocart d-flex">
                                            <div class="addtocart__actions">
                                                <button id="them-vao-gio-hang" onclick="themSachVaoGioHang(${maSach}, ${maTk})" class="tocart"  title="Add to Cart">Thêm vào giỏ hàng </button>
                                            </div>
                                        <div class="product-addto-links clearfix">
                                            <a class="wishlist" href="#"></a>
                                            <a class="compare" href="#"></a>
                                        </div>
                                    </div>
                                    <div class="product-share">
                                        <ul>
                                            <li class="categories-title">Share :</li>
                                            <li>
                                                <a href="#">
                                                    <i class="icon-social-twitter icons"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i class="icon-social-tumblr icons"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i class="icon-social-facebook icons"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i class="icon-social-linkedin icons"></i>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="mt-4">
                                        <h1 class="text-uppercase font-weight-bold text-xxl">Chi tiết thông tin</h1>
                                        <p class="text--italic">Tác giả: ${item.tacGia}</p>
                                        <p class="text--italic">Ngày xuất bản: ${formatDate(item.ngayXuatBan)}</p>
                                        <p class="text--italic">Nhà xuất bản:  ${item.nhaXuatBan}</p>
                                        <p class="text--italic">Thể Loại: ${item.theLoai}</p>
                                        <p class="text--italic">Số lượng sách còn: ${item.soLuong}</p>
                                    </div>
                                </div>
                            </div>`
                    chiTietSanPhamBody.append(result)
            },
            error: function() {
                console.log("Đã xảy ra lỗi khi lấy thông tin sản phẩm.");
            }
        });
}
hienThiChiTieSach()

function themSachVaoGioHang(maSach, maTk) {
        $.ajax({
            url: '/api/v1/them-vao-gio-hang?maSach=' + maSach + '&maTk=' + maTk,
            type: 'POST',
            success: function(response) {
                if (response == "") {
                    alert("Đã hết sách")
                } else {
                    alert("Đã thêm sách vào giỏ hàng")
                }
            },
            error: function(xhr, status, error) {
                console.log('Error sending POST request');
                console.log(error);
            }
        });
}

function hienThiGioHang() {
    var urlParams = new URLSearchParams(window.location.search);
    var maSach = urlParams.get('maSach');
    var maTk = urlParams.get('maTk');
    var gioHangBody = $(".gio-hang-body")
    gioHangBody.empty()
    var tongTien = 0
    var result = ``
    $.ajax({
        url: '/api/v1/lay-gio-hang?maSach=' + maSach + '&maTk=' + maTk,
        type: 'GET',
        success: function(response) {
            response.sachList.forEach(function (item) {
                tongTien += item.giaTien
            })
            result = `
                                    <div class="items-total d-flex justify-content-between">
                                        <span>${response.sachList.length} items</span>
                                        <span>Tổng tiền</span>
                                    </div>
                                    <div class="total_amount text-end">
                                        <span>${formatTienMat(tongTien)}</span>
                                    </div>
                                    <div class="mini_action checkout">
                                        <a class="checkout__btn" href="/api/v1/cart?maSach=${maSach}&maTk=${maTk}">Yêu cầu mượn</a>
                                    </div>
                                    <div class="single__items">
                                        <div class="miniproduct chi-tiet-sach-trong-gio-hang">
                                            
                                        </div>
                                    </div>
                                    <div class="mini_action cart">
                                        <a class="cart__btn" href="/api/v1/cart?maSach=${maSach}&maTk=${maTk}">Xem và chỉnh sửa</a>
                                    </div>
                                `
            gioHangBody.append(result)
            hienThiSachTrongGioHang(maSach, maTk)
        },
        error: function(xhr, status, error) {
            alert("That bai")
            console.log('Error sending POST request');
            console.log(error);
        }
    });
}

function hienThiSachTrongGioHang(maSach, maTk) {
    var sachTrongGioHangBody = $(".chi-tiet-sach-trong-gio-hang")
    var result = ``
    sachTrongGioHangBody.empty()
    $.ajax({
        url: '/api/v1/lay-gio-hang?maSach=' + maSach + '&maTk=' + maTk,
        type: 'GET',
        success: function(response) {
            var count = 0
            response.sachList.forEach(function (item) {
                result = `<!-- item trong giỏ hàng -->
                                            <div class="item01 d-flex mt--20">
                                                <div class="thumb"><img src="/image/${item.image}"
                                                                                     alt="product image" style="width: 75px; height: 94px"></a>
                                                </div>
                                                <div class="content">
                                                    <h6>${item.tenSach}</h6>
                                                    <span class="price">${formatTienMat(item.giaTien)}</span>
                                                    <div class="product_price d-flex justify-content-between">
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- item trong giỏ hàng -->`
                count ++
                if (count < 5) {
                    sachTrongGioHangBody.append(result)
                }

            })
        },
        error: function(xhr, status, error) {
            console.log('Error sending POST request');
            console.log(error);
        }
    });
}

function troLaiTrangChu() {
    var urlParams = new URLSearchParams(window.location.search);
    var maTk = urlParams.get('maTk');
    $(".tro-lai-trang-chu").on("click", function(event) {
        event.preventDefault();
        window.location.href = '/api/v1/index?id-nguoi-dung=' + maTk;
    });
}

troLaiTrangChu()

function formatTienMat(tien) {

    var formattedAmount = tien.toLocaleString('vi-VN', {
        style: 'currency',
        currency: 'VND'
    });
    return formattedAmount
}

var urlParams = new URLSearchParams(window.location.search);
var maTk = urlParams.get('maTk');

function thongTinMuonTraBtn() {
    window.location.href = '/api/v1/thong-tin-muon-tra?maTk=' + maTk;
}

function dangXuatBtn() {
    window.location.href = '/api/v1/login'
}

function formatDate(dateTimeString) {
    var dateTime = new Date(dateTimeString);

    var date = dateTime.getDate();
    var month = dateTime.getMonth() + 1; // Tháng trong JavaScript đếm từ 0 đến 11
    var year = dateTime.getFullYear();

    var formattedDate = (date < 10 ? '0' : '') + date;
    var formattedMonth = (month < 10 ? '0' : '') + month;
    var formattedYear = year;


    var formattedDateTime = formattedDate + '/' + formattedMonth + '/' + formattedYear + ' '
    return formattedDateTime;
}

