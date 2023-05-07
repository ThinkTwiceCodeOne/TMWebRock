package com.thinking.machines.webrock;
import java.io.*;
import java.util.*;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.model.*;
import com.thinking.machines.webrock.pojo.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
public class ServiceDoc
{
private static void listFiles(File dir,WebRockModel wbr,Service service) 
{
File[] files=dir.listFiles();
for(File file:files)
{
if(file.isDirectory())
{
listFiles(file,wbr,service);
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
}
}catch(Exception e)
{
System.out.println(e);
}
}
}
}
}
public static void main(String gg[])
{
try
{
WebRockModel wbr=new WebRockModel();
Service service=new Service();
System.out.println("Hello web rock");
String parentDirectory=gg[0];
System.out.println(parentDirectory);
String pdfFilePath=gg[1];
Document document=new Document();
PdfWriter.getInstance(document,new FileOutputStream(pdfFilePath));
document.open();
System.out.println(pdfFilePath);
File directoryPath=new File(parentDirectory);
listFiles(directoryPath,wbr,service);
System.out.println("let print begins");
//System.out.println(wbr.model);
HashMap<String,Service> hm=wbr.model;
int i=0;
for(Map.Entry<String,Service> entry:hm.entrySet())
{
if(entry.getKey().length()==0) System.out.println("it is empty");
System.out.println("key is "+entry.getKey());
if(entry.getKey().length()>0)
{
Method m=entry.getValue().getService();
Parameter[] p=m.getParameters();
String paramValues="";
for(Parameter para:p)
{
paramValues+=para.getType().toString()+" ,";
}
document.add(new Paragraph(String.valueOf(i)+"    Path is="+entry.getKey()));
document.add(new Paragraph("Class is "+entry.getValue().getServiceClass().getPackage().getName()));
document.add(new Paragraph("Method is "+entry.getValue().getService()));
document.add(new Paragraph("Return type is  "+entry.getValue().getService().getReturnType()));
document.add(new Paragraph("Parameters are  "+paramValues));
i++;
}
}
document.close();
}catch(Exception e)
{
System.out.println(e);
}
}
}
