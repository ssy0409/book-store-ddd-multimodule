package kr.ssy.bookstore2.admin.domain.user.enumtype;

public enum UserAuthorityType {
    //.. 권한은 상품 관리자, 회원 관리자, 주문 관리자, 쿠폰 관리자, 권한 관리자가 있으며 각
    PRODUCT, ORDER, USER, COUPON, AUTHORITY;

    public String combinedWithAdmin() {
        return this.name() + "_ADMIN";
    }

}
