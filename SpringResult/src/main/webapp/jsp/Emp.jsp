<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<c:set var="path" value="${pageContext.request.contextPath}"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Java Web Demo</title>
		<link rel="stylesheet" href="${path}/css/demo.css" type="text/css"/>
	</head>
	<body>
		<div style="text-align:center">
			<form class="form" action="partEmp.do" method="post">
				<label>小于</label><input name="sal" type="text">
				<input type="submit" value="查询">
				<input type="button" value="生成文件" onclick="down()">
			</form>
		</div>
		<table width="80%" align="center" class="hovertable">
			<tr>
				<th>编号</th>
				<th>姓名</th>
				<th>工作</th>
				<th>上司</th>
				<th>入职时间</th>
				<th>薪水</th>
				<th>奖金</th>
				<th>部门编号</th>
			</tr>
			<c:forEach items="${list}" var="bean">
				<tr>
					<td onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
						${bean.empno}
					</td>
					<td onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
						${bean.ename}
					</td>
					<td onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
						${bean.job}
					</td>
					<td onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
						${bean.mgr}
					</td>
					<td onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
						${bean.hiredate}
					</td>
					<td onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
						${bean.sal}
					</td>
					<td onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
						${bean.comm}
					</td>
					<td onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
						${bean.deptno}
					</td>
				</tr>
			</c:forEach>
		</table>
		<c:forEach items="${files}" var="bean">
			<a href="download.do?filename=${bean}">${bean}</a><br>
		</c:forEach>
	</body>
	<script type="text/javascript">
    function down(){
        var url="/SpringResult/jsp/downEmp.do"
        window.location.href = url;
    }
	</script>
</html>