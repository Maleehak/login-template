package com.backend.backend.RegisterAndLoginBackend.repository;

import com.backend.backend.RegisterAndLoginBackend.entity.NotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate, Integer> {
    Optional<NotificationTemplate> findByTemplateType(String type);
}
