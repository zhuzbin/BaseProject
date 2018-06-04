<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/5/18
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>H+ 后台主题UI框架 - 树形视图</title>
    <link href="${pageContext.request.contextPath}/static/bootstrap-treegrid/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/static/bootstrap-treegrid/css/jquery.treegrid.css" rel="stylesheet" />
</head>
<body class="gray-bg">
    <table id="tb" ></table>

    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap-treegrid/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap-treegrid/js/jquery.treegrid.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap-treegrid/js/jquery.treegrid.bootstrap3.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/BootStrap-treegrid.js"></script>

<script>
    $(document).ready(function () {
        $('#tb').treegridData({
            id: 'id',
            parentColumn: 'parentId',
            type: "GET", //请求数据的ajax类型
            url: '${pageContext.request.contextPath}/resource/resourceList',   //请求数据的ajax的url
            ajaxParams: {}, //请求数据的ajax的data属性
            expandColumn: 'name',//在哪一列上面显示展开按钮
            striped: true,   //是否各行渐变色
            bordered: true,  //是否显示边框
            expandAll: true,  //是否全部展开
            columns: [
                {
                    title: '名称',
                    field: 'name'
                },
                {
                    title: '类型',
                    field: 'type'
                },
                {
                    title: 'URL路径',
                    field: 'url'
                },
                {
                    title: '权限字符串',
                    field: 'permission'
                }
            ]
        });
    });

    function operation(){
        var addStr = "<a class='btn btn-default btn-rounded' href='buttons.html#'>增加</a>";

        return addStr;
    }
</script>
</body>
</html>
