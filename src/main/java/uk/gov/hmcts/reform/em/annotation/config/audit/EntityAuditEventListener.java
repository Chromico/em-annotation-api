package uk.gov.hmcts.reform.em.annotation.config.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

public class EntityAuditEventListener extends AuditingEntityListener {

    private final Logger log = LoggerFactory.getLogger(EntityAuditEventListener.class);
    private static final String NO_BEAN_FOUND = "No bean found for AsyncEntityAuditEventWriter";
    private static BeanFactory beanFactory;

    @PostPersist
    public void onPostCreate(Object target) {
        try {
            AsyncEntityAuditEventWriter asyncEntityAuditEventWriter = beanFactory.getBean(AsyncEntityAuditEventWriter.class);
            asyncEntityAuditEventWriter.writeAuditEvent(target, EntityAuditAction.CREATE);
        } catch (NoSuchBeanDefinitionException e) {
            log.error(NO_BEAN_FOUND, e);
        } catch (Exception e) {
            log.error("Exception while persisting create audit entity {}", e.getMessage(), e);
        }
    }

    @PostUpdate
    public void onPostUpdate(Object target) {
        try {
            AsyncEntityAuditEventWriter asyncEntityAuditEventWriter = beanFactory.getBean(AsyncEntityAuditEventWriter.class);
            asyncEntityAuditEventWriter.writeAuditEvent(target, EntityAuditAction.UPDATE);
        } catch (NoSuchBeanDefinitionException e) {
            log.error(NO_BEAN_FOUND, e);
        } catch (Exception e) {
            log.error("Exception while persisting update audit entity {}", e.getMessage(), e);
        }
    }

    @PostRemove
    public void onPostRemove(Object target) {
        try {
            AsyncEntityAuditEventWriter asyncEntityAuditEventWriter = beanFactory.getBean(AsyncEntityAuditEventWriter.class);
            asyncEntityAuditEventWriter.writeAuditEvent(target, EntityAuditAction.DELETE);
        } catch (NoSuchBeanDefinitionException e) {
            log.error(NO_BEAN_FOUND, e);
        } catch (Exception e) {
            log.error("Exception while persisting delete audit entity {}", e.getMessage(), e);
        }
    }

    static void setBeanFactory(BeanFactory beanFactory) {
        EntityAuditEventListener.beanFactory = beanFactory;
    }

}
