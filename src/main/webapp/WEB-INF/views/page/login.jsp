<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="header.jsp" %>
<link href="/static/assets/css/login-style.css" rel="stylesheet" type="text/css"/>
<body ondragstart="return false;" ondrop="return false;">
<div class="login-container">
    <div class="login-layer">
        <div class="right">
            <h5>로그인</h5>
            <form action="login" name="login" class="form-login-wrap" role="login" method="POST">
                <fieldset>
                    <p>
                        <input type="text" class="form-control" name="username" id="username" placeholder="아이디"/>
                    </p>
                    <p>
                        <input type="password" class="form-control" name="password" id="password" placeholder="비밀번호"/>
                    </p>
                    <c:if test="${!empty SPRING_SECURITY_LAST_EXCEPTION}">
                        <p class="error-msg">비밀번호가 잘못 되었습니다.</p>
                        <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
                    </c:if>
<%--                    <p>--%>
<%--                        <label>--%>
<%--                            <input type="checkbox"/>--%>
<%--                            <i></i> 아이디 저장--%>
<%--                        </label>--%>
<%--                        <a href="#">아이디 / 비밀번호 찾기</a>--%>
<%--                    </p>--%>
                    <p>
                        <button class="btn btn-primary" type="submit">로그인</button>
                    </p>
                </fieldset>
            </form>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
