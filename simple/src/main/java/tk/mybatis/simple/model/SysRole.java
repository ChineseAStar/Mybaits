package tk.mybatis.simple.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import tk.mybatis.simple.type.Enabled;

/**
 * 角色表
 */
public class SysRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 角色ID
	 */
	private Long id;
	/**
	 * 角色名
	 */
	private String roleName;
	/**
	 * 有效标志
	 */
	private Enabled enabled;
	/**
	 * 创建人
	 */
	private Long createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 用户信息
	 */
	private SysUser user;
	/**
	 * 角色包含的权限列表
	 * @return
	 */
	private List<SysPrivilege> privilegeList;
	
	//setter和getter方法
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
	public List<SysPrivilege> getPrivilegeList() {
		return privilegeList;
	}
	public void setPrivilegeList(List<SysPrivilege> privilegeList) {
		this.privilegeList = privilegeList;
	}
	public Enabled getEnabled() {
		return enabled;
	}
	public void setEnabled(Enabled enabled) {
		this.enabled = enabled;
	}
	
}
