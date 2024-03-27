package kr.ssy.bookstore2.adminapi.api.book.mapstruct;

import kr.ssy.bookstore2.adminapi.api.book.request.CreateBookRequest;
import kr.ssy.bookstore2.adminapi.api.book.request.UpdateBookStatusRequest;
import kr.ssy.bookstore2.adminapi.api.book.request.UpdateSalesPriceRequest;
import kr.ssy.bookstore2.book.application.command.createbook.CreateBook;
import kr.ssy.bookstore2.book.application.command.updateBookStatus.UpdateBookStatus;
import kr.ssy.bookstore2.book.application.command.updatesalesprice.UpdateSalesPrice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookControllerMapper {
    BookControllerMapper INSTANCE = Mappers.getMapper(BookControllerMapper.class);


    CreateBook mappingCreateBook(CreateBookRequest request, long adminId);

    UpdateBookStatus mappingUpdatBookStatus(UpdateBookStatusRequest request, long adminId);

    UpdateSalesPrice mappingUpdateSalesPrice(UpdateSalesPriceRequest request, long adminId);

}
