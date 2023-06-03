function themTheLoai() {
    $('#formTheLoai').submit(function(event) {
        event.preventDefault();

        var tenTheLoai = $('#tenTheLoai').val();

        var data = {
            tenTheLoai: tenTheLoai
        };

        $.ajax({
            url: '/api/v1/admin/them-the-loai',
            method: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(response) {
                $('#tenTheLoai').val('')
                alert("Thêm thành công")
                hienThiDanhSachTheLoai()
                console.log(response);
            },
            error: function(error) {
                // Xử lý lỗi
                console.log(error);
            }
        });
    });
}

themTheLoai()


function hienThiDanhSachTheLoai() {
    var listOfMembersBody = $(".listOfMembersBody")
    var result = ``
    $.ajax({
        url: '/api/v1/admin/danh-sach-the-loai',
        method: 'GET',
        success: function(response) {
            listOfMembersBody.empty()
            response.forEach(function (item) {
                if (item.daXoa != true) {
                    result = `<tr>
                        <td>${item.maTheLoai}</td>
                        <td>${item.tenTheLoai}</td>
                        <td>
                            <a href="#" onclick="capNhatTheLoaiBtn(${item.maTheLoai})"><i class="fa-solid fa-pen-to-square"></i></i><span class="ml-2">Cập nhật</span></a>
                            <br>
                            <a href="#" onclick="xoaTheLoai(${item.maTheLoai})"><i class="fa-solid fa-trash"></i><span class="ml-2">Xóa</span></a>
                            <br>
                        </td>
                       </tr>`
                    listOfMembersBody.append(result)
                }
            })

            console.log(response);
        },
        error: function(error) {
            console.log(error);
        }
    });
}

hienThiDanhSachTheLoai()

function capNhatTheLoaiBtn(maTheLoai) {
    $(".modal-content").show()
    $('#formCapNhatTheLoai').submit(function(event) {
        event.preventDefault();

        var tenTheLoai = $("#theLoaiCapNhatTen").val()
        var capNhatTheLoaiDto = {
            "tenTheLoai": tenTheLoai
        };

        $.ajax({
            url: '/api/v1/admin/cap-nhat-the-loai?maTheLoai=' + maTheLoai,
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(capNhatTheLoaiDto),
            success: function(response) {
                $("#theLoaiCapNhatTen").val('')
                hienThiDanhSachTheLoai()
                console.log(response);
            },
            error: function(error) {
                console.log(error);
            }
        });
    })
}

function closeFormCapNhat() {
    $(".modal-content").hide()
}

function xoaTheLoai(maTheLoai) {
    if (confirm('Bạn có chắc chắn muốn xóa thể loại này không?')) {
        $.ajax({
            url: '/api/v1/admin/xoa-the-loai?maTheLoai=' + maTheLoai,
            method: 'PUT',
            success: function(response) {
                hienThiDanhSachTheLoai()
                console.log(response);
            },
            error: function(error) {
                console.log(error);
            }
        });
    }
}
