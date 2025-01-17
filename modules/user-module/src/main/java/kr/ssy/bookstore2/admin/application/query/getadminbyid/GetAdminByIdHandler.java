package kr.ssy.bookstore2.admin.application.query.getadminbyid;

import kr.ssy.bookstore2.admin.application.contracts.QueryHandler;
import kr.ssy.bookstore2.admin.application.query.mapstruct.GetAdminByIdMapper;
import kr.ssy.bookstore2.admin.infrastructure.query.user.UserQueryRepository;
import kr.ssy.bookstore2.buildingblocks.domain.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static kr.ssy.bookstore2.admin.domain.AdminBusinessRuleMessage.ADMIN_NOT_EXISTS;

@Service
@RequiredArgsConstructor
class GetAdminByIdHandler implements QueryHandler<GetAdminById, GetAdminByIdResult> {

    private final UserQueryRepository adminQueryRepository;


    @Override
    public GetAdminByIdResult handle(GetAdminById query) {

        var admin = adminQueryRepository.getAdminById(query.id())
                .orElseThrow(
                        () -> new BusinessRuleException(ADMIN_NOT_EXISTS)
                );


        return GetAdminByIdMapper.INSTANCE.mappingAdminById(admin);
    }
}

