var currentURL = window.location.href;

var searchParams = new URLSearchParams(new URL(currentURL).search);

var maTk = searchParams.get('maTk');

function hienThiSachTrongShop() {
    var result = ``
    var productList = $("#productList")
    $.ajax({
        url: '/api/v1/lay-sach-phantrang',
        method: "GET",
        success: function(response) {
            productList.empty()
            response.content.forEach(function (item) {
                result = `<div class="product product__style--3 col-lg-4 col-md-4 col-sm-6 col-12">
                                    <div class="product__thumb">
                                        <a class="first__img" href="/api/v1/single-product?maSach=${item.maSach}&maTk=${maTk}"><img
                                                src="/images/books/1.jpg" alt="product image"></a>
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
            for (var i = 1; i <= response.totalPages; i++) {
                var pageNumber = $('<li><a href="#" onclick="loadBooks(' + (i - 1) + ')">' + i + '</a></li>');
                pagination.append(pageNumber);
            }

        },
        error: function() {
            alert("Đã xảy ra lỗi khi lấy dữ liệu từ API.");
        }
    });
}

hienThiSachTrongShop()

function loadBooks(page) {
    // Gọi API để lấy danh sách sách theo trang
    var result = ``
    $.ajax({
        url: '/api/v1/lay-sach-phantrang?pageNumber=' + page,
        type: 'GET',
        success: function(response) {
            var productList = $('#productList')
            productList.empty()

            response.content.forEach(function (item) {
                result = `<div class="product product__style--3 col-lg-4 col-md-4 col-sm-6 col-12">
                                    <div class="product__thumb">
                                        <a class="first__img" href="/api/v1/single-product?maSach=${item.maSach}&maTk=${maTk}"><img
                                                src="/images/books/1.jpg" alt="product image"></a>
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
            return selectedValue
        }
        return selectedValue;
    });
}

sapXepTheo()
