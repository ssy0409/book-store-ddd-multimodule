package kr.ssy.bookstore2.admin.application.query.getadminbyemail;

import kr.ssy.bookstore2.admin.application.contracts.QueryHandler;
import kr.ssy.bookstore2.admin.application.query.mapstruct.GetAdminByEmailMapper;
import kr.ssy.bookstore2.admin.infrastructure.query.user.UserQueryRepository;
import kr.ssy.bookstore2.buildingblocks.domain.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static kr.ssy.bookstore2.admin.domain.AdminBusinessRuleMessage.ADMIN_NOT_EXISTS;

@Service
@RequiredArgsConstructor
class GetAdminByEmailHandler implements QueryHandler<GetAdminByEmail, GetAdminByEmailResult> {

    private final UserQueryRepository adminQueryRepository;


    @Override
    public GetAdminByEmailResult handle(GetAdminByEmail query) {

        var admin = adminQueryRepository.getAdminByEmail(query.email())
                .orElseThrow(
                        () -> new BusinessRuleException(ADMIN_NOT_EXISTS)
                );


        return GetAdminByEmailMapper.INSTANCE.mappingAdminById(admin);
    }
}

