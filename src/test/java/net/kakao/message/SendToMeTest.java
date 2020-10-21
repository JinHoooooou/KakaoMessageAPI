package net.kakao.message;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SendToMeTest {

  @Test
  public void testWhenSuccessToSendMessageToMeShouldReturnOK() {
    String actual = new SendToMe().sendMessageToMe("test text");
    assertThat(actual, is("OK"));
  }

}