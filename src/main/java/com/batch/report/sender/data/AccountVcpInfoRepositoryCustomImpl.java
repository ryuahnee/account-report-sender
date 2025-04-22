package com.batch.report.sender.data;

import com.batch.report.sender.core.account.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AccountVcpInfoRepositoryCustomImpl implements AccountVcpInfoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AccountVcpInfoRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
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
