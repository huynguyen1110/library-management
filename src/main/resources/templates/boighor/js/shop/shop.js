var selectedValueToOrder
var urlParams = new URLSearchParams(window.location.search);
var maTk = urlParams.get('maTk');
function hienThiSachTrongShop(selectedValue, theLoai) {
    var currentURL = window.location.href;

    var searchParams = new URLSearchParams(new URL(currentURL).search);

    var maTk = searchParams.get('maTk');


    if (selectedValue === undefined) {
        selectedValue = "tenSach"
    }

    var result = ``
    var productList = $("#productList")


    if (theLoai === undefined  || theLoai === "tất cả") {
        $.ajax({
            // url: '/api/v1/lay-sach-phantrang',
            url: '/api/v1/lay-sach-phantrang?pageNumber=' + "&orderBy=" + selectedValue,
            method: "GET",
            success: function(response) {
                productList.empty()
                response.content.forEach(function (item) {
                    result = `<div class="product product__style--3 col-lg-4 col-md-4 col-sm-6 col-12">
                                    <div class="product__thumb">
                                        <a class="first__img" href="/api/v1/single-product?maSach=${item.maSach}&maTk=${maTk}"><img src="/image/${item.image}"
                                                                                     alt="product image" style="width: 249px; height: 313.55px"></a>
                                        <div class="hot__box">
                                            <span class="hot-label">BEST SALLER</span>
                                        </div>
                                    </div>
                                    <div class="product__content content--center">
                                        <h4><a href="/api/v1/single-product?maSach=${item.maSach}&maTk=${maTk}">${item.tenSach}</a></h4>
                                        <ul class="price d-flex">
                                            <li>${ formatTienMat(item.giaTien)}</li>
                                        </ul>
                                    </div>
                                </div>`
                    productList.append(result)
                })
                selectedValueToOrder = selectedValue
                var pagination = $('.wn__pagination');
                pagination.empty()
                for (var i = 1; i <= response.totalPages; i++) {
                    var pageNumber = `<li> <a href="#" onclick="loadBooks(${i - 1})"> ${i} </a> </li>`
                    pagination.append(pageNumber);
                }
            },
            error: function() {
                alert("Đã xảy ra lỗi khi lấy dữ liệu từ API.");
            }
        });
    }

    if (theLoai == "Tiểu Thuyết") {
        $.ajax({
            url: '/api/v1/lay-sach-phantrang-theo-theloai?pageNumber=' + '&orderBy=' + selectedValue + '&theLoai='+ theLoai ,
            method: "GET",
            success: function(response) {
                ajaxComplete = true
                productList.empty()
                response.content.forEach(function (item) {
                    result = `<div class="product product__style--3 col-lg-4 col-md-4 col-sm-6 col-12">
                                    <div class="product__thumb">
                                        <a class="first__img" href="/api/v1/single-product?maSach=${item.maSach}&maTk=${maTk}"><img src="/image/${item.image}"
                                                                                     alt="product image" style="width: 249px; height: 313.55px"></a>
                                        <div class="hot__box">
                                            <span class="hot-label">BEST SALLER</span>
                                        </div>
                                    </div>
                                    <div class="product__content content--center">
                                        <h4><a href="/api/v1/single-product?maSach=${item.maSach}&maTk=${maTk}">${item.tenSach}</a></h4>
                                        <ul class="price d-flex">
                                            <li>${ formatTienMat(item.giaTien)}</li>
                                        </ul>
                                    </div>
                                </div>`
                    productList.append(result)
                })
                var pagination = $('.wn__pagination');
                pagination.empty()
                for (var i = 1; i <= response.totalPages; i++) {
                    var pageNumber = `<li> <a href="#" onclick="loadBooksByTheLoai(${i - 1}, '${theLoai}')"> ${i} </a> </li>`
                    pagination.append(pageNumber);
                }

            },
            error: function() {
                ajaxComplete = true
                alert("Đã xảy ra lỗi khi lấy dữ liệu từ API.");
            }
        });
    }

    if (theLoai == "Truyện Ngắn") {
        $.ajax({
            url: '/api/v1/lay-sach-phantrang-theo-theloai?pageNumber=' + '&orderBy=' + selectedValue + '&theLoai='+ theLoai ,
            method: "GET",
            success: function(response) {
                ajaxComplete = true
                productList.empty()
                response.content.forEach(function (item) {
                    result = `<div class="product product__style--3 col-lg-4 col-md-4 col-sm-6 col-12">
                                    <div class="product__thumb">
                                        <a class="first__img" href="/api/v1/single-product?maSach=${item.maSach}&maTk=${maTk}"><img src="/image/${item.image}"
                                                                                     alt="product image" style="width: 249px; height: 313.55px"></a>
                                        <div class="hot__box">
                                            <span class="hot-label">BEST SALLER</span>
                                        </div>
                                    </div>
                                    <div class="product__content content--center">
                                        <h4><a href="/api/v1/single-product?maSach=${item.maSach}&maTk=${maTk}">${item.tenSach}</a></h4>
                                        <ul class="price d-flex">
                                            <li>${ formatTienMat(item.giaTien)}</li>
                                        </ul>
                                    </div>
                                </div>`
                    productList.append(result)
                })
                var pagination = $('.wn__pagination');
                pagination.empty()
                for (var i = 1; i <= response.totalPages; i++) {
                    var pageNumber = `<li> <a href="#" onclick="loadBooksByTheLoai(${i - 1}, '${theLoai}')"> ${i} </a> </li>`
                    pagination.append(pageNumber);
                }

            },
            error: function() {
                ajaxComplete = true
                alert("Đã xảy ra lỗi khi lấy dữ liệu từ API.");
            }
        });
    }

    if (theLoai == "Thơ") {
        $.ajax({
            url: '/api/v1/lay-sach-phantrang-theo-theloai?pageNumber=' + '&orderBy=' + selectedValue + '&theLoai='+ theLoai ,
            method: "GET",
            success: function(response) {
                ajaxComplete = true
                productList.empty()
                response.content.forEach(function (item) {
                    result = `<div class="product product__style--3 col-lg-4 col-md-4 col-sm-6 col-12">
                                    <div class="product__thumb">
                                        <a class="first__img" href="/api/v1/single-product?maSach=${item.maSach}&maTk=${maTk}"><img src="/image/${item.image}"
                                                                                     alt="product image" style="width: 249px; height: 313.55px"></a>
                                        <div class="hot__box">
                                            <span class="hot-label">BEST SALLER</span>
                                        </div>
                                    </div>
                                    <div class="product__content content--center">
                                        <h4><a href="/api/v1/single-product?maSach=${item.maSach}&maTk=${maTk}">${item.tenSach}</a></h4>
                                        <ul class="price d-flex">
                                            <li>${ formatTienMat(item.giaTien)}</li>
                                        </ul>
                                    </div>
                                </div>`
                    productList.append(result)
                })
                var pagination = $('.wn__pagination');
                pagination.empty()
                for (var i = 1; i <= response.totalPages; i++) {
                    var pageNumber = `<li> <a href="#" onclick="loadBooksByTheLoai(${i - 1}, '${theLoai}')"> ${i} </a> </li>`
                    pagination.append(pageNumber);
                }

            },
            error: function() {
                ajaxComplete = true
                alert("Đã xảy ra lỗi khi lấy dữ liệu từ API.");
            }
        });
    }

}

