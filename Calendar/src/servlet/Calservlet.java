package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.AnotherMonthColor;
import Action.CompanyHoliday;
import Action.MakeBasicHoliday2;
import Action.MakeCalendarDay;

/**
 * 概要：サーブレット。index.htmlから数値を受け取り、各クラスを用いて処理した後、Calendar.jspまたはCalendar2.jspへ渡す。
 *		int型変数selectは月初めの分岐。4月初めは「0」1月初めは「1」。
 *
 *
 *@author 門林 宙司
 */

@WebServlet("/calender")

public class Calservlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int select = Integer.parseInt(request.getParameter("select"));
		int year = Integer.parseInt(request.getParameter("year"));

		MakeCalendarDay MCD = new MakeCalendarDay();
		List<int[]> daylist = new ArrayList();
		MCD.MakeCalendarDay(select,year,daylist);  //12か月分の日付を格納

		MakeBasicHoliday2 MBH = new MakeBasicHoliday2();
		List<int[]> holidaylist = new ArrayList();
		MBH.MakeHoliday(select,year,daylist,holidaylist);  //日付を参照して休日を格納

		AnotherMonthColor AMC = new AnotherMonthColor();
		AMC.AnotherMonthColorChange(select,daylist,holidaylist);   //他の月の色を変えるための処理

		CompanyHoliday CH = new CompanyHoliday();
		CH.CompanyHoliday(select,year,daylist,holidaylist);   //さくらコーポレーション固有の休日を格納


		request.setAttribute("dayList",daylist);
		request.setAttribute("holidayList",holidaylist);
		request.setAttribute("Year",year);

		if(select==0) {
			gotoPage(request,response,"/Calendar.jsp");
		}else if(select==1) {
			gotoPage(request,response,"/Calendar2.jsp");
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void gotoPage(HttpServletRequest request, HttpServletResponse response,String page)
		throws ServletException, IOException{

		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
}