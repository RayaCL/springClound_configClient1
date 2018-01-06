package cn.et.emp.service;

import cn.et.emp.Tools.PageTools;
import cn.et.emp.entity.Student;


public interface StudentService {

	public abstract PageTools queryStudent(String sname,Integer page,Integer rows);
	public void deleteStudent(Integer sid);
	public void updateStudent(Student stu);
	public void saveStudent(Student stu);
}