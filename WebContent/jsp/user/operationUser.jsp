<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="zhangfn" uri="/tld/zhang-functions.tld" %>
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
    <link href="${pageContext.request.contextPath}/static/bootstrap-select/css/bootstrap-select.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/Huploadify/Huploadify.css"/>

    <!-- 全局js -->
    <script src="${pageContext.request.contextPath}/static/h/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/static/h/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${pageContext.request.contextPath}/static/bootstrap-select/js/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap-select/js/i18n/defaults-zh_CN.js"></script>

    <!-- 上传文件 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/Huploadify/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/Huploadify/jquery.Huploadify.js"></script>

    <!-- validate -->
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>

    <script>
        var validator;

        $.validator.setDefaults({
            submitHandler: function() {
                //验证用户名是否存在
                //checkUserName();
                $("#myForm").submit();
            }
        });
        $().ready(function() {
            //如果修改初始化select
            var roles = ${user.roleIds}.split(",");
            $(roles).each(function (index,Obj) {
                if(Obj !=null&&Obj != ""){
                    $("#roleIds").find("option[value='"+Obj+"']").attr("selected",true)
                }
            });

            validator = $("#myForm").validate({
                ignore:false,
                rules:{
                    username:{
                        required:true,
                        remote:{
                            url:"${pageContext.request.contextPath}/user/checkUserName",
                            type:"get",
                            dataType:"json",
                            data:{username:function () {
                                return $("#username").val();
                            }}
                        },
                        rangelength:[6,12]
                    },
                    password:{
                        required:true,
                        rangelength:[6,12]
                    },
                    confirmPassword:{
                        required:true,
                        equalTo:'#password',
                        rangelength:[6,12]
                    },
                    roleIds:{
                        required:true
                    }
                },
                messages:{
                    username:{
                        required:"请输入用户名",
                        remote:"用户名已存在，请重新输入",
                        rangelength:"长度6-12位"
                    },
                    password:{
                        required:"请输入密码",
                        rangelength:"长度6-12位"
                    },
                    confirmPassword:{
                        required:"请输入确认密码",
                        equalTo:'确认密码和密码不一致',
                        rangelength:"长度6-12位"
                    },
                    roleIds:{
                        required:"请选择角色"
                    }
                }
            });
        });

        function checkUserName(){
            var flag = validator.element("#username");
            //console.log(flag);
            //获取username
            var username = $("#username").val();
            if(flag){
                $.ajax({
                    url:"${pageContext.request.contextPath}/user/checkUserName",
                    type:'get',
                    data:{"username":username},
                    success:function (data) {
                        //如果是1表示存在
                        if(data == 1){
                            //触发validata提示错误
                            console.log(data);
                            $("#username-error").css("display","block");
                            $("#username-error").html("该用户已存在！！！！");
                        }
                    }
                });
            }
        }
    </script>

</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>增加用户</h5>
                </div>
                <div class="ibox-content">
                    <form id="myForm" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/user/create">
                        <!-- 开始：放入隐藏的input -->

                        <input type="text" hidden="hidden" name="id" id="id" value="${user.id}" />

                        <!-- 结束：放入隐藏的input -->

                        <div class="form-group">
                            <label class="col-sm-2 control-label">用户名：</label>

                            <div class="col-sm-7">
                                <input name="username" id="username" class="form-control" value="${user.username}"/>
                            </div>
                        </div>

                        <div class="hr-line-dashed"></div>

                        <c:if test="${op == '修改'}">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">密码：</label>

                                <div class="col-sm-7">
                                    <input type="password" name="password" id="password" class="form-control"/>
                                </div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">确认密码：</label>

                                <div class="col-sm-7">
                                    <input type="password" name="confirmPassword" id="confirmPassword" class="form-control"/>
                                </div>
                            </div>

                            <div class="hr-line-dashed"></div>
                        </c:if>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">角色：</label>

                            <div class="col-sm-7">
                                <select class="form-control m-b" name = "roleIds" id="roleIds" multiple="">
                                    <c:forEach var="obj" items="${roleList}">
                                        <option value="${obj.id}">${obj.role}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="hr-line-dashed"></div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">头像：</label>

                            <div class="col-sm-7">
                                <c:if test="${empty user.userImg}">
                                    <img alt="imgs" id="imgs" class="img-circle" src="http://localhost:8080/uploadsImg/${user.userImg}" style="display: none;width: 100px;height: 100px;" />
                                </c:if>
                                <c:if test="${not empty user.userImg}">
                                    <img alt="imgs" id="imgs" class="img-circle" src="" style="display: none;width: 100px;height: 100px;" />
                                </c:if>
                                <span id="upload"></span>
                                <input type="text" style='display:none;' name="userImg" id="userImg" value=""/>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>

                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <input class="btn btn-primary" type="submit" value="提交" />
                                <%--<a class="btn btn-primary" href="javascript:subBtn();">提交</a>--%>
                                <a class="btn btn-default" href="javascript:retBtn();">返回</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function retBtn(){
        window.location.href = "${pageContext.request.contextPath}/user";
    }

    $(function(){

        $('#upload').Huploadify({
            auto:true,
            fileTypeExts:'*.jpg;*.png;*.exe',
            multi:true,
            fileSizeLimit:9999,
            showUploadedPercent:false,//是否实时显示上传的百分比，如20%
            showUploadedSize:false,
            removeTimeout:9999999,
            uploader:'${pageContext.request.contextPath}/uploadFile/upload',
            onUploadStart:function(){
                //alert('开始上传');
            },
            onInit:function(){
                //alert('初始化');
            },
            onUploadComplete:function(data){
                //alert('上传完成');
                console.log(data);
            },
            onDelete:function(file){
                console.log('删除的文件：'+file);
                console.log(file);
            },
            onUploadSuccess:function(file,data,response){
                $("#userImg").val(data);
                $("#imgs").attr("src","http://localhost:8080/uploadsImg/"+data);
                $("#imgs").css("display","block");
                //console.log(data);
            }
        });
    });

</script>
</body>
</html>