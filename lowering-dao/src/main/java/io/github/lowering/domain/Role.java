package io.github.lowering.domain;

import io.github.lowering.common.domain.Tree;
import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

/**
 * Created by Administrator on 2017/5/19.
 */
@Entity
@Table(name = "lowering_sys_role")
public class Role extends Tree<Role> {
	private String name;
	@Column(unique=true)
	private String constant;
	private String description;

	@OneToMany(mappedBy="role")
	private Set<RoleResource> roleAuthorities = Collections.emptySet();
	@ManyToMany(mappedBy="roles")
	private Set<User> users = Collections.emptySet();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<RoleResource> getRoleAuthorities() {
		return roleAuthorities;
	}
	public void setRoleAuthorities(Set<RoleResource> roleAuthorities) {
		this.roleAuthorities = roleAuthorities;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public String getConstant() {
		return constant;
	}
	public void setConstant(String constant) {
		this.constant = constant;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((constant == null) ? 0 : constant.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (constant == null) {
			if (other.constant != null)
				return false;
		} else if (!constant.equals(other.constant))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
