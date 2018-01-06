package cn.et.emp.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.emp.Tools.EPageTools;
import cn.et.emp.dao.DeptMapper;
import cn.et.emp.dao.EmpMapper;
import cn.et.emp.entity.Dept;
import cn.et.emp.entity.DeptExample;
import cn.et.emp.entity.Emp;
import cn.et.emp.entity.EmpExample;
import cn.et.emp.entity.EmpExample.Criteria;
import cn.et.emp.entity.TreeNode;
import cn.et.emp.service.DeptService;


@Service
public class DeptServiceImpl implements DeptService  {
	@Autowired
	DeptMapper dm;
	@Autowired
	EmpMapper em;
	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.StudentService#queryStudent(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.deptService#queryTreeNode(java.lang.Integer)
	 */
	public List<TreeNode> queryTreeNode(Integer pid){
		DeptExample de=new DeptExample();
		de.createCriteria().andPidEqualTo(pid);
		List<Dept> dept=dm.selectByExample(de);
		List<TreeNode> deptList=new ArrayList<TreeNode>();
		for(Dept d:dept){
			TreeNode tn=new TreeNode();
			tn.setId(d.getId());
			tn.setText(d.getDname());
			// �жϵ�ǰ�ڵ��Ƿ񻹴����Խڵ�
			if(queryTreeNode(d.getId()).size()==0){
				tn.setState("onpen");
			}
			deptList.add(tn);
		}
		return deptList;
	}
	public EPageTools queryEmp(Integer id,String sname,Integer page,Integer rows){
		EmpExample ee=new EmpExample();
		Criteria cr = ee.createCriteria();
		if(id!=null){
			 cr.andDeptidEqualTo(id);
		}
		if(sname !=null){
			cr.andEnameLike("%"+sname+"%");
		}
		
		int total=queryEmpCount(ee);
		//����sql����ѯ�ܼ�¼��
		
		EPageTools p=new EPageTools(page, rows, total);
		RowBounds rb=new RowBounds(p.getStartIndex()-1, rows);
		List<Emp> empList=em.selectByExampleWithRowbounds(ee, rb);
		p.setRows(empList);
		return p;
	}
	public int queryEmpCount(EmpExample ee){
		int total=(int)em.countByExample(ee);
		return total;
	}
	public void deleteemp(Integer id){
		em.deleteByPrimaryKey(id);
	}
	public void updateEmp(Emp e){
		em.updateByPrimaryKey(e);
	}
	public void saveEmp(Emp e){
		em.insertSelective(e);
	}
}
