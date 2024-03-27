package kr.ssy.bookstore2.user.infrastructure.query.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserQueryRepositoryImpl implements UserQueryRepository {
    private final UserQueryMyBatisMapper userQueryMyBatisMapper;


}
