package Action;

import java.util.List;

/**
 * 概要：会社の休日をListに埋めていく。
 * 数値99が割り当てられた日は、JSPにおいて赤色で表示される。
 *
 *
 *@author 門林 宙司
 */

public class CompanyHoliday {

	int month = 0;
	int[] ThisMonthDay = new int[42];
	int[] ThisMonthHoliday = new int[42];

	public void CompanyHoliday(int select,int year,List<int[]> daylist,List<int[]> holidaylist) {
		if(select==0) {
			for(month=3;month<15;month++) {
				if(month<12) {
					ThisMonthDay = daylist.get(month-2);
					ThisMonthHoliday = holidaylist.get(month-2);
					MakeCompanyHoliday(year,month,ThisMonthDay,ThisMonthHoliday);
				}else if(12<=month) {
					ThisMonthDay = daylist.get(month-2);
					ThisMonthHoliday = holidaylist.get(month-2);
					MakeCompanyHoliday(year+1,month-12,ThisMonthDay,ThisMonthHoliday);
				}
			}
		}
		if(select==1) {
			for(month=1;month<13;month++) {
				ThisMonthDay = daylist.get(month);
				ThisMonthHoliday = holidaylist.get(month);
				MakeCompanyHoliday(year,month-1,ThisMonthDay,ThisMonthHoliday);
			}
		}
	}

	public void MakeCompanyHoliday(int year,int month,int[] ThisMonthDay,int []ThisMonthHoliday) {

		switch(month) {
		case 0:
			if(1934<year) {
				for(int i=0;i<ThisMonthDay.length;i++) {
					if(ThisMonthDay[i] == 1 || ThisMonthDay[i] == 2 || ThisMonthDay[i] == 3) {
						ThisMonthHoliday[i]=99;  //年末年始(会社の休み)
						if(ThisMonthDay[i] == 3) {
						break;
						}
					}
				}
			}
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			if(1934<year) {
				for(int i=0;i<ThisMonthDay.length;i++) {
					if(ThisMonthDay[i] == 1) {
						ThisMonthHoliday[i]=99;  //メーデー(会社の休み)
						break;
					}
				}
			}
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			break;
		case 9:
			break;
		case 10:
			break;
		case 11:
			if(1934<year) {
				for(int i=29;i<ThisMonthDay.length;i++) {
					if(ThisMonthDay[i] == 30 || ThisMonthDay[i] == 31) {
						ThisMonthHoliday[i]=99;  //年末年始(会社の休み)
						if(ThisMonthDay[i] == 31) {
						break;
						}
					}
				}
			}
			break;
		}
	}
}
