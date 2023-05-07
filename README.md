# TMWebRock
It is webservice framework which provides ease to the developer


It provides various feature. 
User can use different annotations to specify Path, Type of Request, Request Parameter, Autowiring.
Also user don't need to create JS file. User just need to create Java Services and POJO classes and the framework will generate equivalent JS file. 
Along with that there is a tool named as ServiceDoc. The tool can generate a pdf which contain all the information like which class has what methods and what annotations and what is the path, etc.

The project is compiled but if you want to compile use the following command (yashmundra is my username). 
You need to put the thing in tomcat9/webapps
```
javac -classpath "/Users/yashmundra/Desktop/tomcat9/lib/*:/Users/yashmundra/Desktop/tomcat9/webapps/tmwebrock/WEBINF/lib/*:/Users/yashmundra/Desktop/tomcat9/webapps/tmwebrock/WEB-INF/classes:." *.java
```

Now some example how you can use the framework.
You need to create your services inside WEB-INF/classes folder and you've to specify the folder name in web.xml and the param name and param value should be like this 
```
<init-param>
<param-name>SERVICE_PACKAGE_PREFIX</param-name>
<param-value>bobby</param-value>
</init-param>
```

```
package bobby.test;\n
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.*;
import java.sql.*;
import java.util.*;
@Path(value="/studentService")
public class StudentService
\n{
\n@Post(value="/studentService")
\n@Path(value="/add")
public void add(Student s1)
{
try
{
System.out.println("inside add of student method");
Class.forName("com.mysql.cj.jdbc.Driver");
Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/tmdb","tmwebrockuser","tmwebrockuser");
PreparedStatement statement=c.prepareStatement("insert into Student(name,rollNo)values(?,?)");
statement.setString(1,s1.getName());
statement.setString(2,s1.getRollNo());
statement.executeUpdate();
statement.close();
c.close();
}catch(Exception e)
{
System.out.println(e);
}
}
@Get(value="/studentService")
@Path(value="/delete")
public void delete(@RequestParameter(value="rollNo") String rollNo)
{
try
{
System.out.println("inside delete method");
System.out.println(rollNo);
Class.forName("com.mysql.cj.jdbc.Driver");
Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/tmdb","tmwebrockuser","tmwebrockuser");
PreparedStatement statement=c.prepareStatement("delete from Student where rollNo=?");
statement.setString(1,rollNo);
statement.executeUpdate();
statement.close();
c.close();
}catch(Exception e)
{
System.out.println(e);
}
}

}
```


User can use ApplicationScope, RequestScope, SessionScope, ApplicationDirectory in parameters of any method and then call getAttribute or setAttribute to get or set data in Scope respectively. 

Now the client side JS file will be created automatically and you can use like this in your html or jsp
```
<script src='/tmwebrock/JsFile?name=Student'></script>
```
Note that you've to write this in web.xml to specify the name of JS file that you want to create otherwise it'll create separate JS file for each Java File.
```
<init-param>
<param-name>JSFile</param-name>
<param-value>Student</param-value>
</init-param>
```
You can only change param-value if you change param-name then you'll not get the JS File

Enjoy!!
