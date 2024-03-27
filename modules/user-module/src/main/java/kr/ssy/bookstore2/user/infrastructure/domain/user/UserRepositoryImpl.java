package kr.ssy.bookstore2.user.infrastructure.domain.user;

import kr.ssy.bookstore2.user.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserMyBatisMapper userMyBatisMapper;


}
