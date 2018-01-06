package cn.et.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.et.emp.Tools.EPageTools;
import cn.et.emp.entity.Emp;
import cn.et.emp.entity.Result;
import cn.et.emp.entity.TreeNode;
import cn.et.emp.service.DeptService;

@RestController
public class DeptController {
	@Autowired
	DeptService service;
	@RequestMapping("/queryDate")
	public List<TreeNode> queryDept(Integer id){
		if(id==null){
			id=0;
		}
		return service.queryTreeNode(id);
	}
	

	@RequestMapping("/queryEmp")
	public EPageTools queryEmp(Integer id,String name,Integer page,Integer rows){
		return service.queryEmp(id,name,page,rows);
	}

	@RequestMapping(value="/emp/{id}",method=RequestMethod.DELETE)
	public Result deleteEmp(@PathVariable String id){
		String[] sbs=id.split(",");
		Result r=new Result();
		r.setCode(1);
		try {
			for(String s:sbs){
				service.deleteemp(Integer.parseInt(s));
			}
			
		} catch (Exception e) {
			r.setCode(0);
			r.setMessage(e);
		}
		return r;
	}

	@RequestMapping(value="/emp/{id}",method=RequestMethod.PUT)
	public Result updateStudent(@PathVariable Integer id,Emp emp){
		emp.setId(id);
		Result r=new Result();
		r.setCode(1);
		try {
			service.updateEmp(emp);
		} catch (Exception e) {
			r.setCode(0);
			r.setMessage(e);
		}
		return r;
	}
	

	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public Result insertStudent(Emp emp){
		Result r=new Result();
		r.setCode(1);
		try {
			service.saveEmp(emp);
		} catch (Exception e) {
			r.setCode(0);
			r.setMessage(e);
		}
		return r;
	}
}
