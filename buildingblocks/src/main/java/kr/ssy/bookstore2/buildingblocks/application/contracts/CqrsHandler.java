package kr.ssy.bookstore2.buildingblocks.application.contracts;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface CqrsHandler<C extends CqrsCommand<R>, R extends CqrsResult> {

    R handle(C command);

    default boolean matches(C command) {
        Class<?> handlerType = getClass();
        Class<?> commandType = command.getClass();
        return isAssignable(handlerType, commandType);
    }

    private boolean isAssignable(Class<?> handler, Class<?> command) {
        Type[] interfaces = handler.getGenericInterfaces();
        Type genericSuperclass = handler.getGenericSuperclass();

        ParameterizedType type;
        if (interfaces.length > 0) {
            type = (ParameterizedType) interfaces[0];
        } else {
            type = (ParameterizedType) genericSuperclass;
        }

        Type handlerCommand = type.getActualTypeArguments()[0];
        Class<?> handlerCommandClass;

        if (handlerCommand instanceof ParameterizedType) {
            ParameterizedType parameterized = (ParameterizedType) handlerCommand;
            handlerCommandClass = (Class<?>) parameterized.getRawType();
        } else {
            handlerCommandClass = (Class<?>) handlerCommand;
        }

        return handlerCommandClass.isAssignableFrom(command);
    }
}
