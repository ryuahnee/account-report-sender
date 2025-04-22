package com.batch.report.sender.core.account.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAccountVcpInfo is a Querydsl query type for AccountVcpInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccountVcpInfo extends EntityPathBase<AccountVcpInfo> {

    private static final long serialVersionUID = 1977757854L;

    public static final QAccountVcpInfo accountVcpInfo = new QAccountVcpInfo("accountVcpInfo");

    public final StringPath cpid = createString("cpid");

    public final StringPath cpnm = createString("cpnm");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QAccountVcpInfo(String variable) {
        super(AccountVcpInfo.class, forVariable(variable));
    }

    public QAccountVcpInfo(Path<? extends AccountVcpInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccountVcpInfo(PathMetadata metadata) {
        super(AccountVcpInfo.class, metadata);
    }

}

