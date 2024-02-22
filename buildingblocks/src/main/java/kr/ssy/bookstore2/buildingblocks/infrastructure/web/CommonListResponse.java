package kr.ssy.bookstore2.buildingblocks.infrastructure.web;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter
@RequiredArgsConstructor
public class CommonListResponse<T> {

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

    @ArraySchema(schema = @Schema(name = "data", description = "웅답데이터(목록형)"))
    private final List<T> data;

    public static <T> CommonListResponse<T> ok(List<T> data) {
        return new CommonListResponse<>(
                "0000",
                "Success",
                data);
    }

    public static CommonListResponse<Object> emptyOk() {
        return new CommonListResponse<>(
                "0000",
                "Success",
                Collections.emptyList());
    }
}
