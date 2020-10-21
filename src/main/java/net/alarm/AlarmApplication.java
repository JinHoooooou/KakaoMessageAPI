package net.alarm;

import java.net.URISyntaxException;
import net.kakao.message.SendToMe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class AlarmApplication {

  public static void main(String[] args) {
    new SendToMe().sendMessageToMe("테스트입니다. 잘 되나요?");
  }

}
