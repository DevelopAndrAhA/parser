<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Отправить XML</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
      <link rel="stylesheet"  href="resources/css/mycss/xmlupload.css" />
      <link rel="stylesheet"  href="resources/css/bootstrap.css" />
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
</head>
<body class="subpage">
<jsp:include page="headers/header.jsp"/>
<%
String CtrlSumGrp = (String) pageContext.getRequest().getAttribute("CtrlSumGrp");
String CtrlSumPmt = (String) pageContext.getRequest().getAttribute("CtrlSumPmt");
String fileSave = (String) pageContext.getRequest().getAttribute("fileSave");
    if(fileSave.equals("success")){
        out.print("<script type='application/javascript'>alert('Файл успешно сохранен');</script>");
    }else if(fileSave.equals("error")){
        out.print("<script type='application/javascript'>alert('Ошибка сохранения');</script>");
    }else if(fileSave.equals("removed")){
        out.print("<script type='application/javascript'>alert('Последняя запись отменена');</script>");
    }
%>
<div class="container">
    <div class="row">
        <form action="upload-xml" method="post" enctype="multipart/form-data">
            <div>
                <h6>XML</h6>
                <input type="file" id="file" name="file"accept="text/xml"/>
            </div>
            <input value="Сохранить" class="btn btn-primary btn-sm" type="submit" />
            <button type="button" class="btn btn-secondary btn-sm">Отмена последнего</button>
        </form>
        <h1><span>Общ сумма : </span><%if(CtrlSumGrp!=null&&!CtrlSumGrp.isEmpty())out.print(CtrlSumGrp);%></h1>
        <h1><span>Общ.сумма каждого платежа : </span><%if(CtrlSumPmt!=null&&!CtrlSumPmt.isEmpty())out.print(CtrlSumPmt);%></h1>
    </div>
</div>
<script type="application/javascript">
    $('.btn-secondary').click(function(){
        window.location.href = "remove-data";
    });
</script>
</body>
</html>
