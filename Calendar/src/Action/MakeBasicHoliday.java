package Action;

import java.util.List;

/**
 * 概要：サーブレットから渡された数値をもとに祝日を格納するListの中身を埋めていく。
 *		内容はMakeBasicHoliday2と同じ。
 *		行数は長いが処理が多少MakeBasicHoliday2より早い
 *		数値99が割り当てられた日は、JSPにおいて赤色で表示される。
 *
 *
 *@author 門林 宙司
 */

public class MakeBasicHoliday {

	int month = 0;
	int[] BeforeMonthDay = new int[42];
	int[] ThisMonthDay = new int[42];
	int[] BeforeMonthHoliday = new int[42];
	int[] ThisMonthHoliday = new int[42];
	int[] AfterMonthHoliday = new int[42];

	public void MakeHoliday(int select,int year,List<int[]> daylist,List<int[]> holidaylist) {

		int[] calendarDay = new int[42];


		if(select==0) {
			for(month=2;month<16;month++) {
				if(month<12) {
					calendarDay = daylist.get(month-2);
					int[] disHoliday = new int[42];
					disHoliday(year,month,calendarDay,disHoliday);
					holidaylist.add(disHoliday);
				}else if(12<=month) {
					calendarDay = daylist.get(month-2);
					int[] calendarHoliday = new int[42];
					disHoliday(year+1,month-12,calendarDay,calendarHoliday);
					holidaylist.add(calendarHoliday);
				}
			}
			for(month=3;month<15;month++) {
					BeforeMonthDay = daylist.get(month-3);
					BeforeMonthHoliday = holidaylist.get(month-3);
					ThisMonthDay = daylist.get(month-2);
					ThisMonthHoliday = holidaylist.get(month-2);
					AfterMonthHoliday = holidaylist.get(month-1);
					NationalHolidays(BeforeMonthDay,ThisMonthDay,BeforeMonthHoliday,ThisMonthHoliday,AfterMonthHoliday);
					TransferHoliday(BeforeMonthDay,ThisMonthDay,BeforeMonthHoliday,ThisMonthHoliday);
			}
		}

		if(select==1) {

			for(month=0;month<14;month++) {
				if(month==0) {
					calendarDay = daylist.get(month);
					int[] disHoliday = new int[42];
					disHoliday(year-1,month+11,calendarDay,disHoliday);
					holidaylist.add(disHoliday);
				}
				else if(0<month && month<13) {
					calendarDay = daylist.get(month);
					int[] disHoliday = new int[42];
					disHoliday(year,month-1,calendarDay,disHoliday);
					holidaylist.add(disHoliday);
				}
				else if(month==13) {
					calendarDay = daylist.get(month);
					int[] disHoliday = new int[42];
					disHoliday(year+1,month-13,calendarDay,disHoliday);
					holidaylist.add(disHoliday);
				}
			}


			for(month=1;month<13;month++) {
				BeforeMonthDay = daylist.get(month-1);
				BeforeMonthHoliday = holidaylist.get(month-1);
				ThisMonthDay = daylist.get(month);
				ThisMonthHoliday = holidaylist.get(month);
				AfterMonthHoliday = holidaylist.get(month+1);
				NationalHolidays(BeforeMonthDay,ThisMonthDay,BeforeMonthHoliday,ThisMonthHoliday,AfterMonthHoliday);
				TransferHoliday(BeforeMonthDay,ThisMonthDay,BeforeMonthHoliday,ThisMonthHoliday);

			}
		}
	}

