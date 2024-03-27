package kr.ssy.bookstore2.frontapi.api.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "사용자 등록, 로그인,배송지 정보 수정")
@Validated
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
}
