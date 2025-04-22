package com.batch.report.sender.core.account.domain;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Getter
@Setter
public class AccountResponse {

    @Comment("구분값")
    private String cpid;

    @Comment("사업자등록번호")
    private String businessNumber;

    @Comment("가맹점명")
    private String cpnm;

    @Comment("계좌번호")
    private String accountNumber;

    @Comment("은행명")
    private String bankName;

    @Comment("예금주")
    private String accountHolder;

    @Comment("상태 (Y: 사용, N: 미사용)")
    private String status;

    public AccountResponse() {
    }

    @QueryProjection
    public AccountResponse(String cpid, String businessNumber, String cpnm, String accountNumber, String bankName, String accountHolder, String status) {
        this.cpid = cpid;
        this.businessNumber = businessNumber;
        this.cpnm = cpnm;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.accountHolder = accountHolder;
        this.status = status;
    }
}
