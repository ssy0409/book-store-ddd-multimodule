package kr.ssy.bookstore2.order.application.contracts;


public interface OrderModule {

    <R extends Result> R executeCommand(Command<R> command);

    <R extends Result> R executeQuery(Query<R> query);
}
