<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: APPLE
  Date: 15/12/18
  Time: 上午11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>账户管理</title>
</head>
<body>
<div class="bjui-pageHeader">
    <form id="pagerForm" name="userForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/userManagement/query_a_user" method="post">
        <div class="bjui-searchBar">
            <label>查找账号：</label>
            <input type="text" name="username" id="username" value="${userForm.usrName}"  size="15">&nbsp;
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
            <a href="${pageContext.request.contextPath}/userManagement/addnewpage" class="btn btn-green" data-toggle="dialog" data-width="450" data-height="350" data-id="dialog-normal" data-title="新增账户信息">新增用户</a>
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table  class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed" data-width="100%" data-nowrap="true">
        <thead>
        <tr>
            <th align="center">账户ID</th>
            <th align="center">账户名</th>
            <th align="center">组标识</th>
            <th align="center">状态</th>
            <th align="center">邮箱</th>
            <th align="center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${userForm.userDoList}" varStatus="status">
            <tr data-id="<c:out value="${record.usrId}"/>">
                <td align="center"><c:out value="${record.usrId}"/></td>
                <td align="center"><c:out value="${record.usrName}"/></td>
                <td align="center"><c:out value="${record.usrRemark}"/></td>
                <td align="center"><c:out value="${record.usrDisableTag}"/></td>
                <td align="center"><c:out value="${record.usrEmail}"/></td>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/userManagement/modifyacctoauth?id=<c:out value="${record.usrId}"/>" class="btn btn-green" data-toggle="dialog" data-width="800" data-height="400" data-id="dialog-normal" data-title="账户信息">授权</a>
                    <button type="button" class="btn-green" data-toggle="dialog" data-id="change-password" data-url="${pageContext.request.contextPath}/userManagement/deleteUserByUserId?userId=<c:out value="${record.usrId}" />" >删除</button>
                        <%--<a href="${pageContext.request.contextPath}/userManagement/goToChangepwdPage?loginName=<c:out value="${record.usrName}"/>&loginId=<c:out value="${record.usrId}" />" class="btn btn-green" data-toggle="dialog" data-width="800" data-height="400" data-id="dialog-normal" data-title="账户信息">修改密码</a>--%>
                    <%--<button type="button" class="btn-green" data-toggle="dialog" data-id="change-password" data-url="${pageContext.request.contextPath}/userManagement/goToChangepwdPage?loginName=<c:out value="${record.usrName}"/>&loginId=<c:out value="${record.usrId}" />">修改密码</button>--%>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</div>
</body>
</html>
