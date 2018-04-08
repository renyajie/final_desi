package com.ryj.yuyue.utils;

import java.util.ArrayList;
import java.util.List;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * 调用推荐算法的快捷方法
 * @author Thor
 *
 */
public class CallPython {
	
	public static PythonInterpreter interpreter = new PythonInterpreter();
	public static final String fileLocation = 
			"E:\\python-workspace\\final_desi\\py\\recommand.py";
	
	/**
	 * 获取指定用户编号的团课推荐编号
	 * @param userId 用户编号
	 * @return
	 */
	public static List<Integer> getPeopleClassRecommand(Integer userId) {
		
		interpreter.execfile(fileLocation);
		PyFunction func = (PyFunction) interpreter
				.get("getPeopleRecommandList", PyFunction.class);

        PyObject pyobj = func.__call__(new PyInteger(userId));
        
        //将string转化为list
        String tmp = pyobj.toString();
        tmp = tmp.substring(1, tmp.length() - 1);
        String[] strList = tmp.split(", ");
        List<Integer> result = new ArrayList<Integer>();
        for(String string: strList) {
        	result.add(Integer.parseInt(string));
        }
        return result;
	}
	
	/**
	 * 获取指定用户编号的私教推荐编号
	 * @param userId 用户编号
	 * @return
	 */
	public static List<Integer> getIndividualClassRecommand(Integer userId) {
		
		interpreter.execfile(fileLocation);
		PyFunction func = (PyFunction) interpreter
				.get("getIndividualRecommandList", PyFunction.class);

        PyObject pyobj = func.__call__(new PyInteger(userId));
        
        //将string转化为list
        String tmp = pyobj.toString();
        tmp = tmp.substring(1, tmp.length() - 1);
        String[] strList = tmp.split(", ");
        List<Integer> result = new ArrayList<Integer>();
        for(String string: strList) {
        	result.add(Integer.parseInt(string));
        }
        return result;
	}
	
}
