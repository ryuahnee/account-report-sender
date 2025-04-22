package com.batch.report.sender.data;

import com.batch.report.sender.core.account.domain.AccountHistory;
import com.batch.report.sender.core.account.domain.AccountResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AccountHistoryRepositoryImpl implements AccountHistoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AccountHistoryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<AccountHistory> findUpdatedAccountHistories(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public List<AccountHistory> findNewAccountHistories(LocalDateTime localDateTime) {
        return null;
    }

    @Override
    public List<AccountResponse> findAccountInfoWithVcpInfo() {
        return null;
    }

}
