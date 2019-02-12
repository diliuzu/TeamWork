<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="row  form-horizontal">
    <div class="box-body">
        <div class="form-group">
            <label class="col-sm-2 control-label">订单ID</label>

            <div class="col-sm-10">
                ${tbOrder.orderId}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">实付金额</label>

            <div class="col-sm-10">
                ${tbOrder.payment}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">支付类型</label>

            <div class="col-sm-10">
                ${tbOrder.paymentType}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">邮费</label>

            <div class="col-sm-10">
                ${tbOrder.postFee}
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">订单状态</label>

            <div class="col-sm-10">
                ${tbOrder.status}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">创建时间</label>

            <div class="col-sm-10">
                ${tbOrder.createTime}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">更新时间</label>

            <div class="col-sm-10">
                ${tbOrder.updateTime}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">付款时间</label>

            <div class="col-sm-10">
                ${tbOrder.paymentTime}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">发货时间</label>

            <div class="col-sm-10">
                ${tbOrder.consignTime}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">交易完成时间</label>

            <div class="col-sm-10">
                ${tbOrder.endTime}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">交易关闭时间</label>

            <div class="col-sm-10">
                ${tbOrder.closeTime}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">物流名称</label>

            <div class="col-sm-10">
                ${tbOrder.shippingName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">物流单号</label>

            <div class="col-sm-10">
                ${tbOrder.shippingCode}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">用户ID</label>

            <div class="col-sm-10">
                ${tbOrder.userId}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">买家留言</label>

            <div class="col-sm-10">
                ${tbOrder.buyerMessage}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">买家昵称</label>

            <div class="col-sm-10">
                ${tbOrder.buyerNick}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">买家是否已评价</label>

            <div class="col-sm-10">
                ${tbOrder.buyerRate}
            </div>
        </div>
    </div>

</div>