import java.io.FileReader;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

public class GraalJsDemo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ScriptEngineManager manager = new ScriptEngineManager();
			List<ScriptEngineFactory> factories = manager.getEngineFactories();
			factories.forEach((sef)->System.out.println(sef.getEngineName()));
			ScriptEngine engine = manager.getEngineByName("graal.js");
			engine.eval(new FileReader("C:\\Users\\Wissen\\Desktop\\Project\\Assignment\\Spring\\ws\\NashornDemo\\src\\nashorn\\demo.js"));
			
			Invocable inv = (Invocable) engine;
			inv.invokeFunction("abc");
			inv.invokeFunction("add", 10, 20);
			String greeting = (String) inv.invokeFunction("greet", "Wissen", "Technology");
			System.out.println(greeting);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
