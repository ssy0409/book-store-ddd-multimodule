package kr.ssy.bookstore2.user.application.contracts;


public interface UserModule {

    <R extends Result> R executeCommand(Command<R> command);

    <R extends Result> R executeQuery(Query<R> query);
}
