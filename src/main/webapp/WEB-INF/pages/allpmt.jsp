<%@ page import="java.util.List" %>
<%@ page import="com.springapp.mvc.models.PmtInf" %>
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
<jsp:include page="headers/header2.jsp"/>
<%
    List<PmtInf> list = (List<PmtInf>) pageContext.getRequest().getAttribute("list");

%>
<div class="container">
        <TABLE class="table table-striped">
            <TR>
                <TH>PmtInfId</TH>
                <TH>PmtMtd</TH>
                <TH>BtchBookg</TH>
                <TH>NbOfTxs</TH>
                <TH>CtrlSum</TH>
                <TH>ChrgBr</TH>
                <TH>ReqdExctnDt</TH>
                <TH>IBAN</TH>
                <TH style="color: #1b6d85">Действие</TH>
            </TR>
            <%
                for(int i =0;i<list.size();i++){
                    PmtInf pmtInf = list.get(i);
                    out.print("<TR>");
                    out.print("<TD>"+pmtInf.getPmtInfId()+"</TD>");
                    out.print("<TD>"+pmtInf.getPmtMtd()+"</TD>");
                    out.print("<TD>"+pmtInf.isBtchBookg()+"</TD>");
                    out.print("<TD>"+pmtInf.getNbOfTxs()+"</TD>");
                    out.print("<TD>"+pmtInf.getCtrlSum()+"</TD>");
                    out.print("<TD>"+pmtInf.getChrgBr()+"</TD>");
                    out.print("<TD>"+pmtInf.getReqdExctnDt()+"</TD>");
                    out.print("<TD>"+pmtInf.getDbtrAcctIBAN()+"</TD>");
                    out.print("<TD id="+pmtInf.getPIdPrKey()+" class="+pmtInf.getPmtInfId()+" ><a data-target=#myModal role=button class=btn data-toggle=modal >Не правильный</a></TD>");
                    out.print("</TR>");
                }
            %>
        </TABLE>
</div>


<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="myModalLabel"></h3>
            </div>
            <div class="modal-body">

                <p>AC01—Неправильный номер счета</p>
                <p>AC03—Неправильный номер счета получателя;</p>
                <p>AC04—Номер счета закрыт;</p>
                <p>AC06—Счет заблокирован;</p>
                <p>RC01—Неверный банк получателя</p>
                <p>RC10— Неверный идентификатор (БИК) в системе клиринга</p>
                <p>RR04--  Причины регуляторного характера;</p>
                <select id="selectStatusPmt">
                    <option>AC01</option>
                    <option>AC03</option>
                    <option>AC04</option>
                    <option>AC06</option>
                    <option>RC01</option>
                    <option>RC10</option>
                    <option>RR04</option>
                </select>
                <h3 id="parId" style="display: none"></h3>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Отмена</button>
                <button class="btn btn-primary">OK</button>
            </div>
        </div>
    </div>
    <script type="application/javascript">
        $('a[role=button]').click(function(){
            var parId = $(this).parent().attr('id');
            var parIClass = $(this).parent().attr('class');
            $('#myModalLabel').html(parIClass);
            $('#parId').html(parId);

        });
        $('.btn-primary').click(function(){
            var statusPmt = $('#selectStatusPmt').val();
            window.location.href = "generate?pmtId="+$('#parId').text()+"&statusPmt="+statusPmt;
        });
    </script>
</div>
</body>
</html>