hienThiSachTrongShop()

function loadBooks(page) {
    var currentURL = window.location.href;

    var searchParams = new URLSearchParams(new URL(currentURL).search);

    var maTk = searchParams.get('maTk');

    // Gọi API để lấy danh sách sách theo trang
    if (selectedValueToOrder === undefined) {
        selectedValueToOrder = "tenSach"
    }
    var result = ``
    $.ajax({
        url: '/api/v1/lay-sach-phantrang?pageNumber=' + page + "&orderBy=" + selectedValueToOrder,
        type: 'GET',
        success: function(response) {
            var productList = $('#productList')
            productList.empty()

            response.content.forEach(function (item) {
                result = `<div class="product product__style--3 col-lg-4 col-md-4 col-sm-6 col-12">
                                    <div class="product__thumb">
                                        <a class="first__img" href="/api/v1/single-product?maSach=${item.maSach}&maTk=${maTk}"><img src="/image/${item.image}"
                                                                                     alt="product image" style="width: 249px; height: 313.55px"></a>
                                        <div class="hot__box">
                                            <span class="hot-label">BEST SALLER</span>
                                        </div>
                                    </div>
                                    <div class="product__content content--center">
                                        <h4><a href="/api/v1/single-product?maSach=${item.maSach}&maTk=${maTk}">${item.tenSach}</a></h4>
                                        <ul class="price d-flex">
                                            <li>${ formatTienMat(item.giaTien)}</li>
                                        </ul>
                                    </div>
                                </div>`
                productList.append(result)
            })
        },
        error: function(xhr, status, error) {
            console.log('Error sending GET request');
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

//Lấy giá trị trong ô để sắp xếp theo tên, ngày, giá sách
function sapXepTheo() {
    $('.shot__byselect').on('change', function() {
        var selectedValue = $(this).val();
        if (selectedValue == undefined) {
            selectedValue = 'tenSach'
            hienThiSachTrongShop(selectedValue)
            return selectedValue
        }
        hienThiSachTrongShop(selectedValue)
        return selectedValue;
    });
}

sapXepTheo()




function checkClickedButton(inputValue) {
    var value = inputValue
    $("a.tatCarButton, a.tieuThuyetButton, a.truyenNganButton, a.thoButton").click(function(event) {
        event.preventDefault();
        value = $(this).attr('value');
        hienThiSachTrongShop(selectedValueToOrder, value)
        return value
    });
    return value
}

checkClickedButton()



function loadBooksByTheLoai(page, theLoai) {
    var currentURL = window.location.href;

    var searchParams = new URLSearchParams(new URL(currentURL).search);

    var maTk = searchParams.get('maTk');

    // Gọi API để lấy danh sách sách theo trang
    if (selectedValueToOrder === undefined) {
        selectedValueToOrder = "tenSach"
    }
    var result = ``
    $.ajax({
        url: '/api/v1/lay-sach-phantrang-theo-theloai?pageNumber=' + page + "&orderBy=" + selectedValueToOrder + "&theLoai="+ theLoai,
        type: 'GET',
        success: function(response) {
            var productList = $('#productList')
            productList.empty()

            response.content.forEach(function (item) {
                result = `<div class="product product__style--3 col-lg-4 col-md-4 col-sm-6 col-12">
                                    <div class="product__thumb">
                                        <a class="first__img" href="/api/v1/single-product?maSach=${item.maSach}&maTk=${maTk}"><img src="/image/${item.image}"
                                                                                     alt="product image" style="width: 249px; height: 313.55px"></a>
                                        <div class="hot__box">
                                            <span class="hot-label">BEST SALLER</span>
                                        </div>
                                    </div>
                                    <div class="product__content content--center">
                                        <h4><a href="/api/v1/single-product?maSach=${item.maSach}&maTk=${maTk}">${item.tenSach}</a></h4>
                                        <ul class="price d-flex">
                                            <li>${ formatTienMat(item.giaTien)}</li>
                                        </ul>
                                    </div>
                                </div>`
                productList.append(result)
            })
        },
        error: function(xhr, status, error) {
            console.log('Error sending GET request');
            console.log(error);
        }
    });
}




function thongTinMuonTraBtn() {
    window.location.href = '/api/v1/thong-tin-muon-tra?maTk=' + maTk;
}

function dangXuatBtn() {
    window.location.href = '/api/v1/login'
}










