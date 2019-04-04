<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet"  href="resources/css/bootstrap.min.css" />
  <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-main">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/">
        <img src="resources/images/logo.png" height="36" alt="">
      </a>
      <a class="navbar-brand" href="/">
      </a>
    </div>
    <div class="collapse navbar-collapse" id="navbar-main">
      <ul class="nav navbar-nav">
        <li><a href=getlist>просмoтр сохраненных</a></li>
        <%--<li><a href=getlistRejTable>REJ-TABLE</a></li>--%>
        <%--<li><a href=generate-xml-from-rejtable-done-is-null>Новые в REJTABLE</a></li>--%>
       <%-- <li><a href=okhttp>без параметра</a></li>--%>
        <%--<li><a href=okhttp2>без параметра</a></li>--%>
      </ul>
    </div>
  </div>
  <div class="col-sm-3 col-md-3 pull-left">
    <form class="navbar-form" role="search" method="get" action="">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="поиск" name="" id="srch-term">
        <div class="input-group-btn">
          <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
        </div>
      </div>
    </form>
  </div>
</nav>

</body>
</html>