<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

    <title>我的商城 | 后台管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet"href="/static/assets/plugins/treeTable/themes/vsStyle/treeTable.css">


</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>

    <!-- TbContent Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- TbContent Header (Page header) -->
        <section class="content-header">
            <h1>
                商品类别管理
            </h1>
            <ol class="breadcrumb">
                <li><a href="/index"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">内容列表</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <c:if test="${baseResult.status==200}">
                <div class="row">
                    <div class="box-body">
                        <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </div>
                </div>
            </c:if>

            <!-- Small boxes (Stat box) -->
            <!-- /.row -->
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">内容列表</h3>
                        </div>
                        <div class="row">
                            <div class="col-sm-12" style="padding-left: 25px">
                                <a href="/category/form" type="button" class="btn btn-sm btn-default"><i class="fa fa-plus"></i> 新增</a>&nbsp;&nbsp;
                                <a type="button" class="btn btn-sm btn-default"><i class="fa fa-trash"></i> 删除</a>&nbsp;&nbsp;
                                <a type="button" class="btn btn-sm btn-default"><i class="fa fa-download"></i> 导入</a>&nbsp;&nbsp;
                                <a type="button" class="btn btn-sm btn-default"><i class="fa fa-upload"></i> 导出</a>
                            </div>
                        </div>
                        <!-- /.box-header -->

                        <div class="box-body table-responsive no-padding">
                            <table id="treeTable" class="table table-hover">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>名称</th>
                                    <th>排序</th>
                                    <th>创建时间</th>
                                    <th>管理</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${ListCategories}" var="category">
                                    <tr id="${category.id}" pId="${category.parent.id}">
                                        <td>${category.id}</td>
                                        <td>${category.name}</td>
                                        <td>${category.sortOrder}</td>
                                        <td>${category.created}</td>
                                        <td>
                                            <a type="button" href="/category/form?parent.id=${category.id}&parent.name=${category.name}" class="btn btn-sm btn-default"><i class="fa fa-plus"></i> 创建下一级目录</a>
                                            <a type="button" href="/category/form?id=${category.id}" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i> 编辑</a>
                                            <a type="button" href="/category/deleteById?id=${category.id}" class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> 删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
            <!-- /.row (main row) -->
        </section>
        <!-- /.content -->
    </div>
    <jsp:include page="../includes/copyright.jsp"/>


    <!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!--/.modal-->
<div class="modal fade" id="modal-default" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">友情提醒</h4>
            </div>
            <div class="modal-body">
                <p id="model-message"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">关闭</button>
                <button type="button" onclick="btnModalClick()" class="btn btn-primary">确定</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!--/.modal-->


<jsp:include page="../includes/footer.jsp"/>
<script src="/static/assets/plugins/treeTable/jquery.treeTable.js"></script>
<script >

    var option = {
        expandLevel : 2,
        column:1
    };
    $("#treeTable").treeTable(option);
</script>