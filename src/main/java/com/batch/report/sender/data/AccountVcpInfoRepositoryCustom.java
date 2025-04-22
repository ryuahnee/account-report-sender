package com.batch.report.sender.data;

import com.batch.report.sender.core.account.domain.AccountResponse;

import java.util.List;

public interface AccountVcpInfoRepositoryCustom {
    // 메서드 이름을 더 적절하게 변경
    List<AccountResponse> findAccountInfoWithVcpInfo();
}
