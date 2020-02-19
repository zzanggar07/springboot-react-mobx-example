<%@ page contentType="text/html; charset=UTF-8" %>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="content-page page-404">
                <div class="number"><%= request.getAttribute("javax.servlet.error.status_code") %></div>
                <div class="detail">
                    <h3>Oops!</h3>
                    <p> <%= request.getAttribute("javax.servlet.error.message") %></p>
                </div>
            </div>
            <div class="content-btn">
                <a href="javascript:history.back()" class="btn btn-default btn-sm">이전 페이지</a>
                <a href="/" class="btn btn-default btn-sm">홈으로 이동</a>
            </div>
        </div>
    </div><!--/.row-->
</div><!--/.container-->
