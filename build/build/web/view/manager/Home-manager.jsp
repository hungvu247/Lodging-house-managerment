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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <style>

            .room-card img {
                width: 100px;
                height: 100px; /* Đặt chiều cao của ảnh là 100% của phần tử div mẹ */
                object-fit: cover; /* Hiển thị ảnh sao cho nó được fill hết phần tử mà không bị distortion */
                margin: 10px auto; /* Căn giữa ảnh theo cả hai chiều ngang và dọc */
                display: block;
            }
            .room-card {

                border: 1px solid #ddd;
                border-radius: 8px;
                padding: 20px;
                margin-bottom: 20px;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            }
            .room-card h4 {
                margin-top: 0;
            }
            .room-card .btn {
                margin-right: 10px;
            }
            .btn-edit {
                background-color: #f0ad4e;
                color: white;
            }
            .btn-edit:hover {
                background-color: #ec971f;
                color: white;
            }
            .btn-delete {
                background-color: #d9534f;
                color: white;
            }
            .btn-delete:hover {
                background-color: #c9302c;
                color: white;
            }
            .btn-add {
                background-color: #5cb85c;
                color: white;
            }
            .btn-add:hover {
                background-color: #4cae4c;
                color: white;
            }

            .pagination {
                display: inline-block;
                position: sticky;
            }

            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                border: 1px solid #ddd;
            }

            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border: 1px solid #4CAF50;
            }

            .pagination a:hover:not(.active) {
                background-color: #ddd;
            }

            .button1 {
                background: linear-gradient(140.14deg, #ec540e 15.05%, #d6361f 114.99%)
                    padding-box,
                    linear-gradient(142.51deg, #ff9465 8.65%, #af1905 88.82%) border-box;
                border-radius: 7px;
                border: 2px solid transparent;

                text-shadow: 1px 1px 1px #00000040;
                box-shadow: 8px 8px 20px 0px #45090059;

                padding: 10px 40px;
                line-height: 20px;
                cursor: pointer;
                transition: all 0.3s;
                color: white;
                font-size: 18px;
                font-weight: 500;
            }

            .button1:hover {
                box-shadow: none;
                opacity: 80%;
            }
     a.btn.btn-add.btn-orange {
    background-color: orange;
    border-color: orange;
    color: white;
}

a.btn.btn-add.btn-orange:hover {
    background-color: darkorange; /* Change color on hover */
    border-color: darkorange;
}


        </style>
    </head>
    <body onload="time()" class="app sidebar-mini rtl">
        <!-- Navbar-->
        <header class="app-header">
            <!-- Sidebar toggle button-->
            <a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
            <!-- Navbar Right Menu-->
            <ul class="app-nav">
                <!-- User Menu-->
                <li><a class="app-nav__item" href="/index.html"><i class='bx bx-log-out bx-rotate-180'></i></a></li>
            </ul>
        </header>
        <!-- Sidebar menu-->
        <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
        <aside class="app-sidebar">
            <div class="app-sidebar__user">
                <img class="app-sidebar__user-avatar" src="${requestScope.account.getAvartar()}" width="100px" alt="User Image">
                <div>
                    <p class="app-sidebar__user-name"><b>${requestScope.account.fullName}</b></p>
                    <p class="app-sidebar__user-designation">Chào mừng bạn trở lại</p>
                </div>
            </div>
            <hr>
            <ul class="app-menu">
                <li><a class="app-menu__item active" href="/ManageLodgingHouse/management-lodging-houses?index=1"><i class='app-menu__icon bx bx-tachometer'></i><span class="app-menu__label">Quản lí trọ</span></a></li>
                <li><a class="app-menu__item" href="management-lodging-houses"><i class='app-menu__icon bx bx-id-card'></i><span class="app-menu__label">Quản lí thu chi</span></a></li>             
                <li><a class="app-menu__item" href="#"><i class='app-menu__icon bx bx-user-voice'></i><span class="app-menu__label">Chỉ số điện</span></a></li>
                <li><a class="app-menu__item" href="table-data-product.html"><i class='app-menu__icon bx bx-purchase-tag-alt'></i><span class="app-menu__label">Quản lý sản phẩm</span></a></li>
                <li><a class="app-menu__item" href="table-data-oder.html"><i class='app-menu__icon bx bx-task'></i><span class="app-menu__label">Chỉ số nước</span></a></li>
                <li><a class="app-menu__item" href="#"><i class='app-menu__icon bx bx-cog'></i><span class="app-menu__label">Cài đặt hệ thống</span></a></li>

                <li><a class="app-menu__item" href="${pageContext.request.contextPath}/investment-costs-servlet"><i
                            class='app-menu__icon bx bx-purchase-tag-alt'></i><span class="app-menu__label">Quản lí khoản phí đầu tư</span></a>
                </li> 
                <li><a class="app-menu__item" href="${pageContext.request.contextPath}/list-staff"><i class='app-menu__icon bx bx-id-card'></i>
                        <span class="app-menu__label">Quản lý nhân viên</span></a></li>
            </ul>
        </aside>
        <main class="app-content">
            <div class="app-title">
                <div>
                    <h1><i class="fa fa-dashboard"></i> Quản lí các phòng</h1>

                </div>
                <ul class="app-breadcrumb breadcrumb">
                    <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
                    <li class="breadcrumb-item"><a href="#">Quản lí các phòng</a></li>
                </ul>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <div class="tile-body">
                            <!-- Search and Filter Form -->
                            <form id="searchForm" method="GET" action="searchRooms">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="searchKey">Tìm kiếm</label>
                                            <input type="text" class="form-control" id="searchKey" name="searchKey" placeholder="Nhập tên phòng, số phòng...">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="roomType">Loại phòng</label>
                                            <select class="form-control" id="roomType" name="roomType">
                                                <option value="">Tất cả</option>
                                                <option value="single">Phòng đơn</option>
                                                <option value="double">Phòng đôi</option>
                                                <option value="suite">Phòng suite</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="roomStatus">Trạng thái phòng</label>
                                            <select class="form-control" id="roomStatus" name="roomStatus">
                                                <option value="">Tất cả</option>
                                                <option value="available">Có sẵn</option>
                                                <option value="occupied">Đã thuê</option>
                                                <option value="maintenance">Bảo trì</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="roomCount">Số lượng phòng</label>
                                            <select class="form-control" id="roomCount" name="roomCount">
                                                <option value="">Tất cả</option>
                                                <option value="rented">Đã cho thuê</option>
                                                <option value="not_rented">Chưa cho thuê</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                                    </div>

                                </div>
                            </form>
                            <div class="form-group row">
                                <div class="col-md-5 offset-md-1">
                                    <a href="add-room-servlet" class="btn btn-add">Thêm phòng mới</a>
                                </div>
                               
                            </div>


                            <!-- Room Cards -->
                            <div class="row">
                                <c:forEach items="${requestScope.paginationRoom.getListObject()}" var="o">
                                    <div class="col-md-4">
                                        <div class="room-card">
                                            <div class="row">
                                                <div class="col-sm-12"> <!-- Sử dụng col-sm-12 để ảnh nằm trên cùng một dòng -->
                                                    <a href="room-detail-servlet?id=${o.getRoomId()}"> <img src="${o.getImage()}" alt="alt"/></a> 
                                                </div>
                                                <div class="col-sm-12">
                                                    <table style="border: none">
                                                        <tr>
                                                            <td>Giá thuê:</td>
                                                            <td>${o.getPrice()} VND</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Trạng thái:</td>
                                                            <c:if test="${o.getStatus() == 0}">                                                      
                                                                <td>Còn</td>
                                                            </c:if>
                                                            <c:if test="${o.getStatus() == 1}">                                                     
                                                                <td>Hết</td>
                                                            </c:if>   
                                                        </tr>
                                                        <tr>
                                                            <td>Số lượng tối đa:</td>
                                                            <td>${o.getMaxOfQuantity()} người/Phòng</td>
                                                        </tr>   
                                                        <tr>
                                                            <td colspan="2">
                                                                <a href="editRoom?id=${room.id}" class="btn btn-edit btn-sm">Chỉnh sửa</a>
                                                                <a href="deleteRoom?id=${room.id}" class="btn btn-delete btn-sm">Xóa</a>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </c:forEach>

                            </div>
                            <!-- Add New Room Button -->
                            <div class="pagination">
                                <c:set var="currentPage" value="${requestScope.paginationRoom.getCurentPage()}"/>
                                <c:if test="${currentPage > 1}">
                                    <a href="home-manager?curentPage=${currentPage - 1}" >&laquo;</a>
                                </c:if>
                                <c:forEach  begin="${requestScope.paginationRoom.getStart()}" end="${requestScope.paginationRoom.getEnd()}" var="num">   
                                    <c:if test="${num != 0}">
                                        <c:if test="${num == currentPage}">
                                            <a href="home-manager?curentPage=${num}"  class="active">${num}</a>
                                        </c:if>
                                        <c:if test="${num != currentPage}">
                                            <a href="home-manager?curentPage=${num}" >${num}</a>
                                        </c:if>   
                                    </c:if>
                                </c:forEach>
                                <c:if test="${requestScope.paginationRoom.getNumberOfPage() > currentPage}">
                                    <a href="home-manager?curentPage=${currentPage + 1}">&raquo;</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="https://unpkg.com/boxicons@latest/dist/boxicons.js"></script>
    </body>
</html>