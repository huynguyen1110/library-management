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
                                        <img src="/images/product/1.jpg" alt="">
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
                                        <span>${item.giaTien}</span>
                                    </div>
                                    <div class="box-tocart d-flex">
<!--                                         <form>-->
                                            <div class="addtocart__actions">
                                                <button id="them-vao-gio-hang" onclick="themSachVaoGioHang(${maSach}, ${maTk})" class="tocart"  title="Add to Cart">Thêm vào giỏ hàng </button>
                                            </div>
<!--                                        </form>-->
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
                                        <p class="text--italic">Ngày xuất bản: ${item.ngayXuatBan}</p>
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
                alert("Đã thêm sách vào giỏ hàng")
            },
            error: function(xhr, status, error) {
                console.log('Error sending POST request');
                console.log(error);
            }
        });
}
