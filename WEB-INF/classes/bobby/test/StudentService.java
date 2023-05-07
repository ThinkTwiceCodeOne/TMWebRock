package bobby.test;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.*;
import java.sql.*;
import java.util.*;
@Path(value="/studentService")
public class StudentService
{
@Post(value="/studentService")
@Path(value="/add")
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
@Post(value="/studentService")
@Path(value="/update")
public void update(Student s1)
{
try
{
System.out.println("inside update method");
Class.forName("com.mysql.cj.jdbc.Driver");
Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/tmdb","tmwebrockuser","tmwebrockuser");
PreparedStatement statement=c.prepareStatement("update Student set name=? where rollNo=?");
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
@Get(value="/studentService")
@Path(value="/getByRollNo")
public Student getByRollNo(@RequestParameter(value="rollNo") String rollNo)
{
Student st=new Student();
try
{
System.out.println("inside getByRollNo method");
System.out.println(rollNo);
Class.forName("com.mysql.cj.jdbc.Driver");
Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/tmdb","tmwebrockuser","tmwebrockuser");
PreparedStatement statement=c.prepareStatement("select name from Student where rollNo=?");
statement.setString(1,rollNo);
ResultSet rs=statement.executeQuery();
while(rs.next())
{
st.setName(rs.getString(1));
}
statement.close();
c.close();
}catch(Exception e)
{
System.out.println(e);
}
return st;
}
@Get(value="/studentService")
@Path(value="/getAll")
public List<Student> getAll()
{
ArrayList<Student> al=new ArrayList<Student>();
try
{
System.out.println("inside getAll method");
Class.forName("com.mysql.cj.jdbc.Driver");
Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/tmdb","tmwebrockuser","tmwebrockuser");
PreparedStatement statement=c.prepareStatement("select * from Student");
ResultSet rs=statement.executeQuery();
while(rs.next())
{
Student st=new Student();
st.setName(rs.getString(1));
st.setRollNo(rs.getString(2));
al.add(st);
}
statement.close();
c.close();
}catch(Exception e)
{
System.out.println(e);
}
return al;
}

}
