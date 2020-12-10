

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author ashraf
 * 
 */
public class JavaClassLoader extends ClassLoader {

    public void invokeClassMethod(String pluginName, String path) {

        try {

            // Create a new JavaClassLoader 
            ClassLoader classLoader = this.getClass().getClassLoader();

            // Load the target class using its binary name
            Class<?> loadedMyClass = classLoader.loadClass(pluginName);

            System.out.println("Loaded class name: " + loadedMyClass.getName());

            // Create a new instance from the loaded class
            Constructor<?> constructor = loadedMyClass.getConstructor();
            Plugin plugin = (Plugin) constructor.newInstance();
            plugin.loadFile(path);
            // Getting the target method from the loaded class and invoke it using its name
            //Method method = loadedMyClass.getMethod(path);
          //  System.out.println("Invoked method name: " + method.getName());
            //method.invoke(myClassObject);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
