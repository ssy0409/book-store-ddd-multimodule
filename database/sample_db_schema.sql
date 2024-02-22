CREATE TABLE `product` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '상품ID',
    `name` varchar(100) NOT NULL COMMENT '상품명',
    `description` varchar(500) DEFAULT NULL COMMENT '상품 설명',
    `msr_price` decimal(18,2) NOT NULL DEFAULT 0.00 COMMENT '권장소비자가',
    `sales_price` decimal(18,2) NOT NULL DEFAULT 0.00 COMMENT '실제 판매가',
    `sales_opened_at` timestamp(6) NOT NULL DEFAULT current_timestamp(6) COMMENT '판매 시작일시',
    `sales_closed_at` timestamp(6) NULL DEFAULT NULL COMMENT '판매 종료일시',
    `status` varchar(100) NOT NULL COMMENT '상품 상태',
    `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '삭제 여부',
    `created_at` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000' COMMENT '등록일시',
    `created_by` bigint(20) NOT NULL COMMENT '등록자',
    `updated_at` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000' COMMENT '최종수정일시',
    `updated_by` bigint(20) NOT NULL COMMENT '최종수정자',
    PRIMARY KEY (`id`),
    KEY `idx_product_01` (`tenant_id`,`sales_opened_at`,`sales_closed_at`)
) ENGINE=InnoDB COMMENT='상품';

CREATE TABLE `product_contents` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '상품의 컨텐츠 ID',
    `product_id` bigint(20) NOT NULL COMMENT '상품ID',
    `contents_id` bigint(20) NOT NULL COMMENT '컨텐츠ID',
    `contents_position_type` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '상품 내 컨텐츠 위치 타입',
    `order_index` int(11) NOT NULL DEFAULT 1 COMMENT '상품 내 컨텐츠 정렬 순서',
    `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '삭제 여부',
    `created_at` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000' COMMENT '등록일시',
    `created_by` bigint(20) NOT NULL COMMENT '등록자',
    `updated_at` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000' COMMENT '최종수정일시',
    `updated_by` bigint(20) NOT NULL COMMENT '최종수정자',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_product_contents_u01` (`product_id`,`contents_id`)
) ENGINE=InnoDB COMMENT='상품의 컨텐츠';

CREATE TABLE `contents` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '컨텐츠 ID',
    `contents_type` varchar(100) NOT NULL COMMENT '컨텐츠 타입',
    `name` varchar(100) NOT NULL COMMENT '컨텐츠 명',
    `description` varchar(500) DEFAULT NULL COMMENT '컨텐츠 설명',
    `data` longtext NOT NULL COMMENT '컨텐츠 데이터',
    `created_at` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000' COMMENT '등록일시',
    `created_by` bigint(20) NOT NULL COMMENT '등록자',
    `updated_at` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000' COMMENT '최종수정일시',
    `updated_by` bigint(20) NOT NULL COMMENT '최종수정자',
    PRIMARY KEY (`id`),
    KEY `idx_contents_01` (`tenant_id`,`name`)
) ENGINE=InnoDB COMMENT='컨텐츠 Master';
