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
                    window.location.href = '/api/v1/info-update?maTk=' + maTk
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
