<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.springapp.mvc.models4testokhttp.MyOrder" %>
<%@ page import="com.springapp.mvc.models4testokhttp.Courier2" %>
<%@ page import="com.springapp.mvc.models4testokhttp.Products" %>
<%@ page import="java.util.List" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Запрос на внешний ресурс</title>
    <title>Отправить XML</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet"  href="resources/css/mycss/list.css" />
    <link rel="stylesheet"  href="resources/css/bootstrap.css" />
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
</head>
<body>
<ul class="list-group">
      <%
        Gson gson = new Gson();
        String str = (String)pageContext.getRequest().getAttribute("gson");
        MyOrder myOrder = gson.fromJson(str, MyOrder.class);
        Courier2 courier2 = myOrder.getCourier2();
        List<Products> productses = myOrder.getProductses();

        out.println("<li class=list-group-item>"+myOrder.getDate()+"</li>");
        out.println("<li class=list-group-item>"+courier2.getAddress()+"</li>");
        out.println("<li class=list-group-item>"+courier2.getName()+"</li>");
        out.println("<li class=list-group-item>"+courier2.getLogin()+"</li>");
        out.println("<li class=list-group-item>"+courier2.getPassword()+"</li>");
        out.println("<li class=list-group-item>"+courier2.getPhone()+"</li>");
      %>
</ul>
<h1 class="col-lg-12" style="text-align: center">Продукты</h1>
      <div class="container">
        <TABLE class="table table-striped">
          <TR>
            <TH>вес</TH>
            <TH>цена</TH>
            <TH>количество</TH>
          </TR>
      <%
        for(int i =0;i<productses.size();i++){
          Products products = productses.get(i);
          out.print("<TR>");
            out.print("<TD>"+products.getWeight()+"</TD>");
            out.print("<TD>"+products.getName()+"</TD>");
            out.print("<TD>"+products.getAmount()+"</TD>");
          out.print("</TR>");
        }
      %>
</body>
</html>
