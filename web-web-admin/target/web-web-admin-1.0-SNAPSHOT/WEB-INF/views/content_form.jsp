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
               内容管理

            </h1>
            <ol class="breadcrumb">
                <li><a href="/index"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">${tbContent.id==null?"新增":"编辑"}内容</li>
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
                            <h3 class="box-title"> ${tbContent.id==null?"新增":"编辑"}内容</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->

                        <form:form cssClass="form-horizontal" action="/content/save" method="post" modelAttribute="tbContent">
                            <div class="box-body">
                                <form:hidden  path="id"/>
                                <div class="form-group">
                                    <label for="categoryName" class="col-sm-2 control-label">父级分类</label>
                                    <form:hidden  id="parentId" path="parent.id"/>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" onclick="showModal()"  id="categoryName" value="${tbContent.parent.name}" placeholder="请选择" readonly="true" />
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
                                    <label for="subTitle" class="col-sm-2 control-label">子标题</label>

                                    <div class="col-sm-10">
                                        <form:input path="subTitle" cssClass="form-control" placeholder="子标题"/>
                                      <%--  <input type="text" class="form-control" name="phone" id="phone" placeholder="电话">--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="titleDesc" class="col-sm-2 control-label">标题描述</label>

                                    <div class="col-sm-10">
                                        <form:input path="titleDesc" cssClass="form-control" placeholder="标题描述"/>
                                            <%--  <input type="text" class="form-control" name="phone" id="phone" placeholder="电话">
                                         --%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="url" class="col-sm-2 control-label">链接</label>

                                    <div class="col-sm-10">
                                        <form:input path="url" cssClass="form-control" placeholder="链接"/>
                                            <%--  <input type="text" class="form-control" name="phone" id="phone" placeholder="电话">--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pic" class="col-sm-2 control-label">图片1</label>
                                    <div class="col-sm-10">
                                        <form:input path="pic" cssClass="form-control" placeholder="图片1"/>
                                        <div id="dropz1" class="dropzone" style="border: 2px dashed #0087F7;">
                                            <img  src="${tbContent.pic}">
                                        </div>
                                        <%--<input type="email" class="form-control" name="email" id="email" placeholder="邮箱">--%>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="pic2" class="col-sm-2 control-label">图片2</label>

                                    <div class="col-sm-10">
                                        <form:input path="pic2" cssClass="form-control" placeholder="图片2"/>
                                        <div id="dropz2" class="dropzone" style="border: 2px dashed #0087F7;">
                                            <img  src="${tbContent.pic2}">
                                        </div>
                                            <%--<input type="email" class="form-control" name="email" id="email" placeholder="邮箱">--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="content" class="col-sm-2 control-label">内容</label>

                                    <div class="col-sm-10">
                                        <form:hidden path="content" cssClass="form-control" placeholder="内容"/>
                                        <div id="editor">
                                                ${tbContent.content}
                                        </div>
                                            <%--<input type="email" class="form-control" name="email" id="email" placeholder="邮箱">--%>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" onclick="history.go(-1)" class="btn btn-default">返回</button>
                                <button type="submit" onclick="save()" class="btn btn-info pull-right">提交</button>
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
            url:"/category/getCategories",
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

    function save() {
        $("#content").val(editor.txt.html());
    }
</script>
</html>
