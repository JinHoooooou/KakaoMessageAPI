package net.alarm;

import java.io.IOException;

import static net.constants.Constants.*;

import net.crawling.Crawling;
import net.kakao.message.SendToMe;

public class AlarmApplication {

  public static void main(String[] args) throws IOException {

    StringBuilder schedules = new StringBuilder();

    schedules.append(TODAY_FORMAT);
    for (String url : SCHEDULE_URLS) {
      schedules.append(Crawling.getSchedule(url).getString("schedules"));
    }

    new SendToMe().sendMessageToMe(schedules.toString());
  }

}
