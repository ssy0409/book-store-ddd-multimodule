package kr.ssy.bookstore2.book.application.command.updatesalesprice;


import kr.ssy.bookstore2.book.application.contracts.Command;

import java.math.BigDecimal;


public record UpdateSalesPrice(
        long id,
        BigDecimal salesPrice,
        long adminId
) implements Command<UpdateSalesPriceResult> {
}