	public void disHoliday(int year,int month,int[] calendarDay,int[] disHoliday) {

		switch(month) {
		case 0:

			if(1947<year) {
				for(int i=0;i<calendarDay.length;i++) {
					if(calendarDay[i] == 1) {
						disHoliday[i]=99;  //元日
						break;
					}
				}
			}
			if(1947<year && year<2000) {
				for(int i=14;i<calendarDay.length;i++) {
					if(calendarDay[i] == 15) {
						disHoliday[i]=99;  //2000年より前の成人の日
						break;
					}
				}
			}
			if(2000<=year) {
				if(calendarDay[0]==1 || calendarDay[1]==1) {
					disHoliday[8]=99;   //2000年以降の成人の日
				}else {
					disHoliday[15]=99;  //2000年以降の成人の日
				}
			}


			break;
		case 1:

			if(year==1989) {
				for(int i=23;i<calendarDay.length;i++) {
					if(calendarDay[i] == 24) {
						disHoliday[i]=99;  //昭和天皇の大喪の礼
						break;
					}
				}
			}
			if(1966<year) {
				for(int i=10;i<calendarDay.length;i++) {
					if(calendarDay[i] == 11) {
						disHoliday[i]=99;  //憲法記念日
						break;
					}
				}
			}
			if(2019<year) {
				for(int i=22;i<calendarDay.length;i++) {
					if(calendarDay[i] == 23) {
						disHoliday[i]=99;  //天皇誕生日
						break;
					}
				}
			}

			break;
		case 2:

			if(1947<year) {
				int cal = 99;
				if(year<1900 && year<1980) {
					double date = 20.8357d + 0.242194d*(year-1900) - Math.floor((year-1900)/4.0d);
					cal = (int)Math.floor(date);
					if(cal==20) {
						for(int i=19;i<calendarDay.length;i++) {
							if(calendarDay[i] == 20) {
								disHoliday[i]=99;  //1900年から1979年までの春分の日
								break;
							}
						}
					}else if(cal==21) {
						for(int i=20;i<calendarDay.length;i++) {
							if(calendarDay[i] == 21) {
								disHoliday[i]=99;  //1900年から1979年までの春分の日
								break;
							}
						}
					}
				}else if(1980<=year && year<2100) {
					double date = 20.8431d + 0.242194d*(year-1980) - Math.floor((year-1980)/4.0d);
					cal = (int)Math.floor(date);
					if(cal==20) {
						for(int i=19;i<calendarDay.length;i++) {
							if(calendarDay[i] == 20) {
								disHoliday[i]=99;  //1980年から2099年までの春分の日
								break;
							}
						}
					}else if(cal==21) {
						for(int i=20;i<calendarDay.length;i++) {
							if(calendarDay[i] == 21) {
								disHoliday[i]=99;  //1980年から2099年までの春分の日
								break;
							}
						}
					}
				}else if(2100<=year && year<=2150) {
					double date = 21.8510d + 0.242194d*(year-2100) - Math.floor((year-2100)/4.0d);
					cal = (int)Math.floor(date);
					if(cal==20) {
						for(int i=19;i<calendarDay.length;i++) {
							if(calendarDay[i] == 20) {
								disHoliday[i]=99;  //2100年から2150年までの春分の日
								break;
							}
						}
					}else if(cal==21) {
						for(int i=20;i<calendarDay.length;i++) {
							if(calendarDay[i] == 21) {
								disHoliday[i]=99;  //2100年から2150年までの春分の日
								break;
							}
						}
					}
				}else {
					cal = year % 4;
					if(cal==0 || cal==1) {
						for(int i=19;i<calendarDay.length;i++) {
							if(calendarDay[i] == 20) {
								disHoliday[i]=99;  //春分の日
								break;
							}
						}
					}else if(cal==2 || cal==3) {
						for(int i=20;i<calendarDay.length;i++) {
							if(calendarDay[i] == 21) {
								disHoliday[i]=99;  //春分の日
								break;
							}
						}
					}
				}
			}
			break;
		case 3:

			if(year==1959) {
				for(int i=9;i<calendarDay.length;i++) {
					if(calendarDay[i] == 10) {
						disHoliday[i]=99;  //皇太子・明仁親王の結婚の儀
						break;
					}
				}
			}

			if(1947<year) {
				for(int i=28;i<calendarDay.length;i++) {
					if(calendarDay[i] == 29) {
						disHoliday[i]=99;  //昭和の日
						break;
					}
				}
			}

			break;
		case 4:

			if(2019==year) {
				for(int i=0;i<calendarDay.length;i++) {
					if(calendarDay[i] == 1) {
						disHoliday[i]=99;  //天皇即位
						break;
					}
				}
			}
			if(1947<year) {
				for(int i=2;i<calendarDay.length;i++) {
					if(calendarDay[i] == 3) {
						disHoliday[i]=99;  //憲法記念日
						break;
					}
				}
			}
			if(2007<year) {
				for(int i=3;i<calendarDay.length;i++) {
					if(calendarDay[i] == 4) {
						disHoliday[i]=99;  //みどりの日
						break;
					}
				}
			}
			if(1947<year) {
				for(int i=4;i<calendarDay.length;i++) {
					if(calendarDay[i] == 5) {
						disHoliday[i]=99;  //こどもの日
						break;
					}
				}
			}

			break;
		case 5:
			if(1993==year) {
				for(int i=8;i<calendarDay.length;i++) {
					if(calendarDay[i] == 9) {
						disHoliday[i]=99;  //皇太子・徳仁親王の結婚の儀
						break;
					}
				}
			}

			break;
		case 6:

			if(1995<year && year<2003) {
				for(int i=19;i<calendarDay.length;i++) {
					if(calendarDay[i] == 20) {
						disHoliday[i]=99;  //海の日
						break;
					}
				}
			}
			if(2003<=year && year!=2020) {
				if(calendarDay[0]==1 || calendarDay[1]==1) {
					disHoliday[15]=99;   //2003年以降の海の日
				}else {
					disHoliday[22]=99;   //2003年以降の海の日
				}
			}
			if(year==2020) {
				for(int i=22;i<calendarDay.length;i++) {
					if(calendarDay[i] == 23) {
						disHoliday[i]=99;  //2020年の海の日
						break;
					}
				}
			}
			if(year==2020) {
				for(int i=23;i<calendarDay.length;i++) {
					if(calendarDay[i] == 24) {
						disHoliday[i]=99;  //2020年のスポーツの日
						break;
					}
				}
			}

			break;
		case 7:
			if(2015<year && year!=2020) {
				for(int i=10;i<calendarDay.length;i++) {
					if(calendarDay[i] == 11) {
						disHoliday[i]=99;  //山の日
						break;
					}
				}
			}
			if(year==2020) {
				for(int i=10;i<calendarDay.length;i++) {
					if(calendarDay[i] == 10) {
						disHoliday[i]=99;  //2020年の山の日
						break;
					}
				}
			}
			break;
		case 8:
			if(1995<year && year<2003) {
				for(int i=14;i<calendarDay.length;i++) {
					if(calendarDay[i] == 15) {
						disHoliday[i]=99;  //敬老の日
						break;
					}
				}
			}
			if(2003<=year) {
				if(calendarDay[0]==1 || calendarDay[1]==1) {
					disHoliday[15]=99;   //2003年以降の敬老の日
				}else {
					disHoliday[22]=99;   //2003年以降の敬老の日
				}
			}
			if(1947<year) {
				int cal = 99;
				if(year<1900 && year<1980) {
					double date = 23.2588d + 0.242194d*(year-1900) - Math.floor((year-1900)/4.0d);
					cal = (int)Math.floor(date);
					if(cal==22) {
						for(int i=21;i<calendarDay.length;i++) {
							if(calendarDay[i] == 22) {
								disHoliday[i]=99;  //1900年から1979年の秋分の日
								break;
							}
						}
					}else if(cal==23) {
						for(int i=22;i<calendarDay.length;i++) {
							if(calendarDay[i] == 23) {
								disHoliday[i]=99;  //1900年から1979年の秋分の日
								break;
							}
						}
					}
				}else if(1980<=year && year<2100) {
					double date = 23.2488d + 0.242194d*(year-1980) - Math.floor((year-1980)/4.0d);
					cal = (int)Math.floor(date);
					if(cal==22) {
						for(int i=21;i<calendarDay.length;i++) {
							if(calendarDay[i] == 22) {
								disHoliday[i]=99;  //1980年から2099年の秋分の日
								break;
							}
						}
					}else if(cal==23) {
						for(int i=22;i<calendarDay.length;i++) {
							if(calendarDay[i] == 23) {
								disHoliday[i]=99;  //1980年から2099年の秋分の日
								break;
							}
						}
					}
				}else if(2100<=year && year<=2150) {
					double date = 24.2488d + 0.242194d*(year-2100) - Math.floor((year-2100)/4.0d);
					cal = (int)Math.floor(date);
					if(cal==22) {
						for(int i=21;i<calendarDay.length;i++) {
							if(calendarDay[i] == 22) {
								disHoliday[i]=99;  //2100年から2150年の秋分の日
								break;
							}
						}
					}else if(cal==23) {
						for(int i=22;i<calendarDay.length;i++) {
							if(calendarDay[i] == 23) {
								disHoliday[i]=99;  //2100年から2150年の秋分の日
								break;
							}
						}
					}
				}else {
					cal = year % 4;
					if(cal==0 || cal==1) {
						for(int i=21;i<calendarDay.length;i++) {
							if(calendarDay[i] == 22) {
								disHoliday[i]=99;  //上記以外の年の秋分の日
								break;
							}
						}
					}else if(cal==2 || cal==3) {
						for(int i=22;i<calendarDay.length;i++) {
							if(calendarDay[i] == 23) {
								disHoliday[i]=99;  //上記以外の年の秋分の日
								break;
							}
						}
					}
				}
			}

			break;
		case 9:

			if(1965<year && year<2000) {
				for(int i=9;i<calendarDay.length;i++) {
					if(calendarDay[i] == 10) {
						disHoliday[i]=99;  //体育の日
						break;
					}
				}
			}
			if(2000<=year && year!=2020) {
				if(calendarDay[0]==1 || calendarDay[1]==1) {
					disHoliday[8]=99;   //2000年以降の体育の日・スポーツの日
				}else {
					disHoliday[15]=99;   //2000年以降の体育の日・スポーツの日
				}
			}
			if(year == 2019) {
				for(int i=21;i<calendarDay.length;i++) {
					if(calendarDay[i] == 22) {
						disHoliday[i]=99;  //即位礼正殿の儀の行われた日
						break;
					}
				}
			}

			break;
		case 10:

			if(year==1990) {
				for(int i=11;i<calendarDay.length;i++) {
					if(calendarDay[i] == 12) {
						disHoliday[i]=99;  //即位礼正殿の儀
						break;
					}
				}
			}
			if(1947<year) {
				for(int i=2;i<calendarDay.length;i++) {
					if(calendarDay[i] == 3) {
						disHoliday[i]=99;  //文化の日
						break;
					}
				}
			}
			if(1947<year) {
				for(int i=22;i<calendarDay.length;i++) {
					if(calendarDay[i] == 23) {
						disHoliday[i]=99;  //勤労感謝の日
						break;
					}
				}
			}

			break;
		case 11:

			if(1989<year && year<2019) {
				for(int i=22;i<calendarDay.length;i++) {
					if(calendarDay[i] == 23) {
						disHoliday[i]=99;  //天皇誕生日
						break;
					}
				}
			}
			break;
		}
	}

