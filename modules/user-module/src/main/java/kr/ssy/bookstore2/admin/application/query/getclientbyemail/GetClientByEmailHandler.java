package kr.ssy.bookstore2.admin.application.query.getclientbyemail;

import kr.ssy.bookstore2.admin.application.contracts.QueryHandler;
import kr.ssy.bookstore2.admin.application.query.mapstruct.GetClientByEmailMapper;
import kr.ssy.bookstore2.admin.infrastructure.query.user.UserQueryRepository;
import kr.ssy.bookstore2.buildingblocks.domain.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static kr.ssy.bookstore2.admin.domain.AdminBusinessRuleMessage.ADMIN_NOT_EXISTS;

@Service
@RequiredArgsConstructor
class GetClientByEmailHandler implements QueryHandler<GetClientByEmail, GetClientByEmailResult> {

    private final UserQueryRepository adminQueryRepository;


    @Override
    public GetClientByEmailResult handle(GetClientByEmail query) {

        var client = adminQueryRepository.getClientByEmail(query.email())
                .orElseThrow(
                        () -> new BusinessRuleException(ADMIN_NOT_EXISTS)
                );


        return GetClientByEmailMapper.INSTANCE.mappingAdminById(client);
    }
}

