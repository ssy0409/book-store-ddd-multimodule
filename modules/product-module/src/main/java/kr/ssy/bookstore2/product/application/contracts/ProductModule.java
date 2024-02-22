package kr.ssy.bookstore2.product.application.contracts;


public interface ProductModule {

    <R extends Result> R executeCommand(Command<R> command);

    <R extends Result> R executeQuery(Query<R> query);
}
