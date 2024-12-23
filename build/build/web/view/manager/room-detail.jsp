<%-- 
    Document   : room-detail.jsp
    Created on : Jun 13, 2024, 2:15:04 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <title>Danh sách nhân viên | Quản trị Admin</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resource/doc/css/main.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">

        <style>
            .fixed-size-image {
                width: 100%;
                height: auto;
                max-width: 100%; /* Ensures the image fits within the column */
            }
        </style>
    </head>
    <body onload="time()" class="app sidebar-mini rtl">
        <!-- Navbar-->
        <header class="app-header">
            <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                            aria-label="Hide Sidebar"></a>
            <!-- Navbar Right Menu-->
            <ul class="app-nav">


                <!-- User Menu-->
                <li><a class="app-nav__item" href="/index.html"><i class='bx bx-log-out bx-rotate-180'></i> </a>

                </li>
            </ul>
        </header>
        <!-- Sidebar menu-->
        <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
        <aside class="app-sidebar">
            <div class="app-sidebar__user" ><img class="app-sidebar__user-avatar" src="" width="100px"
                                                 alt="User Image">
                <div>
                    <p class="app-sidebar__user-name"><b></b></p>
                    <p class="app-sidebar__user-designation">Chào mừng bạn trở lại</p>
                </div>
            </div>
            <hr>
            <ul class="app-menu">
                <li><a class="app-menu__item haha" href="phan-mem-ban-hang.html"><i class='app-menu__icon bx bx-cart-alt'></i>
                        <span class="app-menu__label">POS Bán Hàng</span></a></li>
                <li><a class="app-menu__item " href="index.html"><i class='app-menu__icon bx bx-tachometer'></i><span
                            class="app-menu__label">Bảng điều khiển</span></a></li>
                <li><a class="app-menu__item " href="#"><i class='app-menu__icon bx bx-user-voice'></i><span
                            class="app-menu__label">Quản lý khách hàng</span></a></li>
                <li><a class="app-menu__item" href="table-data-product.html"><i
                            class='app-menu__icon bx bx-purchase-tag-alt'></i><span class="app-menu__label">Quản lý sản phẩm</span></a>
                </li>
                <li><a class="app-menu__item active" href="${pageContext.request.contextPath}/list-staff"><i class='app-menu__icon bx bx-id-card'></i>
                        <span class="app-menu__label">Quản lý nhân viên</span></a></li>
                <li><a class="app-menu__item" href="table-data-oder.html"><i class='app-menu__icon bx bx-task'></i><span
                            class="app-menu__label">Quản lý đơn hàng</span></a></li>
                <li><a class="app-menu__item" href="table-data-banned.html"><i class='app-menu__icon bx bx-run'></i><span
                            class="app-menu__label">Quản lý nội bộ
                        </span></a></li>
                <li><a class="app-menu__item" href="table-data-money.html"><i class='app-menu__icon bx bx-dollar'></i><span
                            class="app-menu__label">Bảng kê lương</span></a></li>
                <li><a class="app-menu__item" href="quan-ly-bao-cao.html"><i
                            class='app-menu__icon bx bx-pie-chart-alt-2'></i><span class="app-menu__label">Báo cáo doanh thu</span></a>
                </li>
                <li><a class="app-menu__item" href="page-calendar.html"><i class='app-menu__icon bx bx-calendar-check'></i><span
                            class="app-menu__label">Lịch công tác </span></a></li>
                <li><a class="app-menu__item" href="#"><i class='app-menu__icon bx bx-cog'></i><span class="app-menu__label">Cài
                            đặt hệ thống</span></a></li>
            </ul>
        </aside>
        <main class="app-content">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb side">
                    <li class="breadcrumb-item active"><a href="list-staff"><b>Danh sách sản phẩm</b></a></li>
                </ul>
                <div id="clock"></div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <div class="tile-body">
                            <div class="row element-button">

                                <c:if test="${sessionScope.account.getRoleId() == 2}">
                                    <div class="col-sm-2">
                                        <a class="btn btn-add btn-sm" href="add-staff" title="Thêm nhân viên"><i class="fas fa-plus"></i>
                                            Thêm nhân viên mới</a>
                                    </div>
                                    <div class="col-sm-2">
                                        <a class="btn btn-delete btn-sm" type="button" title="Xóa" name="all" onclick="deleteSelectedStaff()"><i
                                                class="fas fa-trash-alt"></i> Xóa tất cả </a>
                                    </div>
                                </c:if>

                                <div class="col-sm-2">
                                    <button class="btn btn-add btn-sm" id="openModalButton">
                                        <i class="fa-solid fa-file-contract"></i> Dịch vụ
                                    </button>
                                </div>
                                <div class="col-sm-2">
                                    <a class="btn btn-primary" href="create-contract-servlet">
                                        Tạo hợp đồng thuê phòng trọ
                                    </a>
                                </div>
                            </div>
                            <div class="row">
                                <c:set value="${requestScope.roomByID}" var="room"/>
                                <div class="col-md-7">
                                    <h4>Thông tin chi tiết của phòng trọ</h4>

                                    <div class="form-group">
                                        <label for="roomName"><strong>Tên phòng trọ:</strong></label>
                                        <input type="text" id="roomName" class="form-control" value="${room.getRoomName()}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="price"><strong>Giá thuê:</strong></label>
                                        <input type="text" id="roomPrice" class="form-control" value="${room.price}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="quantity"><strong>Số người ở tối đa:</strong></label>
                                        <input type="text" id="roomQuantity" class="form-control" value="${room.maxOfQuantity}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="rent"><strong>Giá thuê:</strong></label>
                                    </div>
                                    <div class="form-group">
                                        <label for="amenities"><strong>Tiện nghi:</strong></label>

                                    </div>
                                    <div class="form-group">
                                        <label for="status"><strong>Trạng thái:</strong></label>
                                        <input type="text" id="status" class="form-control" value="Còn trống">
                                    </div>    
                                </div>
                                <div class="col-md-1"></div>
                                <div class="col-md-4"><img class="fixed-size-image"  src="${room.image}" alt="Hình ảnh đã bị lỗi!"/></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>




        </main>

        <div class="modal fade" id="tenancyDeposit" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Thêm phòng trọ</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="service-in-room-servlet" method="post">
                            <h1>Dịch vụ</h1>
                            <c:forEach items="${requestScope.listService}" var="c">
                                <c:set var="isChecked" value="false" />
                                <c:forEach items="${requestScope.listServiceOfRoom}" var="o">
                                    <c:if test="${o.getServiceOfLodgingHouse().getServiceId() == c.getServiceId()}">
                                        <c:set var="isChecked" value="true" />
                                    </c:if>
                                </c:forEach>
                                <div class="form-group form-check">
                                    <input type="checkbox" class="form-check-input" id="modalRoomCheckbox${c.getServiceId()}" value="${c.getServiceId()}" name="service" <c:if test="${isChecked}">checked</c:if>>
                                    <label class="form-check-label" for="modalRoomCheckbox${c.getServiceId()}">${c.getServiceName()}</label>
                                </div>
                            </c:forEach>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                                <button type="submit" class="btn btn-primary">Lưu lại</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>




        <!-- Essential javascripts for application to work-->

        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="src/jquery.table2excel.js"></script>
        <script src="js/main.js"></script>
        <!-- The javascript plugin to display page loading on top-->
        <script src="js/plugins/pace.min.js"></script>
        <!-- Page specific javascripts-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
        <!-- Data table plugin-->
        <script type="text/javascript" src="js/plugins/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/plugins/dataTables.bootstrap.min.js"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script type="text/javascript">
                                            $('#sampleTable').DataTable();
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

        <script>
            document.getElementById('openModalButton').addEventListener('click', function () {
                $('#tenancyDeposit').modal('show'); // Sử dụng jQuery để hiển thị modal
            });
        </script>
    </body>
</html>