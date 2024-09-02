package com.backend.backend.RegisterAndLoginBackend.entity;

import enums.NotificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table(name = "notification_template")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationTemplate{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String templateType;
    private String emailTitle;
    private String emailBody;
    private Boolean emailEnabled;
    private String smsTitle;
    private String smsBody;
    private Boolean smsEnabled;
}
