package net.constants;

import java.util.Calendar;

public interface Constants {

  String KAKAO_OAUTH_URL = "https://kauth.kakao.com/oauth/token";
  String KAKAO_SEND_TO_ME_URL = "https://kapi.kakao.com/v2/api/talk/memo/default/send";

  String NAVER_SPORTS_MAIN_URL = "https://sports.news.naver.com/index.nhn";
  String NAVER_KBO_SCHEDULE_URL = "https://sports.news.naver.com/kbaseball/schedule/index.nhn";
  String NAVER_EPL_SCHEDULE_URL = "https://sports.news.naver.com/wfootball/schedule/index.nhn";
  String KBO_SCHEDULES_URL = "https://search.naver.com/search.naver?ie=UTF-8&query=kbo%EC%9D%BC%EC%A0%95&sm=chr_hty";
  String EPL_SCHEDULES_URL = "https://search.naver.com/search.naver?ie=UTF-8&query=epl%EC%9D%BC%EC%A0%95&sm=chr_hty";
  String[] SCHEDULE_URLS = {KBO_SCHEDULES_URL, EPL_SCHEDULES_URL};

  String[] WEEK_DAY = {"일", "월", "화", "수", "목", "금", "토"};
  Calendar cal = Calendar.getInstance();
  int TODAY_YEAR = cal.get(Calendar.YEAR);
  int TODAY_MONTH = cal.get(Calendar.MONTH) + 1;
  int TODAY_DATE = cal.get(Calendar.DATE);
  int HOUR = cal.get(Calendar.HOUR);
  String TODAY_WEEK_OF_DAY = WEEK_DAY[cal.get(Calendar.DAY_OF_WEEK) - 1];
  String TODAY_FORMAT = String.format("%s년 %s월 %s일 (%s) %s시 \n\n",
      TODAY_YEAR, TODAY_MONTH, TODAY_DATE, TODAY_WEEK_OF_DAY, HOUR);
}
