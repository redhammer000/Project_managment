package com.example.project.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.AuditLog;
import com.example.project.repository.AuditLogRepository;

@Service
public class AuditService {
	
	private final AuditLogRepository auditLogRepository;
	
	
	@Autowired
    public  AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    
    
    public void logAudit(AuditLog auditLog) {
        auditLogRepository.save(auditLog);
    }
	
    
    public void set_audit_values(String Action , String name , Long id , String Ename , String F_name , String newvalue , String Oldvalue)
    {
    	
    	AuditLog auditLog = new AuditLog();
    	auditLog.setLastModifiedDate(LocalDateTime.now());
	    auditLog.setAction(Action);
	    auditLog.setLastModifiedBy(name);
	    auditLog.setEntityId(id);
	    auditLog.setEntityName(Ename);
	    auditLog.setFieldName(F_name);
	    auditLog.setNewValue(newvalue);
	    auditLog.setOldValue(Oldvalue);
	    logAudit(auditLog);
    }
}
