<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="en">

    <head>
        <title>Danh sách nhân viên | Quản trị Admin</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resource/doc/css/main.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
        <!-- Font-icon css-->
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            .avatar{
                width: 200px !important;
                height: 200px !important;
                border-radius: 50% !important;
                object-fit: cover !important;
            }
            .avatarHeader{
                width: 100px !important;
                height: 100px !important;
                border-radius: 50% !important;
                object-fit: cover !important;
            }
            .changeAvatar{
                font-weight: bold;
                color: black;
            }
            .error {
                color: red;
                font-style: italic;
            }
        </style>
    </head>

    <body onload="time()" class="app sidebar-mini rtl">
        <div>
            <%@ include file="header.jsp" %>
        </div>
        <div class="row">
            <!-- Navbar -->
            <div class="col-sm-2">
                <%@ include file="left-menu-tenant.jsp" %>
            </div>
            <!-- Edit Profile -->
            <main class="app-content col-sm-10">
                <div class="row">
                    <div class="col-md-12">
                        <div class="app-title">
                            <ul class="app-breadcrumb breadcrumb">
                                <li class="breadcrumb-item"><a href="account?service=showProfile"><b>Thông Tin Người Dùng</b></a></li>
                            </ul>
                            <div id="clock"></div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <hr>
                        <div class="container bootstrap snippet">
                            <div class="row">
                                <div class="col-sm-4 text-center">
                                    <img src="${account.avatar}" class="avatar img-circle img-thumbnail" alt="avatar">
                                    <h6 class="changeAvatar">Thay Đổi Ảnh Đại Diện</h6>
                                    <h6 class="changeAvatar">(Định Dạng: .jpg)</h6>
                                    <input type="file" class="text-center center-block file-upload" value="upload" accept=".jpg" id="fileButton">
                                    <progress value="0" max="100" id="uploader">0%</progress>
                                </div>
                                <div class="col-sm-8">
                                    <div class="tab-content">
                                            <form class="form" action="account" method="post" id="registrationForm" onsubmit="return validateForm()">
                                                <div class="form-group row">
                                                    <div class="col-sm-6">
                                                        <input class="form-control" name="avatar" type="text" id="avatar123" value="${account.avatar}" style="display : none">
                                                        <div id="imgDiv" style="display : none"></div>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label for="full_name" class="col-sm-3 col-form-label"><h4>Họ và Tên</h4></label>
                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control" name="fullName" id="first_name" placeholder="Họ và Tên" title="enter your first name if any." value="${account.fullName}">
                                                        <span id="fullNameError" class="error"></span>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label for="userName" class="col-sm-3 col-form-label"><h4>User Name</h4></label>
                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control" name="userName" id="userName" placeholder="User Name" title="enter your user name." value="${account.username}">
                                                        <span id="userNameError" class="error"></span>
                                                        <span id="userNameError1" class="error1"></span>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label for="email" class="col-sm-3 col-form-label"><h4>Email</h4></label>
                                                    <div class="col-sm-6">
                                                        <input type="email" class="form-control" name="email" id="email" placeholder="Email" title="enter your email." value="${account.email}">
                                                        <span id="emailError" class="error"></span>
                                                        <span id="emailError1" class="error1"></span>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label for="phone" class="col-sm-3 col-form-label"><h4>Số Điện Thoại</h4></label>
                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control" name="phone" id="last_name" placeholder="Số Điện Thoại" title="enter your phone number." value="${account.phoneNumber}">
                                                        <span id="phoneError" class="error"></span>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group row">
                                                    <label for="dob" class="col-sm-3 col-form-label"><h4>Ngày Sinh</h4></label>
                                                    <div class="col-sm-6">
                                                        <input type="date" class="form-control" name="dob" id="dob" placeholder="Ngày Sinh" title="enter your date of birth." value="${account.dob}">
                                                        <span id="dobError" class="error"></span>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label for="gender" class="col-sm-3 col-form-label"><h4>Giới Tính</h4></label>
                                                    <div class="col-sm-6">
                                                        <select class="form-control" id="gender" name="gender" title="Chọn Giới Tính" required>
                                                            <c:choose>
                                                                <c:when test="${account.gender == 1}">
                                                                    <option value="1">Nam</option>
                                                                    <option value="2">Nữ</option>
                                                                    <option value="3">Khác</option>
                                                                </c:when>
                                                                <c:when test="${account.gender == 2}">
                                                                    <option value="2">Nữ</option>
                                                                    <option value="1">Nam</option>
                                                                    <option value="3">Khác</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="3">Khác</option>
                                                                    <option value="1">Nam</option>
                                                                    <option value="2">Nữ</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label for="province" class="col-sm-3 col-form-label"><h4>Tỉnh Thành</h4></label>
                                                    <div class="col-sm-6">
                                                        <select class="form-control" id="tinh" name="province" title="Chọn Tỉnh Thành" required>
                                                            <option value="${account.province}" selected>${account.province}</option>
                                                            <!-- Options loaded dynamically -->
                                                        </select>
                                                        <span id="provinceError" class="error"></span>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label for="district" class="col-sm-3 col-form-label"><h4>Quận Huyện</h4></label>
                                                    <div class="col-sm-6">
                                                        <select class="form-control" id="quan" name="district" title="Chọn Quận Huyện" required>
                                                            <option value="${account.district}" selected>${account.district}</option>
                                                            <!-- Options loaded dynamically -->
                                                        </select>
                                                        <span id="districtError" class="error"></span>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label for="ward" class="col-sm-3 col-form-label"><h4>Phường Xã</h4></label>
                                                    <div class="col-sm-6">
                                                        <select class="form-control" id="phuong" name="ward" title="Chọn Xã Phường" required>
                                                            <option value="${account.ward}" selected>${account.ward}</option>
                                                            <!-- Options loaded dynamically -->
                                                        </select>
                                                        <span id="wardError" class="error"></span>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label for="address" class="col-sm-3 col-form-label"><h4>Địa Chỉ Cụ Thể</h4></label>
                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control" name="address" id="address" placeholder="Nhập Địa Chỉ Cụ Thể" title="enter your address." value="${account.addressDetail}">
                                                        <span id="addressError" class="error"></span>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label for="cic" class="col-sm-3 col-form-label"><h4>Số CCCD</h4></label>
                                                    <div class="col-xs-6">
                                                        
                                                        <input type="text" class="form-control" name="cic" id="cic" placeholder="Căn Cước Công Dân" title="enter your CIC." value="${account.cic}">
                                                        <span id="cicError" class="error"></span>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-xs-12">
                                                        <button class="btn btn-lg btn-success" type="submit"><i class="glyphicon glyphicon-ok-sign"></i>Lưu Lại Thông Tin</button>
                                                        <button class="btn btn-lg" type="reset"><i class="glyphicon glyphicon-repeat"></i>Đặt Lại</button>
                                                    </div>
                                                    <input type="hidden" name="service" value="update">
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-xs-12">
                                                        <p class="error">${messageUpdateFail}</p>
                                                    </div>
                                                </div>
                                            </form>
