<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="row  form-horizontal">
    <div class="box-body">
        <div class="form-group">
            <label class="col-sm-2 control-label">ID</label>

            <div class="col-sm-10">
                ${tbContent.id}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">所属分类</label>

            <div class="col-sm-10">
                ${tbContent.parent.name}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">标题</label>

            <div class="col-sm-10">
                ${tbContent.title}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">子标题</label>

            <div class="col-sm-10">
                ${tbContent.subTitle}
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">标题描述</label>

            <div class="col-sm-10">
                ${tbContent.titleDesc}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">链接</label>

            <div class="col-sm-10">
                ${tbContent.url}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">图片1</label>

            <div class="col-sm-10">
                ${tbContent.pic}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">图片2</label>

            <div class="col-sm-10">
                ${tbContent.pic2}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">内容</label>

            <div class="col-sm-10">
                ${tbContent.content}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">创建时间</label>

            <div class="col-sm-10">
                ${tbContent.created}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">更新时间</label>

            <div class="col-sm-10">
                ${tbContent.updated}
            </div>
        </div>
    </div>

</div>