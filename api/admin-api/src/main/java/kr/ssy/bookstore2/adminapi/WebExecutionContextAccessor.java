package kr.ssy.bookstore2.adminapi;

import kr.ssy.bookstore2.buildingblocks.application.ExecutionContextAccessor;
import org.springframework.stereotype.Component;

@Component
class WebExecutionContextAccessor implements ExecutionContextAccessor {
    @Override
    public Long getUserId() {
        return 1L;
    }

    @Override
    public String getUserName() {
        return "홍길동";
    }
}
