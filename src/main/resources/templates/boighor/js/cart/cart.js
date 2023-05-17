var urlParams = new URLSearchParams(window.location.search);
var maSach = urlParams.get('maSach');
var maTk = urlParams.get('maTk');
function hienThiChiTietSachTrongCart () {
    var tableBody = $(".table-cart-body")
    tableBody.empty()
    var result = ``
    var tongTienBody = $(".tong-tien")
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
                            <td class="product-remove"><a href="#">X</a></td>
                     </tr>`
                tableBody.append(result);
            })
            tongTienContent = `<ul class="cart__total__list">
                                <li>Tổng tiền</li>
                            </ul>
                            <ul class="cart__total__tk">
                                <li>${formatTienMat(response.tongTien)}</li>
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
