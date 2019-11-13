<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function delfunc(id) {
            if(confirm("确定删除此条信息？"))
            {
                location.href="${pageContext.request.contextPath}/userDelServlet?id="+id;
            }
        }

        <c:if test="${flag==true}">
            alert("删除成功！");
            window.location.href = "${pageContext.request.contextPath}/userListServlet"
        </c:if>

        function deleteSelceted() {
            var cb = document.getElementsByClassName("cb");
            var str = "";
            for(i=0;i<cb.length;i++){
                if (cb[i].checked==true){
                    str = str.concat("uid"+i+"="+cb[i].value+"&");
                }
            }
            str = str.substring(0,str.length-1);
            location.href="${pageContext.request.contextPath}/delSelectedServlet?"+str;
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>

    <div style="float: left;">

        <form class="form-inline" action="${pageContext.request.contextPath}/userListServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" class="form-control" id="exampleInputName2" name="name" value="${t_user.name}">
            </div>
            <div class="form-group">
                <label for="exampleInputName3">籍贯</label>
                <input type="text" class="form-control" id="exampleInputName3" name="address" value="${t_user.address}">
            </div>

            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="email" class="form-control" id="exampleInputEmail2" name="email" value="${t_user.email}">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>

    </div>

    <div style="float: right;margin: 5px;">

        <a class="btn btn-primary" href="add.jsp">添加联系人</a>
        <a class="btn btn-primary" href="javascript:deleteSelceted();">删除选中</a>

    </div>

    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" id="c_b"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>

        <c:forEach items="${users}" var="user" varStatus="s">
            <tr>
                <td><input type="checkbox" class="cb" value="${user.id}"></td>
                <td>${s.count}</td>
                <td>${user.name}</td>
                <td>${user.gender}</td>
                <td>${user.age}</td>
                <td>${user.address}</td>
                <td>${user.qq}</td>
                <td>${user.email}</td>
                <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}">修改</a>
                    &nbsp;<a class="btn btn-default btn-sm" href="javascript:delfunc(${user.id});">删除</a></td>
            </tr>

        </c:forEach>
    </table>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li>
                    <a href="javascript:Prefunc();" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${pus.totalPages}" var="s" step="1">
                    <c:if test="${s==pus.curpage}">
                        <li class="active"><a href="${pageContext.request.contextPath}/userListServlet?curpage=${s}&pagecount=5">${s}</a></li>
                    </c:if>
                    <c:if test="${s!=pus.curpage}">
                        <li><a href="${pageContext.request.contextPath}/userListServlet?curpage=${s}&pagecount=5">${s}</a></li>
                    </c:if>
                </c:forEach>
                <li>
                    <a href="javascript:Nextfunc();" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px;margin-left: 5px;">
                    共${pus.totalCount}条记录，共${pus.totalPages}页
                </span>

            </ul>
        </nav>


    </div>


</div>
<script>
    var c_b = document.getElementById("c_b");
    var cb = document.getElementsByClassName("cb");
    c_b.onclick=function () {
        for (i=0;i<cb.length;i++){
            cb[i].checked=c_b.checked;
        }
    }

    function Nextfunc() {
        if (${pus.curpage+1<=pus.totalPages}){
            location.href="${pageContext.request.contextPath}/userListServlet?curpage=${pus.curpage+1}&pagecount=5";
            //console.log(${pus.curpage+2});
        }
        else {
            alert("当前是最后一页了哦")
        }
    }

    function Prefunc() {
        if (${pus.curpage-1>0}){
            location.href="${pageContext.request.contextPath}/userListServlet?curpage=${pus.curpage-1}&pagecount=5";
        }
        else {
            alert("当前是第一页了哦")
        }
    }
</script>

</body>
</html>
