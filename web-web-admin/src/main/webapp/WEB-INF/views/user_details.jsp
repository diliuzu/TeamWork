<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="row  form-horizontal">
    <div class="box-body">
        <div class="form-group">
            <label class="col-sm-2 control-label">姓名</label>

            <div class="col-sm-10">
                ${tbUser.username}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">密码</label>

            <div class="col-sm-10">
                ${tbUser.password}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">电话</label>
            ${tbUser.phone}
            <div class="col-sm-10">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">邮箱</label>
            ${tbUser.email}
            <div class="col-sm-10">

            </div>
        </div>

    </div>

</div>