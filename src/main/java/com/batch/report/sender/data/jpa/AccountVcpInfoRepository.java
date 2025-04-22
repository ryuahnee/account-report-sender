package com.batch.report.sender.data.jpa;

import com.batch.report.sender.core.account.domain.AccountVcpInfo;
import com.batch.report.sender.data.AccountVcpInfoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountVcpInfoRepository extends JpaRepository<AccountVcpInfo, Long>, AccountVcpInfoRepositoryCustom {
}
