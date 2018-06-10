<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="zhangfn" uri="/tld/zhang-functions.tld" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>H+ 后台主题UI框架 - Bootstrap Table</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="${pageContext.request.contextPath}/static/h/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/h/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/h/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/h/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/h/css/style.css?v=4.1.0" rel="stylesheet">
    <style>
        .zhi{
            float: right;
            margin-right: 20.8%;
            margin-top: 9px;
        }
        .endTime{
            margin-top: -1.5%;
            margin-left: 1%;
        }
    </style>

</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>查询条件</h5>
        </div>
        <div class="ibox-content">
            <form method="get" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-1 control-label">用户名：</label>

                    <div class="col-sm-2">
                        <input type="text" name="username" id="username" class="form-control">
                    </div>

                    <label class="col-sm-1_1 control-label">角色列表：</label>

                    <div class="col-sm-2">
                        <select class="form-control m-b" name="roleIds" id="roleIds">
                            <option value="">请选择角色</option>
                            <c:forEach var="obj" items="${roleList}">
                                <option value="${obj.id}">${obj.role}</option>
                            </c:forEach>
                        </select>
                        <!---<input type="text" name="roleIds" class="form-control"> -->
                    </div>

                    <label class="col-sm-1_1 control-label">注册时间：</label>

                    <div class="col-sm-2">
                        <input placeholder="开始日期" class="form-control layer-date" name="startTime" id="start">
                    </div>

                    <div class="col-sm-2">
                        <input placeholder="结束日期" class="form-control layer-date" name="endTime" id="end">
                    </div>

                </div>
                <div class="hr-line-dashed"></div>

                <div class="form-group">
                    <div class="col-sm-7 col-sm-offset-5">
                        <button class="btn btn-white" type="reset">重置</button>
                        <span class="col-sm-offset-1"></span>
                        <button class="btn btn-primary" type="button" onclick="sub()">查询</button>
                    </div>
                </div>

            </form>
        </div>
    </div>

    <div id="toolbar" class="btn_group">
        <shiro:hasPermission name='user:create'>
            <button id="btn_add" type="button" class="btn btn-primary" onclick="add()">新增</button>
        </shiro:hasPermission>
        <shiro:hasPermission name='user:update'>
            <button id="btn_update" type="button" class="btn btn-primary" onclick="update()">修改</button>
        </shiro:hasPermission>
        <shiro:hasPermission name="user:delete">
            <button id="btn_add" type="button" class="btn btn-primary" onclick="del()">删除</button>
        </shiro:hasPermission>
    </div>

    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>用户列表</h5>
        </div>
        <div class="ibox-content">
            <div class="row row-lg">
                <div class="col-sm-12">
                    <!-- Example Card View -->
                    <table id="table"></table>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- 全局js -->
<script src="${pageContext.request.contextPath}/static/h/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/static/h/js/bootstrap.min.js?v=3.3.6"></script>

<!-- 自定义js -->


<!-- Bootstrap table -->
<script src="${pageContext.request.contextPath}/static/h/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${pageContext.request.contextPath}/static/h/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${pageContext.request.contextPath}/static/h/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<!-- 时间插件的调用 -->
<script src="${pageContext.request.contextPath}/static/h/js/plugins/layer/laydate/laydate.js"></script>

<script>
    $(function(){
        $('#table').bootstrapTable({
            url: '${pageContext.request.contextPath}/user/getUserList',
            dataType:"json",
            method:'get',
            striped:true, // 是否显示行间色
            pagination:true, // 是否显示分页
            paginationLoop:false, //分页是否循环
            toolbar:'#toolbar',//设置toolbar
            toolbarAlign:'right',//设置toolbar位置
            sidePagination: "server", // 请求资源
            queryParamsType : "",
            queryParams : getParams,
            pageSize :10, // 每页显示条数
            pageNumber:1,//显示页数
            pageList:[1,5,10,50],
            paginationPreText:'上一页',
            paginationNextText:'下一页',
            columns: [{
                field: 'id',
                title: '',
                checkbox:true
            }, {
                field: 'role',
                title: '角色名称'
            },{
                field: 'description',
                title: '角色描述',
                formatter:roleList
            },{
                field:'resourceIds',
                title:'资源列表',
                formatter:resourceList
            },{
                field:'createTime',
                title:'创建时间',
                formatter:operationTime            	
            }]
        });
    });

    //获取参数
    function getParams(params) {
        var temp = {
            pageSize : params.pageSize,
            pageNumber : params.pageNumber,
            username:$("#username").val(),
            roleIds:$("#roleIds").val(),
            startTime:$("#start").val(),
            endTime:$("#end").val()
        };
        return temp;
    }

    //提交查询
    function sub(){
        $("#table").bootstrapTable('refresh');
    }

    //跳转到增加页面
    function add() {
        window.location.href = "${pageContext.request.contextPath}/user/create";
    }

    //跳转到修改页面
    function update(){
        var obj = $("#table").bootstrapTable('getSelections');
        if(obj.length>1){
            alert("只能修改一个用户");
            return;
        }
        var id = obj[0].id;
        window.location.href = "${pageContext.request.contextPath}/user/"+id+"/update";
    }

    //删除数据
    function del(){
        var obj = $("#table").bootstrapTable('getSelections');
        //拼接userId
        var ids ="";
        $(obj).each(function (index,value) {
            ids = ids + value.id+","
        });
        //去除最后一个逗号
        if(ids.length>0){
            ids = ids.substring(0,ids.length-1);
        }
        $.ajax({
            url:"${pageContext.request.contextPath}/user/delUserList",
            type:'get',
            data:{"ids":ids},
            success:function (data) {
                //如果是1表示存在
                if(data ){
                    alert("删除成功");
                    //刷新列表
                    sub();
                }
            }
        });
    }

    //设置成多选框
    function checkboxs(value,row,index){
        return "<input type='checkbox' value='"+value+"' />";
    }

    //格式化时间
    function operationTime(value,row,index){
        return times(value);
    }

    //时间转换
    function times(value){
        var now = new Date(value);
        var   year=now.getYear();
        var   month=now.getMonth()+1;
        var   date=now.getDate();
        var   hour=now.getHours();
        var   minute=now.getMinutes();
        var   second=now.getSeconds();
        return   1900+year+"-"+add0(month)+"-"+add0(date)+"   "+add0(hour)+":"+add0(minute)+":"+add0(second);
    }

    //翻译资源名称
    function resourceList(value, row, index){
        var str = ${zhangfn:resourcesAll()};
        var retStr = "";

        $(str).each(function(index,obj){
            var ids = value.split(",");
            $(ids).each(function(index1,Obj1){
                if(obj.id == Obj1){
                    retStr += obj.description+",";
                }
            })

        });
        var len = str.length;
        if(len>0){
            retStr = retStr.substr(0,retStr.length-1);
        }
        return retStr;
    }

    //根据数据是否添加0
    function add0(val){
        if(val<10){
            val= "0"+val;
        }
        return val;
    }

    //日期范围限制
    var start = {
        elem: '#start',
        format: 'YYYY/MM/DD hh:mm:ss',
        max: laydate.now(), //最大日期
        istime: true,
        istoday: false,
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '#end',
        format: 'YYYY/MM/DD hh:mm:ss',
        max: laydate.now(),
        istime: true,
        istoday: false,
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);
</script>
</body>
</html>
