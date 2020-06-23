<!-- @author 門林宙司、檀野 良太  -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<%!
List<int[]> daylist = new ArrayList();
List<int[]> holidaylist = new ArrayList();
int[] calendarDay = new int[42];
int[] calendarHoliday = new int[42];
int day = 0;                         /* 配列の中身を表示するときの添え字に使う。 */
int week = 0;                       /* 1ヶ月の週を表す。 */
int month = 0;                      /* 月を表す。 */
int year = 0;                         /* 年を表す。 */
%>
<%
daylist = (List)request.getAttribute("dayList");
holidaylist = (List)request.getAttribute("holidayList");
year = (int)request.getAttribute("Year");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="calendar.css">
<title><%=year %>年度カレンダー</title>
</head>
<body>
<% for(int month = 0;month<12;month++){ %>
              <%-- <% if(month==4 || month==8){ %>
              <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
              <% } %> --%>

              <% if(month==0){ %>
              <div class="first">  <!-- 1月目 -->
              <% } %><%else if(month==1){ %>
              <div class="second">  <!-- 2月目 -->
              <% } %><%else if(month==2){ %>
              <div class="third">  <!-- 3月目 -->
              <% } %><%else if(month==3){ %>
              <div class="fourth">  <!-- 4月目 -->
              <% } %><%else if(month==4){ %>
              <div class="fifth">  <!-- 5月目 -->
              <% } %><%else if(month==5){ %>
              <div class="sixth">  <!-- 6月目 -->
              <% } %><%else if(month==6){ %>
              <div class="seventh">  <!-- 7月目 -->
              <% } %><%else if(month==7){ %>
              <div class="eighth">  <!-- 8月目 -->
              <% } %><%else if(month==8){ %>
              <div class="ninth">  <!-- 9月目 -->
              <% } %><%else if(month==9){ %>
              <div class="tenth">  <!-- 10月目 -->
              <% } %><%else if(month==10){ %>
              <div class="eleventh">  <!-- 11月目 -->
              <% } %><%else if(month==11){ %>
              <div class="twelfth">  <!-- 12月目 -->
              <% } %>

              <table bgcolor="mistyrose"  width="223"  border="1" align="left">
              <% if(month<9){ %>
              <tr bgcolor="lightpink"><td colspan="7"><%=year %>年</td></tr>
              <% }else if(9<=month){ %>
              <tr bgcolor="lightpink"><td colspan="7"><%=year+1 %>年</td></tr>
              <% } %>
              <% if(month<9){ %>
              <tr bgcolor="feldsper"><td colspan="7"><%=month+4 %>月</td></tr>
              <% }else if(month==9){ %>
              <tr bgcolor="feldsper"><td colspan="7">1月</td></tr>
              <% }else if(month==10){ %>
              <tr bgcolor="feldsper"><td colspan="7">2月</td></tr>
              <% }else if(month==11){ %>
              <tr bgcolor="feldsper"><td colspan="7">3月</td></tr>
              <% } %>

              <tr bgcolor="snow"><th><div class="sunday">日</div></th><th>月</th><th>火</th><th>水</th><th>木</th><th>金</th><th><div class="saturday">土</div></th></tr>
                            <% calendarDay = daylist.get(month+1); %>
                            <% calendarHoliday = holidaylist.get(month+1); %>
                            <% for(int week=0;week<6;week++){ %>
                            <tr bgcolor="snow">
			<% if(calendarHoliday[day]==88){ %>
				<td><div class="anothermonth"><%=calendarDay[day] %></div></td>
				<% }else{ %>
			<td><div class="sunday"><%=calendarDay[day] %></div></td>
					<% } %>
			<% if(calendarHoliday[day+1]==99){ %>
				<td><div class="sunday"><%=calendarDay[day+1] %></div></td>
				<% }else if(calendarHoliday[day+1]==88){ %>
				<td><div class="anothermonth"><%=calendarDay[day+1] %></div></td>
				<% }else{ %>
				<td><%=calendarDay[day+1] %></td>
				<% } %>
			<% if(calendarHoliday[day+2]==99){ %>
				<td><div class="sunday"><%=calendarDay[day+2] %></div></td>
				<% }else if(calendarHoliday[day+2]==88){ %>
				<td><div class="anothermonth"><%=calendarDay[day+2] %></div></td>
				<% }else{ %>
				<td><%=calendarDay[day+2] %></td>
				<% } %>
			<% if(calendarHoliday[day+3]==99){ %>
				<td><div class="sunday"><%=calendarDay[day+3] %></div></td>
				<% }else if(calendarHoliday[day+3]==88){ %>
				<td><div class="anothermonth"><%=calendarDay[day+3] %></div></td>
				<% }else{ %>
				<td><%=calendarDay[day+3] %></td>
				<% } %>
			<% if(calendarHoliday[day+4]==99){ %>
				<td><div class="sunday"><%=calendarDay[day+4] %></div></td>
				<% }else if(calendarHoliday[day+4]==88){ %>
				<td><div class="anothermonth"><%=calendarDay[day+4] %></div></td>
				<% }else{ %>
				<td><%=calendarDay[day+4] %></td>
				<% } %>
			<% if(calendarHoliday[day+5]==99){ %>
				<td><div class="sunday"><%=calendarDay[day+5] %></div></td>
				<% }else if(calendarHoliday[day+5]==88){ %>
				<td><div class="anothermonth"><%=calendarDay[day+5] %></div></td>
				<% }else{ %>
				<td><%=calendarDay[day+5] %></td>
				<% } %>
			<% if(calendarHoliday[day+6]==99){ %>
				<td><div class="sunday"><%=calendarDay[day+6] %></div></td>
				<% }else if(calendarHoliday[day+6]==88){ %>
				<td><div class="anothermonth"><%=calendarDay[day+6] %></div></td>
				<% }else{ %>
				<td><div class="saturday"><%=calendarDay[day+6] %></div></td>
				<% } %>
		</tr>
                            <% day=day+7; %>
                            <% } %>
              </table>
              <% day=0; %> <!-- 数字を保持し続けないようリセットする。 -->


   </div>

<% } %>
</body>
</html>
