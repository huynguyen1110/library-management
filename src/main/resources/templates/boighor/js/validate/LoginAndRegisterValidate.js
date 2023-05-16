    $('#register-form').submit(function (event) {
        event.preventDefault()
        var username = $('#username').val()
        var password = $('#password').val()
        var confirmPassword = $('#confirm-password').val()

        if (validate(username, password, confirmPassword) == false) {
            var formdd = $(this).serialize()
            var formData = {
                "tenTk": username,
                "matKhau": password,
                "nhapLaiMk": confirmPassword
            }
            // $.ajax({
            //     url: '/api/v1/them-tai-khoan',
            //     type: 'POST',
            //     data: formData,
            //     contentType: 'application/json',
            // }).done(function(response) {
            //     // Xử lý khi request thành công
            //     console.log('POST thành công!');
            //     console.log(response); // Dữ liệu response từ backend
            // }).fail(function(xhr, status, error) {
            //     // Xử lý khi có lỗi xảy ra
            //     console.log('Lỗi khi gửi POST request!');
            //     console.log(error);
            // });


            $.post('/api/v1/them-tai-khoan',   // url
                { myData: formData }, // data to be submit
                function(data, status, jqXHR) {// success callback
                $('p').append('status: ' + status + ', data: ' + data);
            }).done(function() { alert('Request done!'); }).fail(function(jqxhr, settings, ex) { alert('failed, ' + ex); });

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
