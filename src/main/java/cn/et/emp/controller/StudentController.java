package cn.et.emp.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.et.emp.Tools.PageTools;
import cn.et.emp.entity.Result;
import cn.et.emp.entity.Student;
import cn.et.emp.service.StudentService;

@Controller
public class StudentController {
	@Autowired
	StudentService service;
	@ResponseBody
	@RequestMapping("/queryStudent")
	public PageTools queryStudent(String sname,Integer page,Integer rows){
		return service.queryStudent(sname,page,rows);
	}
	
	@ResponseBody
	@RequestMapping(value="/student/{sid}",method=RequestMethod.DELETE)
	public Result deleteStudent(@PathVariable String sid,Integer page,Integer rows){
		String[] sbs=sid.split(",");
		Result r=new Result();
		r.setCode(1);
		try {
			for(String s:sbs){
				service.deleteStudent(Integer.parseInt(s));
			}
			
		} catch (Exception e) {
			r.setCode(0);
			r.setMessage(e);
		}
		return r;
	}
	
	@ResponseBody
	@RequestMapping(value="/student",method=RequestMethod.POST)
	public Result insertStudent(Student student,MultipartFile myImage){
		Result r=new Result();
		r.setCode(1);
		try {
			String fileName=myImage.getOriginalFilename();
			File file=new File("E:\\images\\"+fileName);
			myImage.transferTo(file);
			service.saveStudent(student);
		} catch (Exception e) {
			r.setCode(0);
			r.setMessage(e);
		}
		return r;
	}
	@ResponseBody
	@RequestMapping(value="/student/{sid}",method=RequestMethod.PUT)
	public Result updateStudent(@PathVariable Integer sid,Student student,Integer page,Integer rows){
		student.setSid(sid);
		Result r=new Result();
		r.setCode(1);
		try {
			service.updateStudent(student);
		} catch (Exception e) {
			r.setCode(0);
			r.setMessage(e);
		}
		return r;
	}
}
