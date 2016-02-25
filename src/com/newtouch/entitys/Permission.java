package com.newtouch.entitys;

import java.util.Set;

public class Permission {
	
	    private Integer id;
	    private String pname;
	    private String permissioncode;
	    private String permission_uri;
	    
	    private Set<Role> roles ;
           
		public Permission() {
			super();
		}

		public Permission(Integer id, String pname, String permissioncode, String permission_uri, Set<Role> roles) {
			super();
			this.id = id;
			this.pname = pname;
			this.permissioncode = permissioncode;
			this.permission_uri = permission_uri;
			this.roles = roles;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getPname() {
			return pname;
		}

		public void setPname(String pname) {
			this.pname = pname;
		}

		public String getPermissioncode() {
			return permissioncode;
		}

		public void setPermissioncode(String permissioncode) {
			this.permissioncode = permissioncode;
		}

		public String getPermission_uri() {
			return permission_uri;
		}

		public void setPermission_uri(String permission_uri) {
			this.permission_uri = permission_uri;
		}

		public Set<Role> getRoles() {
			return roles;
		}

		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}

		@Override
		public String toString() {
			return "Permission [id=" + id + ", pname=" + pname + ", permissioncode=" + permissioncode
					+ ", permission_uri=" + permission_uri + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((permission_uri == null) ? 0 : permission_uri.hashCode());
			result = prime * result + ((permissioncode == null) ? 0 : permissioncode.hashCode());
			result = prime * result + ((pname == null) ? 0 : pname.hashCode());
			result = prime * result + ((roles == null) ? 0 : roles.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Permission other = (Permission) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (permission_uri == null) {
				if (other.permission_uri != null)
					return false;
			} else if (!permission_uri.equals(other.permission_uri))
				return false;
			if (permissioncode == null) {
				if (other.permissioncode != null)
					return false;
			} else if (!permissioncode.equals(other.permissioncode))
				return false;
			if (pname == null) {
				if (other.pname != null)
					return false;
			} else if (!pname.equals(other.pname))
				return false;
			if (roles == null) {
				if (other.roles != null)
					return false;
			} else if (!roles.equals(other.roles))
				return false;
			return true;
		}
	    
}
