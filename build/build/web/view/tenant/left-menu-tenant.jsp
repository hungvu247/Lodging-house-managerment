<%-- 
    Document   : left-menu-tenant
    Created on : May 20, 2024, 3:50:09 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Sidebar menu-->
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <div class="app-sidebar__user">
        <img class="avatarHeader app-sidebar__user-avatar" src="${account.getAvatar()}" width="50px"
             alt="User Image">
        <div>
            <p class="app-sidebar__user-name"><b>${account.fullName}</b></p>
            <p class="app-sidebar__user-designation">Chào mừng bạn trở lại</p>
        </div>
    </div>
    <hr>
    <ul class="app-menu">
        <li><a class="app-menu__item ${tagMenu=="showRoom"?"active":""}" href="room?service=showRoomInfor"><i class='app-menu__icon  bx bxs-home'></i><span
                    class="app-menu__label">Thông Tin Phòng</span></a></li>
        <li><a class="app-menu__item ${tagMenu=="showProfile"?"active":""}" href="account?service=showProfile"><i class='app-menu__icon bx bx-id-card'></i><span
                    class="app-menu__label">Hồ Sơ</span></a></li>
        <li><a class="app-menu__item ${tagMenu=="changePassword"?"active":""}" href="account?service=showChangePassword"><i class='app-menu__icon bx bxs-lock '></i> <span
                    class="app-menu__label">Mật Khẩu</span></a></li>
        <li><a class="app-menu__item" href=""><i class='app-menu__icon bx bx-task'></i><span
                    class="app-menu__label">Dịch Vụ</span></a></li>
        <li><a class="app-menu__item" href=""><i class='app-menu__icon bx bx-task'></i><span
                    class="app-menu__label">Hóa Đơn</span></a></li>
        <li><a class="app-menu__item" href="account?service=logout"><i class='app-menu__icon bx bx-log-out'></i><span
                    class="app-menu__label">Đăng Xuất</span></a></li>
</aside>