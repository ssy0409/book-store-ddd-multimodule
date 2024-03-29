package kr.ssy.bookstore2.admin.infrastructure;

import kr.ssy.bookstore2.admin.application.contracts.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserModuleImpl implements UserModule {

    private final ObjectProvider<CommandHandler> commandHandlers;
    private final ObjectProvider<QueryHandler> queryHandlers;

    @Override
    @Transactional
    public <R extends Result> R executeCommand(Command<R> command) {
        var handler = commandHandlers.stream()
                .filter(it -> it.matches(command))
                .findFirst()
                .orElseThrow();

        return (R) handler.handle(command);
    }

    @Override
    public <R extends Result> R executeQuery(Query<R> query) {
        var handler = queryHandlers.stream()
                .filter(it -> it.matches(query))
                .findFirst()
                .orElseThrow();

        return (R) handler.handle(query);
    }
}
