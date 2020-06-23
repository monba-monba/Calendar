package Action;

import java.util.Calendar;
import java.util.List;

/**
 * 概要：index.htmlに入力された年数及び月初めから、カレンダーの日にちを取得する。
 *
 *
 *@author 門林 宙司
 */

public class MakeCalendarDay {
	int month = 0;

	public void MakeCalendarDay(int select,int year,List<int[]> daylist) {
		if(select==0) {
			for(month=2;month<16;month++){
				if(month<12) {
				int[] calendarDay = new int[42];
				makeCal(year,month,calendarDay);
				daylist.add(calendarDay);
			}else if(12<=month) {
					int[] calendarDay = new int[42];
					makeCal(year+1,month-12,calendarDay);
					daylist.add(calendarDay);

				}
			}
		}else if(select==1) {
			for(month=0;month<14;month++){
				if(month==0) {
					int[] calendarDay = new int[42];
					makeCal(year-1,month+11,calendarDay);
					daylist.add(calendarDay);
				}
				else if(0<month && month<13) {
					int[] calendarDay = new int[42];
					makeCal(year,month-1,calendarDay);
					daylist.add(calendarDay);
				}
				else if(month==13) {
					int[] calendarDay = new int[42];
					makeCal(year+1,month-13,calendarDay);
					daylist.add(calendarDay);
				}
			}
		}
	}

	public void makeCal(int year,int month,int[] calendarDay) {

		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DATE);

		/* 今月が何曜日から開始されているか確認する */
	    calendar.set(year, month, 1);
	    int startWeek = calendar.get(Calendar.DAY_OF_WEEK);
	    /* 先月が何日までだったかを確認する */
	    calendar.set(year, month, 0);
	    int beforeMonthlastDay = calendar.get(Calendar.DATE);
	    /* 今月が何日までかを確認する */
	    calendar.set(year, month + 1, 0);
	    int thisMonthlastDay = calendar.get(Calendar.DATE);

	    int count = 0;
	    /* 先月分の日付を格納する */
	    for (int i = startWeek - 2 ; i >= 0 ; i--){
	      calendarDay[count++] = beforeMonthlastDay - i;
	    }

	    /* 今月分の日付を格納する */
	    for (int i = 1 ; i <= thisMonthlastDay ; i++){
	      calendarDay[count++] = i;
	    }

	    /* 翌月分の日付を格納する */
	    int nextMonthDay = 1;
	    while (count <calendarDay.length){
	      calendarDay[count++] = nextMonthDay++;

	    }
	}
}
