<!DOCTYPE html>
<html>
<head>
<script src="/xcl/js/jquery-1.7.2.min.js"></script>
</head>
<body>

<div style="margin:0 auto;width:50%;height:100%;" id="ebkCertContentDiv">
 <table class="mt20">
   <tr style="height:60px" id="showFlg">
    <td><input type="button" id="downLoadPdf" value="下载PDF" class="btn btn-small w5"/> </td>
    <td align="right"><input type="button" id="print" value="打印" class="btn btn-small w5"/></td>
    <td>&nbsp;</td>
   </tr>
   <tr>
    <td colspan="3" >${content!''}</td>
   </tr>
 </table>
 </div>
 <script>
 	$("#downLoadPdf").click(function(){
		 window.open("/xcl/pdf/downloadPdf.do");
	 });
 </script>
</body>
</html>