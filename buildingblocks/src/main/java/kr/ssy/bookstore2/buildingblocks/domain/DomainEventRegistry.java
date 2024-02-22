package kr.ssy.bookstore2.buildingblocks.domain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DomainEventRegistry implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static ApplicationEventPublisher publisher() {
        if (applicationContext == null) {
            return null;
        }

        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        if (DomainEventRegistry.applicationContext == null) {
            DomainEventRegistry.applicationContext = applicationContext;
        }
    }
}
