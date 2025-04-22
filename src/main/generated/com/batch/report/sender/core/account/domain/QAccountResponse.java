package com.batch.report.sender.core.account.domain;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.batch.report.sender.core.account.domain.QAccountResponse is a Querydsl Projection type for AccountResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QAccountResponse extends ConstructorExpression<AccountResponse> {

    private static final long serialVersionUID = 401627124L;

    public QAccountResponse(com.querydsl.core.types.Expression<String> cpid, com.querydsl.core.types.Expression<String> businessNumber, com.querydsl.core.types.Expression<String> cpnm, com.querydsl.core.types.Expression<String> accountNumber, com.querydsl.core.types.Expression<String> bankName, com.querydsl.core.types.Expression<String> accountHolder, com.querydsl.core.types.Expression<String> status) {
        super(AccountResponse.class, new Class<?>[]{String.class, String.class, String.class, String.class, String.class, String.class, String.class}, cpid, businessNumber, cpnm, accountNumber, bankName, accountHolder, status);
    }

}

