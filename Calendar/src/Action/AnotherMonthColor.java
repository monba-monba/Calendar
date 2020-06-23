package Action;

import java.util.List;

/**
 * 概要：要件定義書通り、昨月と来月の色を変更する。
 * 数値88が割り当てられた日は、JSPにおいて灰色で表示される。
 *
 *
 *@author 門林 宙司
 */

public class AnotherMonthColor {

	int month =0;
	int[] ThisMonthDay = new int[42];
	int[] ThisMonthHoliday = new int[42];

	public void AnotherMonthColorChange(int select,List<int[]> daylist,List<int[]> holidaylist) {
		if(select==0) {
			for(month=3;month<15;month++) {
					ThisMonthDay = daylist.get(month-2);
					ThisMonthHoliday = holidaylist.get(month-2);
					AnotherMonthColor(ThisMonthDay,ThisMonthHoliday);
			}
		}
		if(select==1) {
			for(month=0;month<12;month++) {
				ThisMonthDay = daylist.get(month+1);
				ThisMonthHoliday = holidaylist.get(month+1);
				AnotherMonthColor(ThisMonthDay,ThisMonthHoliday);
			}
		}
	}

	public void AnotherMonthColor(int[] ThisMonthDay,int[]ThisMonthHoliday) {
		for(int i=0;i<6;i++) {
			if(23<=ThisMonthDay[i]) {
				ThisMonthHoliday[i]=88;
			}
		}
		for(int i=28;i<ThisMonthDay.length;i++) {
			if(ThisMonthDay[i]<=14) {
				ThisMonthHoliday[i]=88;
			}
		}
	}

}


