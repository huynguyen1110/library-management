var currentURL = window.location.href;

var searchParams = new URLSearchParams(new URL(currentURL).search);

var idNguoiDung = searchParams.get('id-nguoi-dung');

function hienThiSachMoi () {
    $.ajax({
        url: "/api/v1/sach-moi",
        method: "GET",
        success: function(response) {
            var sanPhamMoiBody = $('.san-pham-moi')
            var result = ``
            response.forEach(function(item) {
                result = `
                <div class="product product__style--3 col-3">
                        <div class="product__thumb">
                            <a class="first__img" href="/api/v1/single-product?maSach=${item.maSach}&maTk=${idNguoiDung}"><img src="/image/${item.image}"
                                                                                     alt="product image" style="width: 249px; height: 313.55px"></a>
                            <div class="hot__box">
                                <span class="hot-label">BEST SALLER</span>
                            </div>
                        </div>
                        <div class="product__content content--center">
                            <h4 ><a href="single-product.html" >${item.tenSach}</a></h4>
                            <ul class="price d-flex">
                                <li >${formatTienMat(item.giaTien)}</li>
                            </ul>

                            <div class="product__hover--content">
                                <ul class="rating d-flex">
                                    <li class="on"><i class="fa fa-star-o"></i></li>
                                    <li class="on"><i class="fa fa-star-o"></i></li>
                                    <li class="on"><i class="fa fa-star-o"></i></li>
                                    <li><i class="fa fa-star-o"></i></li>
                                    <li><i class="fa fa-star-o"></i></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                            `
                sanPhamMoiBody.append(result)
            })


        },
        error: function() {
            alert("Đã xảy ra lỗi khi lấy dữ liệu từ API.");
        }
    });
}

hienThiSachMoi()

function tatCaSach () {
    $.ajax({
        url: "/api/v1/tat-ca",
        method: "GET",
        success: function(response) {
            var tatCaBody = $(".tat-ca-sach")
            var result = ``
            response.forEach(function (item) {
                result = `
                            <!-- Start Single Product -->
                        <div class="single__product col-3">
                            <div class="single__product__inner ">
                                <div class="product product__style--3">
                                    <div class="product__thumb">
                                        <a class="first__img" href="/api/v1/single-product?maSach=${item.maSach}&maTk=${idNguoiDung}"><img src="/image/${item.image}"
                                                                                     alt="product image" style="width: 249px; height: 313.55px"></a>
                                        <div class="hot__box">
                                            <span class="hot-label">BEST SALLER</span>
                                        </div>
                                    </div>
                                    <div class="product__content content--center">
                                        <h4><a href="/api/v1/single-product">${item.tenSach}</a></h4>
                                        <ul class="price d-flex">
                                            <li>${formatTienMat(item.giaTien)}</li>
                                        </ul>

                                        <div class="product__hover--content">
                                            <ul class="rating d-flex">
                                                <li class="on"><i class="fa fa-star-o"></i></li>
                                                <li class="on"><i class="fa fa-star-o"></i></li>
                                                <li class="on"><i class="fa fa-star-o"></i></li>
                                                <li><i class="fa fa-star-o"></i></li>
                                                <li><i class="fa fa-star-o"></i></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        `
                tatCaBody.append(result)
            })

        },
        error: function() {
            alert("Đã xảy ra lỗi khi lấy dữ liệu từ API.");
        }
    });
}

tatCaSach()


function hienThiSachTheoTheLoai (theLoai) {
    $.ajax({
        url: "/api/v1/sach-theo-the-loai?the-loai=" + theLoai,
        method: "GET",
        success: function(response) {
            var truyenNganBody = $(".truyen-ngan")
            truyenNganBody.empty()
            var thoBody = $(".tho")
            thoBody.empty()
            var result = ``
            if (theLoai == 'Truyện Ngắn') {
                response.forEach(function (item) {
                    result = `
                            <!-- Start Single Product -->
                            <div class="single__product__inner  col-3">
                                <div class="product product__style--3">
                                    <div class="product__thumb">
                                        <a class="first__img" href="/api/v1/single-product?maSach=${item.maSach}&maTk=${idNguoiDung}"><img src="/image/${item.image}"
                                                                                     alt="product image" style="width: 249px; height: 313.55px"></a>
                                        <div class="hot__box">
                                            <span class="hot-label">BEST SALLER</span>
                                        </div>
                                    </div>
                                    <div class="product__content content--center">
                                        <h4><a href="/api/v1/single-product">${item.tenSach}</a></h4>
                                        <ul class="price d-flex">
                                            <li>${formatTienMat(item.giaTien)} </li>
                                        </ul>

                                        <div class="product__hover--content">
                                            <ul class="rating d-flex">
                                                <li class="on"><i class="fa fa-star-o"></i></li>
                                                <li class="on"><i class="fa fa-star-o"></i></li>
                                                <li class="on"><i class="fa fa-star-o"></i></li>
                                                <li><i class="fa fa-star-o"></i></li>
                                                <li><i class="fa fa-star-o"></i></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        `

                    truyenNganBody.append(result)
                })
            }

            if (theLoai == 'Thơ') {
                response.forEach(function (item) {
                    result = `
                            <!-- Start Single Product -->
                            <div class="single__product__inner  col-3">
                                <div class="product product__style--3">
                                    <div class="product__thumb">
                                        <a class="first__img" href="/api/v1/single-product?maSach=${item.maSach}&maTk=${idNguoiDung}"><img src="/image/${item.image}"
                                                                                     alt="product image" style="width: 249px; height: 313.55px"></a>
                                        <div class="hot__box">
                                            <span class="hot-label">BEST SALLER</span>
                                        </div>
                                    </div>
                                    <div class="product__content content--center">
                                        <h4><a href="/api/v1/single-product">${item.tenSach}</a></h4>
                                        <ul class="price d-flex">
                                            <li>${formatTienMat(item.giaTien)}</li>
                                        </ul>

                                        <div class="product__hover--content">
                                            <ul class="rating d-flex">
                                                <li class="on"><i class="fa fa-star-o"></i></li>
                                                <li class="on"><i class="fa fa-star-o"></i></li>
                                                <li class="on"><i class="fa fa-star-o"></i></li>
                                                <li><i class="fa fa-star-o"></i></li>
                                                <li><i class="fa fa-star-o"></i></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        `
                    thoBody.append(result)
                })
            }

        },
        error: function() {
            alert("Đã xảy ra lỗi khi lấy dữ liệu từ API.");
        }
    });
}

function vaoCuaHang() {
    var urlParams = new URLSearchParams(window.location.search);
    var maTk = urlParams.get('id-nguoi-dung');
    $(".vao-cua-hang").on("click", function(event) {
        event.preventDefault();
        window.location.href = '/api/v1/shop?maTk=' + maTk;
    });
}

vaoCuaHang()

function formatTienMat(tien) {
    var formattedAmount = tien.toLocaleString('vi-VN', {
        style: 'currency',
        currency: 'VND'
    });
    return formattedAmount
}

function thongTinMuonTraBtn1() {
    window.location.href = '/api/v1/thong-tin-muon-tra?maTk=' + idNguoiDung;
}

function dangXuatBtn() {
    window.location.href = '/api/v1/login'
}


