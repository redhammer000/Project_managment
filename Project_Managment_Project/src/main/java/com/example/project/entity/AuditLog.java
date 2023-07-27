package com.example.project.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
	public class AuditLog {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String entityName;
	    
	    private Long entityId;
	    
	    private String FieldName;
	    
	    private String oldValue;

	    private String newValue;

	    private String action;

	    @CreatedBy
	    private String createdBy;

	    @CreatedDate
	    private LocalDateTime createdAt;

	    @LastModifiedBy
	    private String lastModifiedBy;

	    @LastModifiedDate
	    private LocalDateTime lastModifiedDate;
	    
	    private String DeletedBy;
	    

		public AuditLog(Long id, String entityName, Long entityId, String oldValue, String newValue, String action,
				String createdBy, LocalDateTime createdAt, String lastModifiedBy, LocalDateTime lastModifiedDate, String FieldName , String deletedBy) {
			super();
			this.id = id;
			this.entityName = entityName;
			this.entityId = entityId;
			this.oldValue = oldValue;
			this.newValue = newValue;
			this.action = action;
			this.createdBy = createdBy;
			this.createdAt = createdAt;
			this.lastModifiedBy = lastModifiedBy;
			this.lastModifiedDate = lastModifiedDate;
			
			this.FieldName = FieldName;
			this.setDeletedBy(deletedBy);
		}

		public AuditLog() {

		}

		public String getFieldName() {
			return FieldName;
		}

		public void setFieldName(String fieldName) {
			FieldName = fieldName;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getEntityName() {
			return entityName;
		}

		public void setEntityName(String entityName) {
			this.entityName = entityName;
		}

		public Long getEntityId() {
			return entityId;
		}

		public void setEntityId(Long entityId) {
			this.entityId = entityId;
		}

		public String getOldValue() {
			return oldValue;
		}

		public void setOldValue(String oldValue) {
			this.oldValue = oldValue;
		}

		public String getNewValue() {
			return newValue;
		}

		public void setNewValue(String newValue) {
			this.newValue = newValue;
		}

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}

		public String getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public String getLastModifiedBy() {
			return lastModifiedBy;
		}

		public void setLastModifiedBy(String lastModifiedBy) {
			this.lastModifiedBy = lastModifiedBy;
		}

		public LocalDateTime getLastModifiedDate() {
			return lastModifiedDate;
		}

		public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
			this.lastModifiedDate = lastModifiedDate;
		}

		public String getDeletedBy() {
			return DeletedBy;
		}

		public void setDeletedBy(String deletedBy) {
			DeletedBy = deletedBy;
		}
	    
	    
	    

	   
}
