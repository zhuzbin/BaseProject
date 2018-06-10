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

    <!-- 全局js -->
    <script src="${pageContext.request.contextPath}/static/h/js/jquery.min.js?v=2.1.4"></script>

    <!--treeView -->
    <script src="${pageContext.request.contextPath}/static/bootstrap-treeview/src/js/bootstrap-treeview.js"></script>
    <script>

        function getTvStateData() {
            var defaultData = [
                {
                    text: 'Parent 1',
                    href: '#parent1',
                    tags: ['4'],
                    state: {
                        checked: true
                    },
                    nodes: [
                        {
                            text: 'Child 1',
                            href: '#child1',
                            tags: ['2'],
                            nodes: [
                                {
                                    text: 'Grandchild 1',
                                    href: '#grandchild1',
                                    tags: ['0']
                                },
                                {
                                    text: 'Grandchild 2',
                                    href: '#grandchild2',
                                    tags: ['0']
                                }
                            ]
                        },
                        {
                            text: 'Child 2',
                            href: '#child2',
                            tags: ['0']
                        }
                    ]
                },
                {
                    text: 'Parent 2',
                    href: '#parent2',
                    tags: ['0'],
                    nodes: [
                        {
                            text: 'Child 1',
                            href: '#child1',
                            tags: ['2'],
                            nodes: [
                                {
                                    text: 'Grandchild 1',
                                    href: '#grandchild1',
                                    tags: ['0']
                                },
                                {
                                    text: 'Grandchild 2',
                                    href: '#grandchild2',
                                    tags: ['0']
                                }
                            ]
                        },
                        {
                            text: 'Child 2',
                            href: '#child2',
                            tags: ['0']
                        }
                    ]
                },
                {
                    text: 'Parent 3',
                    href: '#parent3'
                },
                {
                    text: 'Parent 4',
                    href: '#parent4',
                    tags: ['0']
                },
                {
                    text: 'Parent 5',
                    href: '#parent5',
                    tags: ['0']
                }
            ];
            return defaultData;
        }
        
        $(function () {
            $.ajax({
                type: "Post",
                url: "/Home/GetTreeJson",
                dataType: "json",
                success: function (result) {
                    $('#tree').treeview({
                        data: result,         // 数据源
                        showCheckbox: true,   //是否显示复选框
                        highlightSelected: true,    //是否高亮选中
                        //nodeIcon: 'glyphicon glyphicon-user',    //节点上的图标
                        nodeIcon: 'glyphicon glyphicon-globe',
                        emptyIcon: '',    //没有子节点的节点图标
                        multiSelect: false,    //多选
                        onNodeChecked: function (event,data) {
                            alert(data.nodeId);
                        },
                        onNodeSelected: function (event, data) {
                            alert(data.nodeId);
                        }
                    });
                },
                error: function () {
                    alert("树形结构加载失败！")
                }
            });
        })
        $(function() {
            var $checkableTree = $('#searchTree').treeview({
                data: getTvStateData(), //数据
                showCheckbox:true,
                levels: 1,
                onNodeChecked:nodeChecked ,
                onNodeUnchecked:nodeUnchecked
            });

            //$('#treeview-checkable123123').treeview("checkAll", { silent: true });
        });

        var nodeCheckedSilent = false;
        function nodeChecked (event, node){
            if(nodeCheckedSilent){
                return;
            }
            nodeCheckedSilent = true;
            checkAllParent(node);
            checkAllSon(node);
            nodeCheckedSilent = false;
        }

        var nodeUncheckedSilent = false;
        function nodeUnchecked  (event, node){
            if(nodeUncheckedSilent)
                return;
            nodeUncheckedSilent = true;
            uncheckAllParent(node);
            uncheckAllSon(node);
            nodeUncheckedSilent = false;
        }

        //选中全部父节点
        function checkAllParent(node){
            $('#searchTree').treeview('checkNode',node.nodeId,{silent:true});
            var parentNode = $('#searchTree').treeview('getParent',node.nodeId);
            if(!("nodeId" in parentNode)){
                return;
            }else{
                checkAllParent(parentNode);
            }
        }
        //取消全部父节点
        function uncheckAllParent(node){
            $('#searchTree').treeview('uncheckNode',node.nodeId,{silent:true});
            var siblings = $('#searchTree').treeview('getSiblings', node.nodeId);
            var parentNode = $('#searchTree').treeview('getParent',node.nodeId);
            if(!("nodeId" in parentNode)) {
                return;
            }
            var isAllUnchecked = true;  //是否全部没选中
            for(var i in siblings){
                if(siblings[i].state.checked){
                    isAllUnchecked=false;
                    break;
                }
            }
            if(isAllUnchecked){
                uncheckAllParent(parentNode);
            }

        }

        //级联选中所有子节点
        function checkAllSon(node){
            $('#searchTree').treeview('checkNode',node.nodeId,{silent:true});
            if(node.nodes!=null&&node.nodes.length>0){
                for(var i in node.nodes){
                    checkAllSon(node.nodes[i]);
                }
            }
        }
        //级联取消所有子节点
        function uncheckAllSon(node){
            $('#searchTree').treeview('uncheckNode',node.nodeId,{silent:true});
            if(node.nodes!=null&&node.nodes.length>0){
                for(var i in node.nodes){
                    uncheckAllSon(node.nodes[i]);
                }
            }
        }

        //获取选中的值
        function getCheckeds(){
            var list = $('#searchTree').treeview('getChecked');
            //console.log(list);
            var nodeIds = '';
            if(list.length>0){
                $(list).each(function(index,obj){
                    nodeIds = nodeIds + obj.nodeId+",";
                    console.log(obj);
                });
            }
            //alert(nodeIds);
        }
    </script>
</head>
<body>
    <div id="searchTree"></div>
    <button onclick="getCheckeds()">查看选中的值</button>
</body>
</html>
