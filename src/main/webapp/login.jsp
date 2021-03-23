<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%--<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>--%>

<!doctype html>
<html>
<c:set var="title" value="Страница входа" scope="page"/>
<%--<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>--%>
<head>
    <link href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="https://getbootstrap.com/docs/4.0/examples/sign-in/signin.css" rel="stylesheet">
</head>
<body class="text-center">
<form class="form-signin" method="post" action="controller?action=login">
    <img class="mb-4" src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal">ISP management console</h1>
    <label for="login" class="sr-only">Email address</label>
    <input type="text" id="login" name="login" class="form-control" placeholder="Login" required autofocus>
    <label for="password" class="sr-only">Password</label>
    <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> Remember me
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-muted">Don't have account? Sign up</p>
    <p class="mt-5 mb-3 text-muted">&copy; 2021</p>
</form>

<%--<div class="container">--%>
<%--    <div class="d-flex justify-content-center">--%>
<%--        <div class="card border-primary mb-3" style="width: 50%; margin-top: 100px ">--%>
<%--            <form class="p-5" method="post" action="controller?action=login">--%>
<%--                <div class="form-group">--%>
<%--&lt;%&ndash;                    <c:if test="${not empty errorMessage}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <div class="alert alert-danger" role="alert">${errorMessage}</div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </c:if>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <label for="login"><fmt:message key="login.label.login"/></label>&ndash;%&gt;--%>
<%--                    <label for="login">Login</label>--%>
<%--                    <input type="text" class="form-control" id="login" name="login"--%>
<%--                           placeholder="login...."--%>
<%--                           value="+380" size="13" maxlength="13" required>--%>
<%--                </div>--%>
<%--                <div class="form-group">--%>
<%--                    <label for="password">Password</label>--%>
<%--                    <input type="password" class="form-control" id="password" name="password"--%>
<%--                           placeholder="pass" minlength="6"--%>
<%--                           maxlength="40" required>--%>
<%--                </div>--%>
<%--                <button type="submit" class="btn btn-primary">Do login</button>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>
</body>
</html>