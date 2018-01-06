package cn.et.emp.service;

import java.util.List;

import cn.et.emp.Tools.EPageTools;
import cn.et.emp.entity.Emp;
import cn.et.emp.entity.TreeNode;

public interface DeptService {

	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.StudentService#queryStudent(java.lang.String)
	 */
	public abstract List<TreeNode> queryTreeNode(Integer pid);
	public EPageTools queryEmp(Integer id,String sname,Integer page,Integer rows);
	public void deleteemp(Integer id);
	public void updateEmp(Emp e);
	public void saveEmp(Emp e);
}