<!--                                            <hr>
                                        </div>/tab-pane-->
                                    </div><!--/tab-content-->
                                </div><!--/col-9-->
                            </div><!--/row-->
                        </div><!--/container-->
                    </div><!--/col-sm-12-->
                </div><!--/row-->
            </main>
        </div>


        <!--End Edit Profile -->
        <script src="https://www.gstatic.com/firebasejs/4.2.0/firebase.js"></script>
        <%--<script>--%>

        <%--    //BE SURE TO PROTECT EVERYTHING IN THE CONFIG--%>
        <%--    //DON'T COMMIT IT!!!--%>

        <%--    // Initialize Firebase--%>
        <%--    const firebaseConfig = {--%>
        <%--        apiKey: "AIzaSyAHbXIdiO5i-nOweX-szmiNn4JSyrOjDi4",--%>
        <%--        authDomain: "chinhbeo-18d3b.firebaseapp.com",--%>
        <%--        databaseURL: "https://chinhbeo-18d3b.firebaseio.com",--%>
        <%--        projectId: "chinhbeo-18d3b",--%>
        <%--        storageBucket: "chinhbeo-18d3b.appspot.com",--%>
        <%--        messagingSenderId: "197467443558",--%>
        <%--        appId: "1:197467443558:web:7cccdbe875f827eb84b8a7",--%>
        <%--        measurementId: "G-D375CXH5LG"--%>
        <%--    };--%>
        <%--    firebase.initializeApp(firebaseConfig);--%>
        <%--</script>--%>
        <script src="https://esgoo.net/scripts/jquery.js"></script>
        <script>
                                            $(document).ready(function () {
                                                //Lấy tỉnh thành
                                                $.getJSON('https://esgoo.net/api-tinhthanh/1/0.htm', function (data_tinh) {
                                                    if (data_tinh.error == 0) {
                                                        $.each(data_tinh.data, function (key_tinh, val_tinh) {
                                                            $("#tinh").append('<option value="' + val_tinh.id + '">' + val_tinh.full_name + '</option>');
                                                        });
                                                        $("#tinh").change(function (e) {
                                                            var idtinh = $(this).val();
                                                            //Lấy quận huyện
                                                            $.getJSON('https://esgoo.net/api-tinhthanh/2/' + idtinh + '.htm', function (data_quan) {
                                                                if (data_quan.error == 0) {
                                                                    $("#quan").html('<option value="0">Quận Huyện</option>');
                                                                    $("#phuong").html('<option value="0">Phường Xã</option>');
                                                                    $.each(data_quan.data, function (key_quan, val_quan) {
                                                                        $("#quan").append('<option value="' + val_quan.id + '">' + val_quan.full_name + '</option>');
                                                                    });
                                                                    //Lấy phường xã  
                                                                    $("#quan").change(function (e) {
                                                                        var idquan = $(this).val();
                                                                        $.getJSON('https://esgoo.net/api-tinhthanh/3/' + idquan + '.htm', function (data_phuong) {
                                                                            if (data_phuong.error == 0) {
                                                                                $("#phuong").html('<option value="0">Phường Xã</option>');
                                                                                $.each(data_phuong.data, function (key_phuong, val_phuong) {
                                                                                    $("#phuong").append('<option value="' + val_phuong.id + '">' + val_phuong.full_name + '</option>');
                                                                                });
                                                                            }
                                                                        });
                                                                    });

                                                                }
                                                            });
                                                        });

                                                    }
                                                });
                                            });


        </script>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                document.getElementById('tinh').value = "${account.province}";
                document.getElementById('quan').value = "${account.district}";
                document.getElementById('phuong').value = "${account.ward}";
            });
        </script>

        <script type="text/javascript">
            const firebaseConfig = {
                apiKey: "AIzaSyBw_90A6Jnaw8MyUwsM3mzv0pauopphy2w",
                authDomain: "managehouse-df951.firebaseapp.com",
                projectId: "managehouse-df951",
                storageBucket: "managehouse-df951.appspot.com",
                messagingSenderId: "767540445597",
                appId: "1:767540445597:web:254317747a881714342f74",
                measurementId: "G-H4GS7YBG2S"
            };
            firebase.initializeApp(firebaseConfig);

            var image = '';
            // firebase bucket name
            // REPLACE WITH THE ONE YOU CREATE
            // ALSO CHECK STORAGE RULES IN FIREBASE CONSOLE
            var fbBucketName = 'images';

            // get elements
            var uploader = document.getElementById('uploader');
            var fileButton = document.getElementById('fileButton');

            // listen for file selection
            fileButton.addEventListener('change', function (e) {

                // what happened
                console.log('file upload event', e);

                // get file
                var file = e.target.files[0];

                // create a storage ref
            <%--var storageRef = firebase.storage().ref(`${fbBucketName}/${file.name}`);--%>
                const storageRef = firebase.storage().ref(file.name);
                // upload file
                var uploadTask = storageRef.put(file);

                // The part below is largely copy-pasted from the 'Full Example' section from
                // https://firebase.google.com/docs/storage/web/upload-files

                // update progress bar
                uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED, // or 'state_changed'
                        function (snapshot) {
                            // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
                            var progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
                            uploader.value = progress;
                            console.log('Upload is ' + progress + '% done');
                            switch (snapshot.state) {
                                case firebase.storage.TaskState.PAUSED: // or 'paused'
                                    console.log('Upload is paused');
                                    break;
                                case firebase.storage.TaskState.RUNNING: // or 'running'
                                    console.log('Upload is running');
                                    break;
                            }
                        }, function (error) {

                    // A full list of error codes is available at
                    // https://firebase.google.com/docs/storage/web/handle-errors
                    switch (error.code) {
                        case 'storage/unauthorized':
                            // User doesn't have permission to access the object
                            break;

                        case 'storage/canceled':
                            // User canceled the upload
                            break;

                        case 'storage/unknown':
                            // Unknown error occurred, inspect error.serverResponse
                            break;
                    }
                }, function () {
                    // Upload completed successfully, now we can get the download URL
                    // save this link somewhere, e.g. put it in an input field
                    var downloadURL = uploadTask.snapshot.downloadURL;
                    image = downloadURL;
                    console.log('downloadURL ===>', downloadURL);
                    let divLocation = document.getElementById("imgDiv");
                    let imgElement = document.createElement("img");
                    imgElement.src = downloadURL
                    imgElement.width = 100;
                    imgElement.height = 100;
                    console.log('pic ==', downloadURL)
                    divLocation.append(imgElement);
                    document.getElementById('avatar123').value = downloadURL;
                });

            });

            function resultImage() {
                console.log('image resulte -->', image)
                return image;
            }
        </script>

        <script>
            function validateForm() {
                var isValid = true;

                // Reset error messages
                var errorElements = document.getElementsByClassName("error");
                for (var i = 0; i < errorElements.length; i++) {
                    errorElements[i].innerText = "";
                }

                // Validate Full Name
                var regex = /\d/;
                var fullName = document.forms["registrationForm"]["fullName"].value;
                if (fullName === "" || fullName.length > 30 || regex.test(fullName)) {
                    document.getElementById("fullNameError").innerText = "*Họ và Tên không được để trống và không được chứa số*";
                    isValid = false;
                } else {
                    document.getElementById("fullNameError").innerText = " ";
                }

                // Validate Username
                var userName = document.forms["registrationForm"]["userName"].value;
                if (userName !== "" && (userName.length < 8 || userName.length > 20)) {
                    document.getElementById("userNameError").innerText = "*User Name phải từ 8-20 kí tự.*";
                    isValid = false;
                } else {
                    document.getElementById("userNameError").innerText = " ";
                }

                // Validate Email
                var email = document.forms["registrationForm"]["email"].value;
                var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
                if (email === "" || !emailPattern.test(email)) {
                    document.getElementById("emailError").innerText = "*Email không hợp lệ.*";
                    isValid = false;
                } else {
                    document.getElementById("emailError").innerText = " ";
                }

                // Validate Phone
                var phone = document.forms["registrationForm"]["phone"].value;
                var phonePattern = /^[0-9]{10}$/;
                if (phone === "" || !phonePattern.test(phone)) {
                    document.getElementById("phoneError").innerText = "*Số điện thoại không hợp lệ.*";
                    isValid = false;
                } else {
                    document.getElementById("phoneError").innerText = " ";
                }
                // Validate Dob
                var dob = document.forms["registrationForm"]["dob"].value;
                if (dob === "") {
                    document.getElementById("dobError").innerText = "*Ngày sinh không hợp lệ*";
                    isValid = false;
                } else {
                    document.getElementById("dobError").innerText = " ";
                }

                // Validate Address Detail
                var addressDetail = document.forms["registrationForm"]["address"].value;
                if (addressDetail.length > 100) {
                    document.getElementById("addressError").innerText = "*Địa Chỉ Cụ Thể không được quá 100 kí tự.*";
                    isValid = false;
                } else {
                    document.getElementById("addressError").innerText = " ";
                }

                // Validate CIC
                var cic = document.forms["registrationForm"]["cic"].value;
                var cicPattern = /^[0-9]{12}$/;
                if (cic === "" || !cicPattern.test(cic)) {
                    document.getElementById("cicError").innerText = "*Số căn cước công dân không hợp lệ.*";
                    isValid = false;
                } else {
                    document.getElementById("cicError").innerText = " ";
                }

                // Validate province
                const province = document.getElementById('tinh').value;
                const provinceError = document.getElementById('provinceError');
                if (province === "0") {
                    provinceError.textContent = "*Vui lòng chọn Tỉnh Thành.*";
                    isValid = false;
                } else {
                    provinceError.textContent = "";
                }

                // Validate district
                const district = document.getElementById('quan').value;
                const districtError = document.getElementById('districtError');
                if (district === "0") {
                    districtError.textContent = "*Vui lòng chọn Quận Huyện.*";
                    isValid = false;
                } else {
                    districtError.textContent = "";
                }

                // Validate ward
                const ward = document.getElementById('phuong').value;
                const wardError = document.getElementById('wardError');
                if (ward === "0") {
                    wardError.textContent = "*Vui lòng chọn Phường Xã.*";
                    isValid = false;
                } else {
                    wardError.textContent = "";
                }

                if (isValid) {
                    $.ajax({
                        type: 'POST',
                        url: 'account?service=checkUserAndEmail',
                        data: {
                            userName: userName,
                            email: email
                        },
                        success: function (response) {
                            // Xóa thông báo lỗi trước đó
                            $('.error').text('');

                            if (response.userNameError) {
                                $('#userNameError').text(response.userNameError);
                                isValid = false;
                            }
                            if (response.emailError) {
                                $('#emailError').text(response.emailError);
                                isValid = false;
                            }

                            if (isValid) {
                                alert("Cập Nhật Thông Tin Thành Công!");
                                document.getElementById("registrationForm").submit();
                            }
                        }
                    });
                }

                return false; // Ngăn form submit mặc định
            }
        </script>
        <script src="${pageContext.request.contextPath}/Resource/doc/js/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script src="${pageContext.request.contextPath}/Resource/doc/js/popper.min.js"></script>
        <script src="https://unpkg.com/boxicons@latest/dist/boxicons.js"></script>
        <!--===============================================================================================-->
        <script src="${pageContext.request.contextPath}/Resource/doc/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="${pageContext.request.contextPath}/Resource/doc/js/main.js"></script>
        <!--===============================================================================================-->
        <script src="${pageContext.request.contextPath}/Resource/js/profile.js"></script>
        <!--===============================================================================================-->
        <script src="${pageContext.request.contextPath}/Resource/doc/js/plugins/pace.min.js"></script>
        <!--===============================================================================================-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/Resource/doc/js/plugins/chart.js"></script>
        <!--===============================================================================================-->
        <script type="text/javascript">
            //Thời Gian
            function time() {
                var today = new Date();
                var weekday = new Array(7);
                weekday[0] = "Chủ Nhật";
                weekday[1] = "Thứ Hai";
                weekday[2] = "Thứ Ba";
                weekday[3] = "Thứ Tư";
                weekday[4] = "Thứ Năm";
                weekday[5] = "Thứ Sáu";
                weekday[6] = "Thứ Bảy";
                var day = weekday[today.getDay()];
                var dd = today.getDate();
                var mm = today.getMonth() + 1;
                var yyyy = today.getFullYear();
                var h = today.getHours();
                var m = today.getMinutes();
                var s = today.getSeconds();
                m = checkTime(m);
                s = checkTime(s);
                nowTime = h + " giờ " + m + " phút " + s + " giây";
                if (dd < 10) {
                    dd = '0' + dd
                }
                if (mm < 10) {
                    mm = '0' + mm
                }
                today = day + ', ' + dd + '/' + mm + '/' + yyyy;
                tmp = '<span class="date"> ' + today + ' - ' + nowTime +
                        '</span>';
                document.getElementById("clock").innerHTML = tmp;
                clocktime = setTimeout("time()", "1000", "Javascript");

                function checkTime(i) {
                    if (i < 10) {
                        i = "0" + i;
                    }
                    return i;
                }
            }
        </script>
    </body>

</html>