<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="row  form-horizontal">
    <div class="box-body">
        <div class="form-group">
            <label class="col-sm-2 control-label">ID</label>

            <div class="col-sm-10">
                ${tbItem.id}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">所属分类</label>

            <div class="col-sm-10">
                ${tbItem.parent.name}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">标题</label>

            <div class="col-sm-10">
                ${tbItem.title}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">商品卖点</label>

            <div class="col-sm-10">
                ${tbItem.sellPoint}
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">价格</label>

            <div class="col-sm-10">
                ${tbItem.price}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">库存数量</label>

            <div class="col-sm-10">
                ${tbItem.num}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">商品条形码</label>

            <div class="col-sm-10">
                ${tbItem.barcode}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">图片</label>

            <div class="col-sm-10">
                ${tbItem.image}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">商品状态</label>

            <div class="col-sm-10">
                <c:choose>
                    <c:when test="${tbItem.status==1}">
                        正常
                    </c:when>
                    <c:when test="${tbItem.status==2}">
                        下架
                    </c:when>
                    <c:when test="${tbItem.status==3}">
                        删除
                    </c:when>
                </c:choose>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">创建时间</label>

            <div class="col-sm-10">
                ${tbItem.created}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">更新时间</label>

            <div class="col-sm-10">
                ${tbItem.updated}
            </div>
        </div>
    </div>

</div>