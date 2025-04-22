-- VCP Info 테이블 데이터
INSERT INTO tb_account_vcp_info (id, cpid, cpnm)
VALUES (1, '1234', '네네치킨'),
       (2, '5678', '땅땅치킨'),
       (3, '9012', '굽네치킨'),
       (4, '3456', '교촌치킨'),
       (5, '7890', 'BHC치킨');

-- Account 테이블 데이터
INSERT INTO tb_merchant_account (cpid, business_number, merchant_name, account_number, bank_code, bank_name,
                                 account_holder, status, created_by, created_at, updated_by, updated_at)
VALUES ('1234', '1234567890', '네네치킨 본사', '110-123-456789', '004', '국민은행', '김네네', 'Y', 'admin', CURRENT_TIMESTAMP(),
        'admin', CURRENT_TIMESTAMP()),
       ('5678', '2345678901', '땅땅치킨 본사', '112-234-567890', '011', 'NH농협', '이땅땅', 'Y', 'admin', CURRENT_TIMESTAMP(),
        'admin', CURRENT_TIMESTAMP()),
       ('9012', '3456789012', '굽네치킨 본사', '324-345-678901', '088', '신한은행', '박굽네', 'Y', 'admin', CURRENT_TIMESTAMP(),
        'admin', CURRENT_TIMESTAMP()),
       ('3456', '4567890123', '교촌치킨 본사', '456-456-789012', '020', '우리은행', '최교촌', 'Y', 'admin', CURRENT_TIMESTAMP(),
        'admin', CURRENT_TIMESTAMP()),
       ('7890', '5678901234', 'BHC치킨 본사', '245-567-890123', '081', '하나은행', '정비에이치', 'Y', 'admin', CURRENT_TIMESTAMP(),
        'admin', CURRENT_TIMESTAMP());

-- AccountHistory 테이블 데이터 추가
INSERT INTO tb_merchant_account_history (cpid, new_account_number, new_bank_code, new_bank_name, merchant_name,
                                         changed_by, changed_at, action)
VALUES ('1234', '110-123-456789', '004', '국민은행', '김네네', 'admin', TIMESTAMPADD(DAY, -1, CURRENT_TIMESTAMP()),
        '계좌정보 최초 등록'),
       ('5678', '112-234-567890', '011', 'NH농협', '이땅땅', 'admin', TIMESTAMPADD(DAY, -1, CURRENT_TIMESTAMP()),
        '계좌정보 최초 등록'),
       ('9012', '324-345-678901', '088', '신한은행', '박굽네', 'admin', TIMESTAMPADD(DAY, -1, CURRENT_TIMESTAMP()),
        '계좌정보 변경 (예금주 변경)'),
       ('3456', '456-456-789012', '020', '우리은행', '최교촌', 'admin', TIMESTAMPADD(DAY, -1, CURRENT_TIMESTAMP()), '계좌번호 변경'),
       ('7890', '245-567-890123', '081', '하나은행', '정비에이치', 'admin', TIMESTAMPADD(DAY, -1, CURRENT_TIMESTAMP()), '은행 변경');
