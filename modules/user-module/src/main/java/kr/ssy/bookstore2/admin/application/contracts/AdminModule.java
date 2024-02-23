package kr.ssy.bookstore2.admin.application.contracts;


public interface AdminModule {

    <R extends Result> R executeCommand(Command<R> command);

    <R extends Result> R executeQuery(Query<R> query);
}
