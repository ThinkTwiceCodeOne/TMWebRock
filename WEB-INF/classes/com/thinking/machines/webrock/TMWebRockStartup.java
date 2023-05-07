package com.thinking.machines.webrock;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.model.*;
import com.thinking.machines.webrock.pojo.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
public class TMWebRockStartup extends HttpServlet
{
static ArrayList<Service> onStartupSequenceList=new ArrayList<>();
public static void createJsFile(HashMap<String,Service> model,String jsFile)
{
try
{
int f=0;
File file=null;
PrintWriter writer=null;
if(jsFile!=null)
{
file=new File("/Users/yashmundra/Desktop/tomcat9/webapps/tmwebrock/WEB-INF/js/"+jsFile);
writer=new PrintWriter(new FileWriter(file,true));
f=1;
}
System.out.println("success");
HashMap<Class,Boolean> cmap=new HashMap<Class,Boolean>();
for(Service s:model.values())
{
if(s.getServiceClass().isAnnotationPresent(Path.class)) cmap.put(s.getServiceClass(),true);
else cmap.put(s.getServiceClass(),false);
}
for(HashMap.Entry<Class,Boolean> entry:cmap.entrySet()) 
{
Class c=entry.getKey();
if(f==0)
{
file=new File("/Users/yashmundra/Desktop/tomcat9/webapps/tmwebrock/WEB-INF/js/"+c.getSimpleName()+".js");
writer=new PrintWriter(new FileWriter(file,true));
}
//PrintWriter writer=new PrintWriter(new FileWriter(file,true));
//System.out.println("success");
if(entry.getValue())
{
writer.print("class "+c.getSimpleName()+" {\n");
String url="";
Path p=(Path)c.getAnnotation(Path.class);
//url+=p.value();
for(Method m:c.getDeclaredMethods())
{
url="";
url+=p.value();
Path path=(Path)m.getAnnotation(Path.class);
if(path!=null)
{
url+=path.value();
url=url.substring(1);
String httpMethod="";
if(m.isAnnotationPresent(Post.class)) httpMethod="POST";
else if(m.isAnnotationPresent(Get.class)) httpMethod="GET";
String methodName=m.getName();
System.out.println("Method name "+methodName);
writer.print(methodName+"(s)\n");
writer.print("{\n");
Parameter[] parameters=m.getParameters();
if(parameters.length==0)
{
System.out.println("parameters length is zero");
writer.print("return new Promise((resolve,reject)=>{\n");
writer.print("$.ajax({\n");
writer.print("url:'"+url+"',\n");
writer.print("method:'"+httpMethod+"'\n");
writer.print("}).done((data)=>{\n");
writer.print("resolve(data);\n");
writer.print("})\n");
writer.print(".fail((error)=>{\n");
writer.print("reject(error);\n");
writer.print("});\n");
writer.print("});\n");
}
else if(parameters.length>0)
{
Annotation[] ann=parameters[0].getDeclaredAnnotations();
if(ann.length>0)
{
writer.print("var jsonData={\n");
for(Annotation paramAnnotation:ann)
{
if(paramAnnotation instanceof RequestParameter)
{
RequestParameter rq=(RequestParameter)paramAnnotation;
String value=rq.value();
writer.print(value+":"+value+"\n");
}
}
}
else
{
writer.print("var jsonData={\n");
Field[] fields=parameters[0].getType().getDeclaredFields();
int i=0;
for(Field field:fields)
{
System.out.println("field name "+field.getName());
if(i<fields.length-1) writer.print(field.getName()+":s."+field.getName()+",\n");
else writer.print(field.getName()+":s."+field.getName()+"\n");
i++;
}
}
//System.out.println("Method name "+methodName);
//writer.print(methodName+"(s)\n");
//writer.print("{\n");
/*writer.print("var jsonData={\n");
Field[] fields=parameters[0].getType().getDeclaredFields();
int i=0;
for(Field field:fields)
{
System.out.println("field name "+field.getName());
if(i<fields.length-1) writer.print(field.getName()+":s."+field.getName()+",\n");
else writer.print(field.getName()+":s."+field.getName()+"\n");
i++;
}*/
writer.print("\n};\n");
writer.print("return new Promise((resolve,reject)=>{\n");
writer.print("$.ajax({\n");
writer.print("url:'"+url+"',\n");
writer.print("method:'"+httpMethod+"',\n");
writer.print("data:JSON.stringify(jsonData),\n");
writer.print("dataType:'json',\n");
writer.print("contentType:'application/json',\n");
writer.print("mimeType:'application/json:charset=utf-8'\n");
writer.print("}).done((data)=>{\n");
writer.print("resolve(data);\n");
writer.print("})\n");
writer.print(".fail((error)=>{\n");
writer.print("reject(error);\n");
writer.print("});\n");
writer.print("})\n");
writer.print("}\n");
}
}
}
writer.print("}\n");
writer.print("}\n");
}
else
{
writer.print("class "+c.getSimpleName()+" {\n");
for(Field field:c.getDeclaredFields())
{
//writer.print("constructor() {\n");
//writer.print("this._"+field.getName()+"='';\n");
//writer.print("}\n");
writer.print("set "+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1)+"(value){\n");
writer.print("this._"+field.getName()+"=value;\n");
writer.print("}\n");
writer.print("get "+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1)+"(){\n");
writer.print("return this._"+field.getName()+";\n");
writer.print("}\n");
}
writer.print("}\n");
//writer.close();
}
}
writer.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void createFolder()
{
File folder=new File("/Users/yashmundra/Desktop/tomcat9/webapps/tmwebrock/WEB-INF/js");
if((folder.exists() && folder.isDirectory())==false)
{
boolean success=folder.mkdirs();
if(success) System.out.println("folder created");
else System.out.println("folder not created");
}
}
public static void sortList(ArrayList<Service> onStartupSequenceList)
{
int i,j;
for(i=0;i<onStartupSequenceList.size()-1;i++)
{
for(j=0;j<onStartupSequenceList.size()-i-1;j++)
{
if(onStartupSequenceList.get(j).getPriority()>onStartupSequenceList.get(j+1).getPriority())
{
Service ss=onStartupSequenceList.get(j);
onStartupSequenceList.set(j,onStartupSequenceList.get(j+1));
onStartupSequenceList.set(j+1,ss);
}
}
}
}
private static void listFiles(File dir,WebRockModel wbr,Service service,ServletContext sc) 
{
File[] files=dir.listFiles();
for(File file:files)
{
if(file.isDirectory())
{
listFiles(file,wbr,service,sc);
}
else
{
if(file.getName().endsWith(".java"))
{
String fileName=file.getPath().replace("\\","/");
try
{
fileName = fileName.substring(0, fileName.length() - 5);
fileName=fileName.substring(fileName.indexOf("classes") + 8);
fileName=fileName.replace("/",".");
System.out.println(fileName);
String key="";
boolean isGet=false;
boolean isPost=false;
boolean injectApplicationScope=false;
boolean injectRequestScope=false;
boolean injectSessionScope=false;
boolean injectApplicationDirectory=false;
boolean isSecuredAccess=false;
Class c=Class.forName(fileName);
if(c.isAnnotationPresent(InjectApplicationScope.class))
{
injectApplicationScope=true;
}
if(c.isAnnotationPresent(InjectRequestScope.class))
{
injectRequestScope=true;
}
if(c.isAnnotationPresent(InjectSessionScope.class))
{
injectSessionScope=true;
}
if(c.isAnnotationPresent(InjectApplicationDirectory.class))
{
injectApplicationDirectory=true;
}
if(c.isAnnotationPresent(Get.class))
{
System.out.println("Get is present on class");
isGet=true;
}
if(c.isAnnotationPresent(Post.class))
{
System.out.println("Post is present on class");
isPost=true;
}
if(c.isAnnotationPresent(Path.class))
{
Path path=(Path)c.getAnnotation(Path.class);
key=key+path.value();
}
ArrayList<RequestObject> alo=new ArrayList<RequestObject>();
Field[] fields=c.getDeclaredFields();
String autowiredValue="";
boolean isInjectRequestParameter=false;
for(Field f:fields)
{
if(f.isAnnotationPresent(InjectRequestParameter.class))
{
isInjectRequestParameter=true;
InjectRequestParameter irp=(InjectRequestParameter)f.getAnnotation(InjectRequestParameter.class);
RequestObject ro=new RequestObject();
ro.setRequestKey(irp.name());
ro.setRequestField(f);
alo.add(ro);
}
if(f.isAnnotationPresent(Autowired.class))
{
Autowired autowired=(Autowired)f.getAnnotation(Autowired.class);
autowiredValue=autowired.name();
AutowiredProperties ap=new AutowiredProperties();
ap.setAutowiredPropertyObject(f.getType());
service.arrayList.add(ap);
}
}
sc.setAttribute(autowiredValue,service.arrayList);
Method[] methods=c.getDeclaredMethods();
for(Method method:methods)
{
String methodPath="";
String forwardToPath="";
int priorityValue=0;
Service s = new Service();
Parameter[] parameters=method.getParameters();
Class[] parameterTypes=method.getParameterTypes();
boolean isRequestParameter=false;
HashMap<String,Class> h=new HashMap<String,Class>();
int i=0;
for(Parameter param:parameters)
{
if(param.isAnnotationPresent(RequestParameter.class))
{
isRequestParameter=true;
RequestParameter rq=(RequestParameter)param.getAnnotation(RequestParameter.class);
String val=rq.value();
System.out.println(val);
Class ccc=parameterTypes[i];
System.out.println(ccc);
i++;
h.put(val,ccc);
}
}
if(method.isAnnotationPresent(OnStartup.class))
{
Class returnType=method.getReturnType();
if(returnType.getName()=="void" && method.getParameterTypes().length==0)
{
OnStartup onStartup=(OnStartup)method.getAnnotation(OnStartup.class);
priorityValue=onStartup.priority();
s.setRunOnStartup(true);
s.setPriority(priorityValue);
}
else
{
System.out.println("The method should have void return type and no parameters");
}
}
if(method.isAnnotationPresent(Get.class))
{
System.out.println("get is present on method "+method.getName());
isGet=true;
}
if(method.isAnnotationPresent(Post.class))
{
System.out.println("Post is present on method "+method.getName());
isPost=true;
}
if(method.isAnnotationPresent(ForwardTo.class))
{
ForwardTo forwardTo=(ForwardTo)method.getAnnotation(ForwardTo.class);
forwardToPath=forwardTo.value();
s.setForwardTo(forwardToPath);
}
if(method.isAnnotationPresent(SecuredAccess.class))
{
isSecuredAccess=true;
SecuredAccess securedAccess=(SecuredAccess)method.getAnnotation(SecuredAccess.class);
s.setSecuredClass(securedAccess.checkPost());
s.setSecuredMethod(securedAccess.guard());
}
if(method.isAnnotationPresent(Path.class))
{
Path path=(Path)method.getAnnotation(Path.class);
methodPath=path.value();
s.setServiceClass(c);
s.setPath(key+methodPath);
s.setService(method);
s.setGet(isGet);
s.setPost(isPost);
s.setInjectSessionScope(injectSessionScope);
s.setInjectApplicationScope(injectApplicationScope);
s.setInjectRequestScope(injectRequestScope);
s.setInjectApplicationDirectory(injectApplicationDirectory);
s.setRequestParameterMap(h);
s.setIsRequestParameter(isRequestParameter);
s.setIsInjectRequestParameter(isInjectRequestParameter);
s.setRequestObjectList(alo);
s.setIsSecuredAccess(isSecuredAccess);
}
else
{
s.setServiceClass(c);
}
wbr.model.put(key+methodPath,s);
if(s.getPriority()!=0)
{
onStartupSequenceList.add(s);
}
}
sortList(onStartupSequenceList);
}catch(Exception e)
{
System.out.println(e);
}
}
}
}
}
public void init(ServletConfig config) throws ServletException
{
super.init(config);
createFolder();
ServletContext sc=config.getServletContext();
WebRockModel wbr=new WebRockModel();
Service service=new Service();
System.out.println("Hello web rock");
String name=getInitParameter("SERVICE_PACKAGE_PREFIX");
System.out.println(name);
String parentDirectory="/Users/yashmundra/Desktop/tomcat9/webapps/tmwebrock/WEB-INF/classes/"+name;
File directoryPath=new File(parentDirectory);
listFiles(directoryPath,wbr,service,sc);
System.out.println("let print begins");
System.out.println(wbr.model);
String jsFile=getInitParameter("JSFile");
createJsFile(wbr.model,jsFile);
sc.setAttribute("model",wbr.model);
System.out.println("map printing starts");
try
{
for(int i=0;i<onStartupSequenceList.size();i++)
{
Class cc=onStartupSequenceList.get(i).getServiceClass();
Method mm=onStartupSequenceList.get(i).getService();
mm.setAccessible(true);
Object obj=cc.newInstance();
mm.invoke(obj);
}
}catch(Exception e)
{
System.out.println(e);
}

}
}
