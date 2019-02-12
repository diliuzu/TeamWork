var App = function () {

    var handlerIcheckfun =function() {
        //iCheck for checkbox and radio inputs
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass: 'iradio_minimal-blue'
        })

        //全选/全不选
        _checkboxMaster = $(".checkbox-master");
        _checkbox = $("tbody").find("[type='checkbox']").not("[disabled]");
        _checkboxMaster.on("ifClicked", function (e) {
            // 当前状态已选中，点击后应取消选择
            if (e.target.checked) {
                _checkbox.iCheck("uncheck");
            }

            // 当前状态未选中，点击后应全选
            else {
                _checkbox.iCheck("check");
            }
        });
    };

    var  handlerShowDetails =function(url) {
        $.ajax({
            "url":url,
            "type":"GET",
            "dataType":"HTML",
            "success":function (data) {
                $("#modal-detail").html(data);
                $("#modal-user").modal("show");
            }
        });
    };


    var handlerDataTable = function (url,columns) {
        var dt =$("#useDataTable").DataTable({
            "autoWidth": true,
            "info":true,
            "lengthChange": false,
            "ordering": true,
            "paging": true,
            "searching": false,
            "serverSide":true,
            "ajax":{
                "url":url,
                "type":"POST",
            },
            "columns":columns,
            "drawCallback": function (settings) {
                //初始化后的回调函数
                App.icheckfun();

            },
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            }
        });
        return dt;
    };


    var handlerSearch = function(dt,param){
        dt.settings()[0].ajax.data = param;
        dt.ajax.reload();
    };

    var  handlerModalClick=function(){
        $("#modal-user").modal("hide");
    }


    var  handlerDeleteItem = function(url){
        var _idArray = new Array();
        _checkbox.each(function(){
            //判断当前复选框有没有被选中
            var delFlag = $(this).is(":checked");
            if(delFlag){
                _idArray.push($(this).attr("id"));
            }
        })
        if(_idArray.length==0){//弹出莫泰框
            $("#model-message").html("请至少选中一个");
            //弹出
            $("#modal-default").modal("show");

        }else{
            //ajax
            $.ajax({
                "url":url,
                "type":"POST",
                "data":{"ids":_idArray.toString()},
                "dataType":"JSON",
                "success":function(data){
                    //BaseResult  : status   message
                    //更新页面
                    window.location.reload();
                    alert(data.message);
                }
            });
        }
    };

    var handlerBtnModalClick= function (){
        //隐藏
        $("#modal-default").modal("hide");
    };


    return{
        icheckfun:function () {
            handlerIcheckfun();
        },
        showDetails:function(url){
          handlerShowDetails(url)
        },
        dataTable:function(url,columns){
            return handlerDataTable(url,columns);
        },
        search:function(dt,param){
          handlerSearch(dt,param);
        },
        deleteItem:function (url) {
            handlerDeleteItem(url);
        },
        btnModalClick:function () {
            handlerBtnModalClick();
        },
        modalClick:function () {
            handlerModalClick();
        },
    }

}();