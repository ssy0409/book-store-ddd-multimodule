package kr.ssy.bookstore2.buildingblocks.infrastructure.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter
@RequiredArgsConstructor
public class CommonDataResponse<T> {

    @Schema(name = "responseCode",
            description = """
                    4자리 응답코드. 정상일 떄 0000을 리턴한다.
                    """,
            requiredMode = REQUIRED,
            example = "0000")
    private final String responseCode;

    @Schema(name = "responseMessage",
            description = """
                    보통 에러일 때만 있다.
                    """,
            example = "Success")
    private final String responseMessage;

    @Schema(name = "data", description = "응답데이터")
    private final T data;

    public static <T> CommonDataResponse<T> ok(T data) {
        return new CommonDataResponse<>(
                "0000",
                "Success",
                data);
    }

    public static CommonDataResponse<Object> emptyOk() {
        return new CommonDataResponse<>(
                "0000",
                "Success",
                null);
    }

}
