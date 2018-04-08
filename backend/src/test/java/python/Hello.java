package python;

import org.junit.Test;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * 测试Java调用python代码
 * @author Thor
 *
 */
public class Hello {
	
	/**
	 * Java直接调用Python语句
	 */
	@Test
	public void testOne() {
		PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("days=('mod','Tue','Wed','Thu','Fri','Sat','Sun'); ");
        interpreter.exec("print days[1];");
	}
	
	/**
	 * Java调用python函数
	 */
	@Test
	public void testTwo() {
		
		PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("E:\\python-workspace\\final_desi\\py\\test\\hello.py");
        PyFunction func = (PyFunction) interpreter.get("adder",
                PyFunction.class);

        int a = 2010, b = 2;
        PyObject pyobj = func.__call__(new PyInteger(a), new PyInteger(b));
        System.out.println("anwser = " + pyobj.toString());
	}
	
	/**
	 * Java直接执行Python脚本
	 */
	@Test
	public void testThree() {
		PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("E:\\python-workspace\\final_desi\\py\\test\\hello.py");
	}
}
