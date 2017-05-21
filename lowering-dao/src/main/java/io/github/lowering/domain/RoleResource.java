package io.github.lowering.domain;

import io.github.lowering.common.domain.Id;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lowering_sys_role_resource")
public class RoleResource extends Id {
	
	private static final long serialVersionUID = -7495755021687904897L;
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	@ManyToOne
	@JoinColumn(name="resource_id")
	private Resource resource;
	private Boolean heritable = Boolean.TRUE;
	private Boolean enable = Boolean.TRUE;
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public Boolean getHeritable() {
		return heritable;
	}
	public void setHeritable(Boolean heritable) {
		this.heritable = heritable;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	
}
