package kr.ssy.bookstore2.buildingblocks.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
public abstract class AggregateRoot {

    public void addDomainEvent(DomainEvent domainEvent) {
        ApplicationEventPublisher publisher = DomainEventRegistry.publisher();
        if (publisher == null) {
            log.warn("Domain event would be ignored. [ApplicationEventPublisher is null]");
            return;
        }

        publisher.publishEvent(domainEvent);
    }

    protected void checkRule(BusinessRule businessRule) {
        if (businessRule.isBroken()) {
            throw new BusinessRuleException(businessRule.getMessage());
        }
    }
}
