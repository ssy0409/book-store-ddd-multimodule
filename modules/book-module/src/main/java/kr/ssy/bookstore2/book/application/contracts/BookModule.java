package kr.ssy.bookstore2.book.application.contracts;


public interface BookModule {

    <R extends Result> R executeCommand(Command<R> command);

    <R extends Result> R executeQuery(Query<R> query);
}
