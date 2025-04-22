package com.batch.report.sender.batch;

import com.batch.report.sender.client.mail.MailSendService;
import com.batch.report.sender.core.account.application.service.AccountService;
import com.batch.report.sender.core.account.domain.AccountHistory;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AccountChangeReportBatchConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final AccountService accountService;
    private final MailSendService mailSendService;

    @Bean
    public Job accountJob() throws MessagingException {
        return new JobBuilder("accountJob", jobRepository)
              .listener(jobExecutionListener())
              .start(accountStep())
              .build();
    }

    @Bean
    public Step accountStep() throws MessagingException {
        return new StepBuilder("accountStep", jobRepository)
                .<List<AccountHistory>, List<AccountHistory>>chunk(1, transactionManager)
                .reader(accountChangeReader())
                .processor(accountChangeProcessor())
                .writer(accountChangeWriter())
                .build();
    }

    @Bean
    public ItemReader<List<AccountHistory>> accountChangeReader(){
        // 어제 날짜 기준으로 변경된 계좌 내역을 가져옴
        List<AccountHistory> histories = accountService.findUpdatedAccountHistories();
        // 단일 리스트로 변환 (이중 래핑 제거)
        return new ListItemReader<>(List.of(histories));
    }

    @Bean
    public ItemProcessor<List<AccountHistory>, List<AccountHistory>> accountChangeProcessor() {
        return histories -> {
            log.info("Processing {} account history items", histories.size());
            // 필요한 추가 처리 로직
            return histories;
        };
    }

    @Bean
    public ItemWriter<List<AccountHistory>> accountChangeWriter() {
        return items -> {
            for (List<AccountHistory> historyList : items) {
                if (historyList.isEmpty()) {
                    log.info("계좌 변경 내역이 없습니다. 이메일 전송을 건너뜁니다.");
                    return;
                }

                StringBuilder tableHtml = new StringBuilder();
                tableHtml.append("<h1>가맹점 계좌 변경 내역 안내</h1>");
                tableHtml.append("<p>아래는 어제 발생한 가맹점 계좌 변경 내역입니다.</p>");

                // 테이블 시작
                tableHtml.append("<table border='1' style='border-collapse: collapse; width: 100%;'>");
                tableHtml.append("<tr style='background-color: #f2f2f2;'>");
                tableHtml.append("<th>가맹점ID</th>");
                tableHtml.append("<th>변경일시</th>");
                tableHtml.append("<th>예금주</th>");
                tableHtml.append("<th>은행명</th>");
                tableHtml.append("<th>계좌번호</th>");
                tableHtml.append("<th>변경자</th>");
                tableHtml.append("</tr>");

                // 각 계좌 변경 내역을 테이블 행으로 추가
                for (AccountHistory history : historyList) {
                    tableHtml.append("<tr>");
                    tableHtml.append("<td>").append(history.getCpid()).append("</td>");
                    tableHtml.append("<td>").append(history.getChangedAt()).append("</td>");
                    tableHtml.append("<td>").append(history.getNewAccountHolder()).append("</td>");
                    tableHtml.append("<td>").append(history.getNewBankName()).append("</td>");
                    tableHtml.append("<td>").append(maskAccountNumber(history.getNewAccountNumber())).append("</td>");
                    tableHtml.append("<td>").append(history.getChangedBy()).append("</td>");
                    tableHtml.append("</tr>");
                }

                // 테이블 종료
                tableHtml.append("</table>");

                // 총 건수 정보 추가
                tableHtml.append("<p>총 <b>").append(historyList.size()).append("</b>건의 계좌 변경 내역이 있습니다.</p>");
                tableHtml.append("<p>본 메일은 자동 발송되는 메일입니다. 문의사항이 있으시면 담당자에게 연락 바랍니다.</p>");

                String to = "ryuahneee@gmail.com"; // 실제 운영에서는 환경변수나 설정 파일에서 관리
                String subject = "[가맹점 계좌 변경 내역] " + historyList.size() + "건";

                try {
                    mailSendService.sendHtmlMessage(to, subject, tableHtml.toString());
                    log.info("계좌 변경 내역 이메일 발송 완료: {}건", historyList.size());
                } catch (Exception e) {
                    log.error("이메일 발송 중 오류 발생", e);
                    throw new RuntimeException("이메일 발송 중 오류 발생", e);
                }
            }
        };
    }

    // 계좌번호 마스킹 처리 메소드
    private String maskAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.length() <= 6) {
            return accountNumber;
        }

        // 앞 3자리와 뒤 3자리를 제외한 나머지 부분을 *로 마스킹
        String prefix = accountNumber.substring(0, 3);
        String suffix = accountNumber.substring(accountNumber.length() - 3);
        String masked = "*".repeat(accountNumber.length() - 6);

        return prefix + masked + suffix;
    }

    @Bean
    public JobExecutionListener jobExecutionListener() {
        return new JobExecutionListener() {
            @Override
            public void beforeJob(JobExecution jobExecution) {
                log.info("Job {} 시작됨", jobExecution.getJobInstance().getJobName());
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                log.info("Job {} 완료됨, 상태: {}",
                        jobExecution.getJobInstance().getJobName(),
                        jobExecution.getStatus());
            }
        };
    }
}
