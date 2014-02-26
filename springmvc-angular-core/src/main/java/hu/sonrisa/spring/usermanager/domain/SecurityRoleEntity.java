package hu.sonrisa.spring.usermanager.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "security_role", catalog = "", schema = "")
@NamedQueries({
		@NamedQuery(name = "SecurityRoleEntity.findAll", query = "SELECT s FROM SecurityRoleEntity s"),
		@NamedQuery(name = "SecurityRoleEntity.findById", query = "SELECT s FROM SecurityRoleEntity s WHERE s.id = :id"),
		@NamedQuery(name = "SecurityRoleEntity.findByName", query = "SELECT s FROM SecurityRoleEntity s WHERE s.name = :name") })
public class SecurityRoleEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@ManyToMany(mappedBy = "securityRoleCollection")
	@JsonIgnore
	private Collection<MyUser> userCollection = new HashSet(0);

	public SecurityRoleEntity() {
	}

	public SecurityRoleEntity(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Collection<MyUser> getUserCollection() {
		return userCollection;
	}

	public void setUserCollection(Collection userCollection) {
		this.userCollection = userCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set

		if (!(object instanceof SecurityRoleEntity)) {
			return false;
		}

		SecurityRoleEntity other = (SecurityRoleEntity) object;

		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

}
