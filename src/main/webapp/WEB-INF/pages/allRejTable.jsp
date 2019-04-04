<%@ page import="java.util.List" %>
<%@ page import="com.springapp.mvc.models.PmtInf" %>
<%@ page import="com.springapp.mvc.models.RejTable" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Отправить XML</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet"  href="resources/css/mycss/list.css" />
      <link rel="stylesheet"  href="resources/css/bootstrap.css" />
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
</head>
<body class="subpage">
<jsp:include page="headers/header.jsp"/>
<%
    List<RejTable> list = (List<RejTable>) pageContext.getRequest().getAttribute("rejTables");

%>
<div class="container">
        <TABLE class="table table-striped">
            <TR>
                <TH>ID</TH>
                <TH>REJ_CODE</TH>
                <TH>done</TH>
                <TH style="color: #1b6d85">Действие</TH>
            </TR>
            <%
                if(list!=null&&list.size()!=0){
                    for(int i =0;i<list.size();i++){
                        RejTable rejTable = list.get(i);
                        out.print("<TR>");
                        out.print("<TD>"+rejTable.getR_id()+"</TD>");
                        out.print("<TD>"+rejTable.getRej_code()+"</TD>");
                        out.print("<TD>"+rejTable.getDone()+"</TD>");
                        out.print("<TD><a href=generate-xml?r_id="+rejTable.getR_id()+" >Сгенерить XML</a></TD>");
                        out.print("</TR>");
                    }
                }

            %>
        </TABLE>
</div>


</body>
</html>
