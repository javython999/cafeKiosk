package study.cafekiosk.spring.api.service.mail;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import study.cafekiosk.spring.client.mail.MailSendClient;
import study.cafekiosk.spring.domain.history.mail.MailSendHistory;
import study.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Mock
    //@Spy
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks
    private MailService mailService;

    @Test
    @DisplayName("메일 전송 테스트")
    void sendMail() {
        // given
        /*
        when(
            mailSendClient.sendEmail(
                    anyString(),
                    anyString(),
                    anyString(),
                    anyString()
            )
        )
        .thenReturn(true);
        */

        /*
        doReturn(true)
                .when(mailSendClient)
                .sendEmail(anyString(), anyString(), anyString(), anyString());
        */

        given(mailSendClient.sendEmail(
                anyString(),
                anyString(),
                anyString(),
                anyString())
        ).willReturn(true);


        // when
        boolean result = mailService.sendMail("", "", "", "");


        // then
        assertThat(result).isTrue();
        verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
    }
}