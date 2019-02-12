<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

    <title>我的商城 | 后台管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/dropzone.min.css" />
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/basic.min.css" />
    <link rel="stylesheet" href="/static/assets/plugins/wangEditor/wangEditor.min.css" />

   </head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
               商品管理

            </h1>
            <ol class="breadcrumb">
                <li><a href="/index"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">${tbItem.id==null?"新增":"编辑"}商品</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <!-- Small boxes (Stat box) -->
            <c:if test="${baseResult.status==500}">
                <div class="row">
                    <div class="box-body">
                        <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                           ${baseResult.message}
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title"> ${tbItem.id==null?"新增":"编辑"}商品</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->

                        <form:form cssClass="form-horizontal" action="/item/save" method="post" modelAttribute="tbItem">
                            <div class="box-body">
                                <form:hidden  path="id"/>
                                <div class="form-group">
                                    <label for="categoryName" class="col-sm-2 control-label">商品类别</label>
                                    <form:hidden  id="parentId" path="parent.id"/>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" onclick="showModal()"  id="categoryName" value="${tbItem.parent.name}" placeholder="请选择" readonly="true" />
                                        <%--<form:input cssClass="form-control" onclick="showModal()" path="categoryName" placeholder="请选择" readonly="true"/>--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="title" class="col-sm-2 control-label">标题</label>

                                    <div class="col-sm-10">
                                        <form:input path="title" cssClass="form-control" placeholder="标题"/>
                                       <%-- <input type="password" class="form-control" name="password" id="password" placeholder="密码">--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="sellPoint" class="col-sm-2 control-label">商品卖点</label>

                                    <div class="col-sm-10">
                                        <form:input path="sellPoint" cssClass="form-control" placeholder="商品卖点"/>
                                      <%--  <input type="text" class="form-control" name="phone" id="phone" placeholder="电话">--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="price" class="col-sm-2 control-label">价格</label>

                                    <div class="col-sm-10">
                                        <form:input path="price" cssClass="form-control" placeholder="价格"/>
                                            <%--  <input type="text" class="form-control" name="phone" id="phone" placeholder="电话">--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="num" class="col-sm-2 control-label">库存数量</label>

                                    <div class="col-sm-10">
                                        <form:input path="num" cssClass="form-control" placeholder="库存数量"/>
                                            <%--  <input type="text" class="form-control" name="phone" id="phone" placeholder="电话">--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="barcode" class="col-sm-2 control-label">商品条形码</label>
                                    <div class="col-sm-10">
                                        <form:input path="barcode" cssClass="form-control" placeholder="商品条形码"/>
                                        <div id="dropz1" class="dropzone" style="border: 2px dashed #0087F7;">
                                            <img  src="${tbItem.barcode}">
                                        </div>
                                        <%--<input type="email" class="form-control" name="email" id="email" placeholder="邮箱">--%>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="image" class="col-sm-2 control-label">图片</label>

                                    <div class="col-sm-10">
                                        <form:input path="image" cssClass="form-control" placeholder="图片"/>
                                        <div id="dropz2" class="dropzone" style="border: 2px dashed #0087F7;">
                                            <img  src="${tbItem.image}">
                                        </div>
                                            <%--<input type="email" class="form-control" name="email" id="email" placeholder="邮箱">--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="status" class="col-sm-2 control-label">商品状态</label>
                                    <div class="col-sm-10">
                                        <form:input path="status" cssClass="form-control" placeholder="商品状态"/>
                                            <%--<input type="email" class="form-control" name="email" id="email" placeholder="邮箱">--%>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" onclick="history.go(-1)" class="btn btn-default">返回</button>
                                <button type="submit"  class="btn btn-info pull-right">提交</button>
                            </div>
                            <!-- /.box-footer -->
                        </form:form>
                    </div>
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

<jsp:include page="../includes/footer.jsp"/>

</body>
<script src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core-3.5.js"></script>
<script src="/static/assets/plugins/dropzone/min/dropzone.min.js"></script>
<script src="/static/assets/plugins/wangEditor/wangEditor.min.js"></script>
<script>
    var setting = {
        view:{
            selectedMulti:false,
        },
        async: {
            enable: true,
            url:"/itemCat/getItemCats",
            autoParam:["id"]
        },
    };

    function showModal() {
        $("#modal-title").html("选择分类");
        $("#modal-detail").html('<ul id="treeDemo" class="ztree"></ul>');
        $.fn.zTree.init($("#treeDemo"), setting);
        $("#modal-user").modal("show");
    }

    function modalClick(){
        //隐藏
        //$("#modal-user").modal("hide");
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        nodes = zTree.getSelectedNodes();
        if (nodes.length==0){
            alert("请至少选择一个");
        }else{
            $("#parentId").val(nodes[0].id);
            $("#categoryName").val(nodes[0].name);

        }
    }

    var myDropzone = new Dropzone("#dropz1",{
        url: "/uploadPic",
        dictDefaultMessage: '拖动文件至此或者点击上传', // 设置默认的提示语句
        paramName: "dropFile", // 传到后台的参数名称
        init: function () {
            this.on("success", function (file, data) {
                // 上传成功触发的事件
                $("#pic").val(data.filename);
            });
        }
    });

    var myDropzone = new Dropzone("#dropz2",{
        url: "/uploadPic",
        dictDefaultMessage: '拖动文件至此或者点击上传', // 设置默认的提示语句
        paramName: "dropFile", // 传到后台的参数名称
        init: function () {
            this.on("success", function (file, data) {
                // 上传成功触发的事件
                $("#pic2").val(data.filename);
            });
        }
    });

    var E = window.wangEditor;
    var editor = new E('#editor');

    editor.customConfig.uploadImgServer = "/uploadContent";
    editor.customConfig.uploadFileName = "editorFile";

    editor.create();


</script>
</html>