	public void NationalHolidays(int[] BeforeMonthDay,int[]ThisMonthDay,int[] BeforeMonthHoliday,int[] ThisMonthHoliday,int[] AfterMonthHoliday) {

		for(int i=1;i<4;i++) {
			if(ThisMonthHoliday[i]==99 && ThisMonthHoliday[i+2]==99) {
				ThisMonthHoliday[i+1]=99;
			}
			if(ThisMonthHoliday[i+7]==99 && ThisMonthHoliday[i+9]==99) {
				ThisMonthHoliday[i+8]=99;
			}
			if(ThisMonthHoliday[i+14]==99 && ThisMonthHoliday[i+16]==99) {
				ThisMonthHoliday[i+8]=99;
			}
			if(ThisMonthHoliday[i+21]==99 && ThisMonthHoliday[i+23]==99) {
				ThisMonthHoliday[i+22]=99;
			}
			if(ThisMonthHoliday[i+28]==99 && ThisMonthHoliday[i+30]==99) {
				ThisMonthHoliday[i+29]=99;
			}
		}

		if(BeforeMonthDay[34]<14) {
			for(int i=3;i<7;i++) {
				if(BeforeMonthHoliday[i+26]==99 && ThisMonthHoliday[i]==99 ) {
					ThisMonthHoliday[i-1]=99;
				}
			}
		}else {
			for(int i=3;i<4;i++) {
				if(BeforeMonthHoliday[i+33]==99 && ThisMonthHoliday[i]==99 ) {
					ThisMonthHoliday[i-1]=99;
				}
			}
		}

		if(ThisMonthDay[34]<14) {
			for(int i=29;i<32;i++) {
				if(ThisMonthHoliday[i]==99 && AfterMonthHoliday[i-26]==99 ) {
					ThisMonthHoliday[i+1]=99;
				}
			}
		}
	}

