package com.batch.report.sender.data;

import com.batch.report.sender.core.account.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class AccountHistoryRepositoryCustomImpl implements AccountHistoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AccountHistoryRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }


    // 어제날짜로 인입된 정보들 가져오기
    @Override
    public List<AccountHistory> findUpdatedAccountHistories(LocalDateTime start,LocalDateTime end) {
        QAccountHistory history = QAccountHistory.accountHistory;
        QAccount account = QAccount.account;

       return queryFactory
               .selectFrom(history)
                .where(history.changedAt.between(start,end)).fetch();

    }
    @Override
    public List<AccountHistory> findNewAccountHistories(LocalDateTime localDateTime) {
        return null;
    }

    @Override
    public List<AccountResponse> findAccountInfoWithVcpInfo() {
        QAccount account = QAccount.account;
        QAccountVcpInfo vcpInfo = QAccountVcpInfo.accountVcpInfo;

        return queryFactory.select(new QAccountResponse(
                        vcpInfo.cpid,
                        account.businessNumber,
                        vcpInfo.cpnm,
                        account.accountNumber,
                        account.bankName,
                        account.accountHolder,
                        account.status
                )).from(vcpInfo)
                .leftJoin(account)
                .on(vcpInfo.cpid.eq(account.cpid))
                .fetch();
    }
}
