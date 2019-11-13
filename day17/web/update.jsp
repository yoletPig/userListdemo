<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        <c:if test="${flag==true}">
        alert("修改成功！");
        window.location.href = "${pageContext.request.contextPath}/userListServlet"
        </c:if>
        var back = document.getElementById("back");
        back.onclick=function () {
            window.history.back(-1);
        }
    </script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="${pageContext.request.contextPath}/updateServlet" method="post">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" readonly="readonly" placeholder="请输入姓名"
                   value="${user.name}"/>
        </div>
        <input type="hidden" name="id" value="${user.id}">
        <div class="form-group">
            <label>性别：</label>
            <c:if test="${user.gender=='男'}">
                <input type="radio" name="gender" value="男" checked/>男
                <input type="radio" name="gender" value="女"/>女
            </c:if>
            <c:if test="${user.gender!='男'}">
                <input type="radio" name="gender" value="男"/>男
                <input type="radio" name="gender" value="女" checked/>女
            </c:if>
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age" name="age" placeholder="请输入年龄" value="${user.age}"/>
        </div>

        <%
            String[] address = {"广东", "广西", "湖南", "陕西", "北京"};
            request.setAttribute("address", address);
        %>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" class="form-control">
                <c:forEach items="${address}" var="addres" varStatus="s">
                    <c:if test="${user.address ==addres}">
                        <option selected value=${address[s.count-1]}>${address[s.count-1]}</option>
                    </c:if>
                    <c:if test="${user.address!=addres}">
                        <option value=${address[s.count-1]}>${address[s.count-1]}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" class="form-control" name="qq" placeholder="请输入QQ号码" value="${user.qq}"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" name="email" placeholder="请输入邮箱地址" value="${user.email}"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" id="submit"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-default" type="button" value="返回" id="back"/>
        </div>
    </form>
</div>
</body>
</html>
