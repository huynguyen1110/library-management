$(document).ready(function() {
    $('#register-form').submit(function (event) {
        event.preventDefault()
        var username = $('#username').val()
        var password = $('#password').val()
        var confirmPassword = $('#confirm-password').val()

        if (validate(username, password, confirmPassword) == false) {
            var formData = {
                tenTk: username,
                matKhau: password,
                nhapLaiMk: confirmPassword
            }

            var formDataJson = JSON.stringify(formData);

            $.ajax({
                url: '/api/v1/them-tai-khoan',
                type: 'POST',
                data: formDataJson,
                contentType: 'application/json',
                success: function(response) {
                    if (response == "") {
                        alert("Tài khoản đã tồn tại")
                    } else {
                        alert("Tạo tài khoản thành công.")
                    }
                },
                error: function(xhr, status, error) {
                    console.log('Error sending POST request');
                    console.log(error);
                }
            });

        }

    })

    function validate(username, password, confirmPassword) {
        var hasError = false;
        if (username.trim() === '') {
            $('#username-error-msg').text('Vui lòng nhập tên tài khoản');
            hasError = true;
        } else {
            $('#username-error-msg').text('');
        }

        if (password.trim() === '') {
            $('#password-error-msg').text('Vui lòng nhập mật khẩu');
            hasError = true;
        } else {
            $('#password-error-msg').text('');
        }

        if (confirmPassword.trim() === '') {
            $('#confirm-password-error-msg').text('Vui lòng nhập lại mật khẩu');
            hasError = true;
        } else if (confirmPassword !== password) {
            $('#confirm-password-error-msg').text('Mật khẩu nhập lại không khớp');
            hasError = true;
        } else {
            $('#confirm-password-error-msg').text(''); // Xóa thông báo lỗi nếu có
        }
        return hasError
    }

    function validateLogin(username, password) {
        var hasError = false;
        if (username.trim() === '') {
            $('#username-error-msg').text('Vui lòng nhập tên tài khoản');
            hasError = true;
        } else {
            $('#username-error-msg').text('');
        }

        if (password.trim() === '') {
            $('#password-error-msg').text('Vui lòng nhập mật khẩu');
            hasError = true;
        } else {
            $('#password-error-msg').text('');
        }
        return hasError
    }

    $("#login-form-input").submit(function (event) {
        event.preventDefault()
        var username = $('#username').val()
        var password = $('#password').val()
        if (validateLogin(username, password) == false) {
            var formData = {
                tenTk: username,
                matKhau: password
            }
            var formDataJson = JSON.stringify(formData);

            $.ajax({
                url: '/api/v1/dang-nhap',
                type: 'POST',
                data: formDataJson,
                contentType: 'application/json',
                success: function(response) {
                    if (response == "") {
                        alert("Tên đăng nhập hoặc mật khẩu không đúng")
                    } else {
                        if (response.role == "NGUOI_DUNG") {
                            alert("Đăng nhập thành công, xin chào: " + username)
                            window.location.href = '/api/v1/index?id-nguoi-dung=' + response.maTk;
                        } else {
                            alert("Đăng nhập thành công, xin chào admin: " + username)
                            window.location.href = '/api/v1/admin/them-sach?id-nguoi-dung=' + response.maTk;
                        }
                    }
                },
                error: function(xhr, status, error) {
                    console.log('Error sending POST request');
                    console.log(error);
                }
            });
        }
    })
});