	public void TransferHoliday(int[] BeforeMonthDay,int[] ThisMonthDay,int[] BeforeMonthHoliday,int[] ThisMonthHoliday) {

		if(BeforeMonthHoliday[28]==99 && 27<BeforeMonthDay[28] && ThisMonthDay[1]==1) {
			for(int i=1;i<6;i++) {
				if(ThisMonthHoliday[i]==0) {
					ThisMonthHoliday[i]=99;
					break;
				}
			}
		}
		if(BeforeMonthHoliday[35]==99 && 27<BeforeMonthDay[35] && ThisMonthDay[1]==1) {
			for(int i=1;i<6;i++) {
				if(ThisMonthHoliday[i]==0) {
					ThisMonthHoliday[i]=99;
					break;
				}
			}
		}
		if(ThisMonthHoliday[0]==99) {
			for(int i=1;i<6;i++) {
				if(ThisMonthHoliday[i]==0) {
					ThisMonthHoliday[i]=99;
					break;
				}
			}
		}else if(ThisMonthHoliday[7]==99) {
			for(int i=8;i<13;i++) {
				if(ThisMonthHoliday[i]==0) {
					ThisMonthHoliday[i]=99;
					break;
				}
			}
		}else if(ThisMonthHoliday[14]==99) {
			for(int i=15;i<20;i++) {
				if(ThisMonthHoliday[i]==0) {
					ThisMonthHoliday[i]=99;
					break;
				}
			}
		}else if(ThisMonthHoliday[21]==99) {
			for(int i=22;i<27;i++) {
				if(ThisMonthHoliday[i]==0) {
					ThisMonthHoliday[i]=99;
					break;
				}
			}
		}else if(ThisMonthHoliday[28]==99) {
			for(int i=29;i<34;i++) {
				if(ThisMonthHoliday[i]==0) {
					ThisMonthHoliday[i]=99;
					break;
				}
			}
		}else if(ThisMonthHoliday[35]==99) {
			for(int i=36;i<41;i++) {
				if(ThisMonthHoliday[i]==0) {
					ThisMonthHoliday[i]=99;
					break;
				}
			}
		}
	}

}