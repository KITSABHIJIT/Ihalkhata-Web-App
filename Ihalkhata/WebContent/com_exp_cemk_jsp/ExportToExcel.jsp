<%@ page contentType="application/vnd.ms-excel"%><%
String title = request.getParameter("title");
if(title==null)
{
	title = "Grid_Data";
}
response.setHeader("Content-Disposition","inline; filename = "+title+".xls");
out.println(request.getParameter("data"));
out.flush();
%><%--

If data is in JSON format.

<%@page import="com.sebi.utility.Util"%>
<%@ page import="org.apache.poi.ss.usermodel.*" %>
<%@ page import="net.sf.json.*" %>
<%
Workbook workbook = new org.apache.poi.hssf.usermodel.HSSFWorkbook();
String data = request.getParameter("data");

JSONObject input = JSONObject.fromObject(data);

Object titleObj = input.get("title");
String title;
if(titleObj ==null || Util.stringNullOrEmpty(titleObj.toString()))
{
	title = "Grid Data";
}
else
{
	title = titleObj.toString();
}

Sheet mainSheet = workbook.createSheet(title);

Row headerRow = mainSheet.createRow(0);
JSONArray headers = (JSONArray)input.get("headers");
int headerColumnIndex = 0;
CellStyle headerCellStyle = workbook.createCellStyle();
Font headerFont = workbook.createFont();
headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
headerCellStyle.setFont(headerFont);
for(Object header : headers)
{
	Cell headerCell = headerRow.createCell(headerColumnIndex);
	headerCell.setCellValue(header.toString());	
	headerCell.setCellStyle(headerCellStyle);
	headerColumnIndex++;
}

JSONArray rows = (JSONArray)input.get("rows");
int rowIndex = 1;
for(Object rowObj : rows)
{
	Row row = mainSheet.createRow(rowIndex);
	JSONArray rowArr = (JSONArray)rowObj;
	int columnIndex = 0;
	for(Object cellObj : rowArr)
	{
		Cell rowCell = row.createCell(columnIndex);
		rowCell.setCellValue(cellObj.toString());
		columnIndex++;	
	}					
	rowIndex++;
}
response.setContentType("application/vnd.ms-excel");
response.setHeader("Content-Disposition", "attachment; filename=\""+ title+".xls" + "\"");
workbook.write(response.getOutputStream());
out.clearBuffer();
%>
 --%>