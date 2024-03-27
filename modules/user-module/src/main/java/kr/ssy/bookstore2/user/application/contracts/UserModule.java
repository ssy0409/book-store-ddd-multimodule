package kr.ssy.bookstore2.user.application.contracts;



public interface UserModule {

    <R extends kr.ssy.bookstore2.user.application.contracts.Result> R executeCommand(Command<R> command);

    <R extends Result> R executeQuery(Query<R> query);
}
