package com.batch.report.sender.core.account.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAccountHistory is a Querydsl query type for AccountHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccountHistory extends EntityPathBase<AccountHistory> {

    private static final long serialVersionUID = -1681531263L;

    public static final QAccountHistory accountHistory = new QAccountHistory("accountHistory");

    public final StringPath action = createString("action");

    public final DateTimePath<java.time.LocalDateTime> changedAt = createDateTime("changedAt", java.time.LocalDateTime.class);

    public final StringPath changedBy = createString("changedBy");

    public final StringPath cpid = createString("cpid");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath newAccountHolder = createString("newAccountHolder");

    public final StringPath newAccountNumber = createString("newAccountNumber");

    public final StringPath newBankCode = createString("newBankCode");

    public final StringPath newBankName = createString("newBankName");

    public QAccountHistory(String variable) {
        super(AccountHistory.class, forVariable(variable));
    }

    public QAccountHistory(Path<? extends AccountHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccountHistory(PathMetadata metadata) {
        super(AccountHistory.class, metadata);
    }

}

