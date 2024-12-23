<%-- 
    Document   : table-contract-of-tenant
    Created on : Jun 16, 2024, 10:54:17 PM
    Author     : admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            .pagination {
                display: inline-block;
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
        </style>

    </head>
    <body>

        <div class="col-sm-2">
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

                    <li><a class="app-menu__item " href="index.html"><i class='app-menu__icon bx bx-tachometer'></i><span
                                class="app-menu__label">Bảng điều khiển</span></a></li>
                    <li><a class="app-menu__item " href="#"><i class='app-menu__icon bx bx-user-voice'></i><span
                                class="app-menu__label">Quản lý khách hàng</span></a></li>
                    <li><a class="app-menu__item" href="table-data-product.html"><i
                                class='app-menu__icon bx bx-purchase-tag-alt'></i><span class="app-menu__label">Quản lý sản phẩm</span></a>
                    </li>
                    <li><a class="app-menu__item" href="${pageContext.request.contextPath}/tenant-contract-servlet?statusAccept=1"><i class='app-menu__icon bx bx-id-card'></i>
                            <span class="app-menu__label">Quản lí hợp đồng</span></a></li>
                    <li><a class="app-menu__item active" href="${pageContext.request.contextPath}/room-tenant-servlet"><i class='app-menu__icon bx bx-id-card'></i>
                            <span class="app-menu__label">Quản lí phòng</span></a></li>
                </ul>
            </aside>
        </div>
        <main class="app-content">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb side">
                    <li class="breadcrumb-item active"><a href="#"><b>Danh sách phòng</b></a></li>
                </ul>
                <div id="clock"></div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <div class="tile-body">

                            <div class="row element-button">

                                <div class="input-group col-sm-5">
                                    <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" style="padding:0;"/>
                                </div>
                            </div>

                            <div id="content">                               
                                <table id="sampleTable" class="table table-hover table-bordered js-copytextarea">
                                    <thead>
                                        <tr>
                                            <th>Tên phòng</th>
                                            <th>Giá</th>
                                            <th>Số người tối đa</th>
                                            <th>Miêu tả</th>
                                            <th>Ảnh</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.paginationRoomTenant.getListObject()}" var="o">

                                            <tr onclick="goToRoomDetail('${o.getRoomId()}')">
                                                <td>${o.getRoomName()} </td>
                                                <td>${o.getPrice()}</td>
                                                <td>${o.getMaxOfQuantity()}</td>
                                                <td>${o.getDescription()}</td>
                                                <td><img src="${o.getImage()}" alt="Image description" style="height: 60px; width: 60px"></td>                                               
                                            </tr>

                                        </c:forEach>
                                    </tbody>
                                </table>

                                <div class="pagination">
                                    <c:set var="currentPage" value="${requestScope.paginationRoomTenant.getCurentPage()}"/>
                                    <c:if test="${currentPage > 1}">
                                        <a href="room-tenant-servlet?curentPage=${currentPage - 1}" >&laquo;</a>
                                    </c:if>
                                    <c:forEach  begin="${requestScope.paginationContractOfTenant.getStart()}" end="${requestScope.paginationRoomTenant.getEnd()}" var="num">   
                                        <c:if test="${num != 0}">
                                            <c:if test="${num == currentPage}">
                                                <a href="room-tenant-servlet?curentPage=${num}"  class="active">${num}</a>
                                            </c:if>
                                            <c:if test="${num != currentPage}">
                                                <a href="room-tenant-servlet?curentPage=${num}" >${num}</a>
                                            </c:if>   
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${requestScope.paginationRoomTenant.getNumberOfPage() > currentPage}">
                                        <a href="room-tenant-servlet?curentPage=${currentPage + 1}">&raquo;</a>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="contractModal" tabindex="-1" role="dialog" aria-labelledby="contractModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="contractModalLabel">Contract Details</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body" id="modalBody">
                            <!-- Nội dung hợp đồng sẽ được hiển thị ở đây -->
                        </div>

                    </div>
                </div>
            </div>


        </main>
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
                                                $(document).ready(function () {
                                                    $('.send-form-btn').on('click', function (e) {
                                                        e.preventDefault();
                                                        // Lấy dữ liệu từ các thuộc tính data- của button
                                                        var room = $(this).data('room');
                                                        var manager = $(this).data('manager');
                                                        var email = $(this).data('email');
                                                        var dateFrom = $(this).data('date-from');
                                                        var dateTo = $(this).data('date-to');
                                                        var deposit = $(this).data('deposit');
                                                        // Đưa dữ liệu vào form
                                                        $('#room').val(room);
                                                        $('#email').val(email);
                                                        $('#dateFrom').val(dateFrom);
                                                        $('#dateTo').val(dateTo);
                                                        $('#deposit').val(deposit);
                                                        // Gửi yêu cầu AJAX đến Servlet create-contract-servlet
                                                        $.ajax({
                                                            url: '${pageContext.request.contextPath}/create-contract-servlet',
                                                            type: 'POST',
                                                            data: $('#contractForm').serialize(),
                                                            success: function (response) {
                                                                // Hiển thị kết quả trả về trong modal
                                                                $('#modalBody').html(response); // Đưa dữ liệu vào modal
                                                                $('#contractModal').modal('show'); // Hiển thị modal
                                                            },
                                                            error: function () {
                                                                // Xử lý lỗi nếu có
                                                                Swal.fire('Lỗi', 'Không thể gửi hợp đồng', 'error');
                                                            }
                                                        });
                                                    });
                                                });
        </script>
        <script>
            function goToRoomDetail(roomId) {
                window.location.href = 'room-detail-servlet?id=' + roomId;
            }
        </script>

        <script>
            function searchByKeyword(param, targetElementId, activeValue) {
                var searchInfo = param.value.trim();
                $.ajax({
                    url: "/ManageLodgingHouse/home-manager",
                    type: "post", // Gửi qua phương thức GET
                    data: {
                        keyword: searchInfo,

                    },
                    success: function (responseData) {
                        document.getElementById(targetElementId).innerHTML = responseData;
                    }
                });
            }

            document.addEventListener("DOMContentLoaded", function () {
                var searchInput = document.querySelector('input[type="search"]');

                searchInput.addEventListener('input', function () {
                    searchByKeyword(this, "content");
                });
            });
        </script>
    </body>
</html>
