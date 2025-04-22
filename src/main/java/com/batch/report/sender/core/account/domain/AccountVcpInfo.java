package com.batch.report.sender.core.account.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Table(name = "tb_account_vcp_info")
@NoArgsConstructor
public class AccountVcpInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpid;

    private String cpnm;

    @Builder
    public AccountVcpInfo(Long id, String cpid, String cpnm) {
        this.id = id;
        this.cpid = cpid;
        this.cpnm = cpnm;
    }
}
