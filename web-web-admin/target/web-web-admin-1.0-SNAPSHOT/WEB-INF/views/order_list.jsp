<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

    <title>我的商城 | 后台管理</title>
    <jsp:include page="../includes/header.jsp"/>


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
                订单管理
            </h1>
            <ol class="breadcrumb">
                <li><a href="/index"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">订单列表</li>
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
                            <h3 class="box-title">订单列表</h3>
                        </div>
                        <div class="row">
                            <div class="col-sm-12" style="padding-left: 25px">
                                <a href="/item/form" type="button" class="btn btn-sm btn-default"><i
                                        class="fa fa-plus"></i> 新增</a>&nbsp;&nbsp;
                                <a type="button" onclick="App.deleteItem('/item/delSelect')" class="btn btn-sm btn-default"><i class="fa fa-trash"></i> 删除</a>&nbsp;&nbsp;
                                <a type="button" class="btn btn-sm btn-default"><i class="fa fa-download"></i> 导入</a>&nbsp;&nbsp;
                                <a type="button" class="btn btn-sm btn-default"><i class="fa fa-upload"></i> 导出</a>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <!--row  search-->
                        <%--<div class="row" style="padding-top: 10px">

                            <div class="col-sm-3 form-group">
                                <label for="username" class="col-sm-3 control-label">姓名</label>
                                <div class="col-sm-8">
                                    &lt;%&ndash; <input type="text" class="form-control" value="${tbTbUser.username}" name="username" id="username" placeholder="姓名">&ndash;%&gt;
                                    <input cssClass="form-control" id="username" placeholder="姓名"/>
                                </div>
                            </div>

                            <div class="col-sm-3 form-group">
                                <label for="phone" class="col-sm-3 control-label">电话</label>

                                <div class="col-sm-8">
                                    <input id="phone" cssClass="form-control" placeholder="电话"/>
                                    &lt;%&ndash;  <input type="text" class="form-control" name="phone" id="phone" placeholder="电话">
                                 &ndash;%&gt; </div>
                            </div>

                            <div class="col-sm-3 form-group">
                                <label for="email" class="col-sm-3 control-label">邮箱</label>

                                <div class="col-sm-8">
                                    <input id="email" cssClass="form-control" placeholder="邮箱"/>
                                    &lt;%&ndash;<input type="email" class="form-control" name="email" id="email" placeholder="邮箱">
                                &ndash;%&gt;</div>
                            </div>
                               <div class="col-sm-3 ">
                                   <button type="submit" onclick="mysearch()" class="btn btn-primary btn-sm">搜索</button>
                               </div>

                        </div>--%>
                        <!-- /.row search-->
                        <div class="box-body table-responsive no-padding">
                            <table id="useDataTable" class="table table-hover">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" class="minimal checkbox-master"></th>
                                    <th>订单ID</th>
                                    <th>实付金额</th>
                                    <th>订单状态</th>
                                    <th>买家留言</th>
                                    <th>买家昵称</th>
                                    <th>创建时间</th>
                                    <th>管理</th>
                                </tr>
                                </thead>
                                <tbody>

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
                <h4 class="modal-title">修改订单状态</h4>
            </div>
            <div class="modal-body">
                <input id="orderId" hidden="hidden">
                <label>当前订单状态</label>
                <select id="statusSelect">
                    <option value="1">未付款</option>
                    <option value="2">已付款</option>
                    <option value="3">未发货</option>
                    <option value="4">已发货</option>
                    <option value="5">交易成功</option>
                    <option value="6">交易关闭</option>
                </select>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">关闭</button>
                <button type="button" onclick="updateStatus()" class="btn btn-primary">确定</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!--/.modal-->


<jsp:include page="../includes/footer.jsp"/>
<script src="/static/assets/js/datetime.js"></script>
<script src="/static/assets/js/App.js"></script>
<script>
    var url="/order/selectPageOrder";

    var columns =[
        {
            "data": function (row, type, val, meta) {
                return '<input id="' + row.id + '" type="checkbox" class="minimal">';
            }
        },
        {"data": "orderId"},
        {"data": "payment"},
        {"data": function (row, type, val, meta) {
            var result;
            //1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
            switch (row.status) {
                case 1:
                    result="未付款";
                    break;
                case 2:
                    result="已付款";
                    break;
                case 3:
                    result="未发货";
                    break;
                case 4:
                    result="已发货";
                    break;
                case 5:
                    result="交易成功";
                    break;
                case 6:
                    result="交易关闭";
                    break;
            }
                return result;
            }},
        {"data": "buyerMessage"},
        {"data": "buyerNick"},
        {"data": function (row, type, val, meta) {
                return DateTime.format(row.createTime,"yyyy-MM-dd HH:mm:ss");
            }},
        {
            "data": function (row, type, val, meta) {
                return '<button type="button" onclick="App.showDetails(\'/order/details?id='+row.orderId+'\')" class="btn btn-sm btn-default"><i class="fa fa-search"></i>查看</button>' +
                    '<a type="button" onclick="myModalClick('+row.orderId+','+row.status+')" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i>编辑</a>'
            }
        }
    ];

    var dt = App.dataTable(url,columns);

    function mysearch(){
        var username = $("#username").val();
        var email = $("#email").val();
        var phone = $("#phone").val();
        var param = {
            "username":username,
            "email":email,
            "phone":phone
        };
        App.search(dt,param);
    }
    function modalClick(){
        $("#modal-user").modal("hide");
    }

    function myModalClick(orderId,status){
        $("#modal-default").modal("show");
        $("#statusSelect").val(status);
        $("#orderId").val(orderId);
    }

    function updateStatus() {
        var status = $("#statusSelect").val();
        var orderId = $("#orderId").val();
        window.location.href="/order/save?strstatus="+status+"&id="+orderId;
        /*$.ajax({
            type:"POST",
            data:{status:status},
            url:"/order/"
        })*/
    }
</script>
</body>
</html>
