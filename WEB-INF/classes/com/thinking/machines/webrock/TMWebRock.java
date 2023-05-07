package com.thinking.machines.webrock;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import com.thinking.machines.webrock.model.*;
import com.thinking.machines.webrock.pojo.*;
import com.thinking.machines.webrock.annotations.*;
import java.lang.reflect.*;
import com.google.gson.*;
public class TMWebRock extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
/*try
{
String fullPath=request.getServletPath();
fullPath+=request.getPathInfo();
System.out.println(fullPath);
WebRockModel wbr=new WebRockModel();
ServletContext sc=request.getServletContext();
wbr.model=(HashMap<String,Service>)sc.getAttribute("model");
Service s=wbr.model.get(fullPath);
Class c=s.getServiceClass();
Method m=s.getService();
boolean isGet=s.getGet();
boolean isPost=s.getPost();
boolean injectApplicationScope=s.getInjectApplicationScope();
boolean injectRequestScope=s.getInjectRequestScope();
boolean injectSessionScope=s.getInjectSessionScope();
String forwardToPath=s.getForwardTo();
System.out.println(isPost);
Object obj=c.newInstance();
if(injectApplicationScope)
{
Class cc=Class.forName("com.thinking.machines.webrock.ApplicationScope");
ApplicationScope as=(ApplicationScope)cc.newInstance();
as.sc=sc;
if(as!=null) System.out.println("as is not null");
Field[] fields=c.getDeclaredFields();
for(Field field:fields)
{
if(field.getType()==ApplicationScope.class)
{
System.out.println(field.getName());
Field f=c.getDeclaredField(field.getName());
System.out.println("before error");
f.setAccessible(true);
System.out.println("after error");
f.set(obj,as);
System.out.println(f==null);
break;
}
}
}
if(injectRequestScope)
{
Class cc=Class.forName("com.thinking.machines.webrock.RequestScope");
RequestScope rs=(RequestScope)cc.newInstance();
rs.hsr=request;
Field[] fields=c.getDeclaredFields();
for(Field field:fields)
{
if(field.getType()==RequestScope.class)
{
System.out.println(field.getName());
Field f=c.getDeclaredField(field.getName());
System.out.println("before error");
f.setAccessible(true);
System.out.println("after error");
f.set(obj,rs);
System.out.println(f==null);
break;
}
}
}
if(injectSessionScope)
{
Class cc=Class.forName("com.thinking.machines.webrock.SessionScope");
SessionScope ss=(SessionScope)cc.newInstance();
ss.hs=request.getSession();
Field[] fields=c.getDeclaredFields();
for(Field field:fields)
{
if(field.getType()==SessionScope.class)
{
System.out.println(field.getName());
Field f=c.getDeclaredField(field.getName());
System.out.println("before error");
f.setAccessible(true);
System.out.println("after error");
f.set(obj,ss);
System.out.println(f==null);
break;
}
}
}

if(isPost==true && request.getMethod().equalsIgnoreCase("POST") &)
{
m.setAccessible(true);
m.invoke(obj);
if(forwardToPath!="") 
{
RequestDispatcher requestDispatcher=sc.getRequestDispatcher(forwardToPath);
requestDispatcher.forward(request,response);
} 
}
else 
{
System.out.println("inside else");
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}
}catch(Exception e)
{
System.out.println(e);
}*/
try
{
System.out.println("inside post");
String fullPath=request.getServletPath();
fullPath+=request.getPathInfo();
System.out.println(fullPath);
WebRockModel wbr=new WebRockModel();
ServletContext sc=request.getServletContext();
wbr.model=(HashMap<String,Service>)sc.getAttribute("model");
Service s=wbr.model.get(fullPath);
Class c=s.getServiceClass();
Method m=s.getService();
boolean isGet=s.getGet();
boolean isPost=s.getPost();
boolean injectApplicationScope=s.getInjectApplicationScope();
boolean injectRequestScope=s.getInjectRequestScope();
boolean injectSessionScope=s.getInjectSessionScope();
boolean injectApplicationDirectory=s.getInjectApplicationDirectory();
boolean isRequestParameter=s.getIsRequestParameter();
String forwardToPath=s.getForwardTo();
boolean isSecuredAccess=s.getIsSecuredAccess();
ArrayList<RequestObject> alr=s.getRequestObjectList();
boolean isInjectRequestParameter=s.getIsInjectRequestParameter();
Object obj=c.newInstance();
HashMap<String,Class> h=s.getRequestParameterMap();
Object[] paramValues=new Object[m.getParameters().length];
if(isInjectRequestParameter)
{
for(int i=0;i<alr.size();i++)
{
RequestObject rq=alr.get(i);
String ele=request.getParameter(rq.getRequestKey());
Field f=rq.getRequestField();
f.setAccessible(true);
f.set(obj,ele);
}
}
if(isRequestParameter)
{
int ii=0;
for(Map.Entry<String,Class> mapElement:h.entrySet())
{
String ele=request.getParameter(mapElement.getKey());
System.out.println(ele);
if(mapElement.getValue()==int.class)
{
int val=Integer.parseInt(ele);
paramValues[ii]=val;
}
else if(mapElement.getValue()==double.class)
{
double val=Double.parseDouble(ele);
paramValues[ii]=val;
}
else if(mapElement.getValue()==float.class)
{
float val=Float.parseFloat(ele);
paramValues[ii]=val;
}
else if(mapElement.getValue()==boolean.class)
{
boolean val=Boolean.parseBoolean(ele);
paramValues[ii]=val;
}
else if(mapElement.getValue()==long.class)
{
long val=Long.parseLong(ele);
paramValues[ii]=val;
}
else if(mapElement.getValue()==short.class)
{
short val=Short.parseShort(ele);
paramValues[ii]=val;
}
else if(mapElement.getValue()==byte.class)
{
byte val=Byte.parseByte(ele);
paramValues[ii]=val;
}
else if(mapElement.getValue()==char.class)
{
char val=ele.charAt(0);
paramValues[ii]=val;
}
else if(mapElement.getValue()==String.class)
{
String val=ele;
paramValues[ii]=val;
}
ii++;
}
}
if(isSecuredAccess)
{
String securedClass=s.getSecuredClass();
String securedMethod=s.getSecuredMethod();
Class cSec=Class.forName(securedClass);
Method mSec=cSec.getDeclaredMethod(securedMethod);
mSec.setAccessible(true);
Object ooo=cSec.newInstance();
mSec.invoke(obj);
}
if(injectApplicationScope)
{
Class cc=Class.forName("com.thinking.machines.webrock.ApplicationScope");
ApplicationScope as=(ApplicationScope)cc.newInstance();
as.sc=sc;
Field[] fields=c.getDeclaredFields();
for(Field field:fields)
{
if(field.getType()==ApplicationScope.class)
{
System.out.println(field.getName());
Field f=c.getDeclaredField(field.getName());
System.out.println("before error");
f.setAccessible(true);
System.out.println("after error");
f.set(obj,as);
System.out.println(f==null);
break;
}
}
}
if(injectRequestScope)
{
Class cc=Class.forName("com.thinking.machines.webrock.RequestScope");
RequestScope rs=(RequestScope)cc.newInstance();
rs.hsr=request;
Field[] fields=c.getDeclaredFields();
for(Field field:fields)
{
if(field.getType()==RequestScope.class)
{
System.out.println(field.getName());
Field f=c.getDeclaredField(field.getName());
System.out.println("before error");
f.setAccessible(true);
System.out.println("after error");
f.set(obj,rs);
System.out.println(f==null);
break;
}
}
}
if(injectSessionScope)
{
Class cc=Class.forName("com.thinking.machines.webrock.SessionScope");
SessionScope ss=(SessionScope)cc.newInstance();
ss.hs=request.getSession();
Field[] fields=c.getDeclaredFields();
for(Field field:fields)
{
if(field.getType()==SessionScope.class)
{
System.out.println(field.getName());
Field f=c.getDeclaredField(field.getName());
System.out.println("before error");
f.setAccessible(true);
System.out.println("after error");
f.set(obj,ss);
System.out.println(f==null);
break;
}
}
}
if(isPost==true)
{
System.out.println("inside if of scope");
m.setAccessible(true);
Parameter[] scopeParameters=m.getParameters();
if(scopeParameters.length==1)
{
System.out.println("it has only 1 parameter");
System.out.println(scopeParameters[0].getType());
if((scopeParameters[0].getType().isPrimitive()==false) && scopeParameters[0].getType().equals(SessionScope.class)==false && scopeParameters[0].getType().equals(RequestScope.class)==false && scopeParameters[0].getType().equals(ApplicationScope.class)==false && scopeParameters[0].getType().equals(ApplicationDirectory.class)==false)
{
System.out.print("inside if");
BufferedReader br=request.getReader();
StringBuffer b=new StringBuffer();
String d;
while(true)
{
d=br.readLine();
if(d==null) break;
b.append(d);
}
String rawData=b.toString();
System.out.println(rawData);
String json=rawData;
System.out.println(json);
Gson gson=new Gson();
Object object=gson.fromJson(json,scopeParameters[0].getType());
Object arg=scopeParameters[0].getType().cast(object);
paramValues[0]=arg;
} 
}
else if(scopeParameters.length>1)
{
int cnt=0;
for(Parameter para:scopeParameters)
{
if(para.getType().equals(SessionScope.class) || para.getType().equals(RequestScope.class) || para.getType().equals(ApplicationScope.class) || para.getType().equals(ApplicationDirectory.class)) cnt++;
if(para.getType().isPrimitive()==false) cnt--;
}
if(cnt==scopeParameters.length-2) System.out.println("ok");
else System.out.println("not ok");
}
int jj=0;
for(Parameter parameter:scopeParameters)
{
if(parameter.getType().equals(SessionScope.class))
{
paramValues[jj]=new SessionScope(request.getSession());
}
else if(parameter.getType().equals(RequestScope.class))
{
paramValues[jj]=new RequestScope(request);
}
else if(parameter.getType().equals(ApplicationScope.class))
{
paramValues[jj]=new ApplicationScope(request.getServletContext());
}
else if(parameter.getType().equals(ApplicationDirectory.class))
{
File f=new File(request.getServletContext().getRealPath("/WEB-INF/classes"));
paramValues[jj]=new ApplicationDirectory(f);
}
}
Object o=request.getAttribute("xyz");
ArrayList<AutowiredProperties> al=(ArrayList<AutowiredProperties>)request.getAttribute("autowired");
if(o==null)
{
System.out.println("it can be in session or servletContext");
}
else
{
Class ac;
for(int i=0;i<al.size();i++)
{
ac=al.get(i).getClass();
if(ac.isAssignableFrom(o.getClass())) 
{
Object oo=al.get(i).getAutowiredPropertyObject();
oo=o;
}
}
 
}
m.invoke(obj,paramValues);
if(forwardToPath!="") 
{
RequestDispatcher requestDispatcher=sc.getRequestDispatcher(forwardToPath);
requestDispatcher.forward(request,response);
}
}
else 
{
System.out.println("inside else");
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}
}catch(Exception e)
{
System.out.println(e);
}
}
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
System.out.println("inside get");
String fullPath=request.getServletPath();
fullPath+=request.getPathInfo();
System.out.println(fullPath);
WebRockModel wbr=new WebRockModel();
ServletContext sc=request.getServletContext();
wbr.model=(HashMap<String,Service>)sc.getAttribute("model");
Service s=wbr.model.get(fullPath);
Class c=s.getServiceClass();
Method m=s.getService();
boolean isGet=s.getGet();
boolean isPost=s.getPost();
System.out.println("is Get is "+isGet);
System.out.println("is Post is "+isPost);
boolean injectApplicationScope=s.getInjectApplicationScope();
boolean injectRequestScope=s.getInjectRequestScope();
boolean injectSessionScope=s.getInjectSessionScope();
boolean injectApplicationDirectory=s.getInjectApplicationDirectory();
boolean isRequestParameter=s.getIsRequestParameter();
String forwardToPath=s.getForwardTo();
boolean isSecuredAccess=s.getIsSecuredAccess();
ArrayList<RequestObject> alr=s.getRequestObjectList();
boolean isInjectRequestParameter=s.getIsInjectRequestParameter();
Object obj=c.newInstance();
HashMap<String,Class> h=s.getRequestParameterMap();
Object[] paramValues=new Object[m.getParameters().length];
if(isInjectRequestParameter)
{
for(int i=0;i<alr.size();i++)
{
RequestObject rq=alr.get(i);
String ele=request.getParameter(rq.getRequestKey());
Field f=rq.getRequestField();
f.setAccessible(true);
f.set(obj,ele);
}
}
if(isRequestParameter)
{
int ii=0;
for(Map.Entry<String,Class> mapElement:h.entrySet())
{
System.out.println(mapElement.getKey());
String ele=request.getParameter(mapElement.getKey());
System.out.println(ele);
if(mapElement.getValue()==int.class)
{
int val=Integer.parseInt(ele);
paramValues[ii]=val;
}
else if(mapElement.getValue()==double.class)
{
double val=Double.parseDouble(ele);
paramValues[ii]=val;
}
else if(mapElement.getValue()==float.class)
{
float val=Float.parseFloat(ele);
paramValues[ii]=val;
}
else if(mapElement.getValue()==boolean.class)
{
boolean val=Boolean.parseBoolean(ele);
paramValues[ii]=val;
}
else if(mapElement.getValue()==long.class)
{
long val=Long.parseLong(ele);
paramValues[ii]=val;
}
else if(mapElement.getValue()==short.class)
{
short val=Short.parseShort(ele);
paramValues[ii]=val;
}
else if(mapElement.getValue()==byte.class)
{
byte val=Byte.parseByte(ele);
paramValues[ii]=val;
}
else if(mapElement.getValue()==char.class)
{
char val=ele.charAt(0);
paramValues[ii]=val;
}
else if(mapElement.getValue()==String.class)
{
System.out.println("inside if of string");
String val=ele;
paramValues[ii]=val;
}
ii++;
}
}
if(isSecuredAccess)
{
String securedClass=s.getSecuredClass();
String securedMethod=s.getSecuredMethod();
Class cSec=Class.forName(securedClass);
Method mSec=cSec.getDeclaredMethod(securedMethod);
mSec.setAccessible(true);
Object ooo=cSec.newInstance();
mSec.invoke(obj);
}
if(injectApplicationScope)
{
Class cc=Class.forName("com.thinking.machines.webrock.ApplicationScope");
ApplicationScope as=(ApplicationScope)cc.newInstance();
as.sc=sc;
Field[] fields=c.getDeclaredFields();
for(Field field:fields)
{
if(field.getType()==ApplicationScope.class)
{
System.out.println(field.getName());
Field f=c.getDeclaredField(field.getName());
System.out.println("before error");
f.setAccessible(true);
System.out.println("after error");
f.set(obj,as);
System.out.println(f==null);
break;
}
}
}
if(injectRequestScope)
{
Class cc=Class.forName("com.thinking.machines.webrock.RequestScope");
RequestScope rs=(RequestScope)cc.newInstance();
rs.hsr=request;
Field[] fields=c.getDeclaredFields();
for(Field field:fields)
{
if(field.getType()==RequestScope.class)
{
System.out.println(field.getName());
Field f=c.getDeclaredField(field.getName());
System.out.println("before error");
f.setAccessible(true);
System.out.println("after error");
f.set(obj,rs);
System.out.println(f==null);
break;
}
}
}
if(injectSessionScope)
{
Class cc=Class.forName("com.thinking.machines.webrock.SessionScope");
SessionScope ss=(SessionScope)cc.newInstance();
ss.hs=request.getSession();
Field[] fields=c.getDeclaredFields();
for(Field field:fields)
{
if(field.getType()==SessionScope.class)
{
System.out.println(field.getName());
Field f=c.getDeclaredField(field.getName());
System.out.println("before error");
f.setAccessible(true);
System.out.println("after error");
f.set(obj,ss);
System.out.println(f==null);
break;
}
}
}
if(isGet==true)
{
System.out.println("inside if of scope");
m.setAccessible(true);
Parameter[] scopeParameters=m.getParameters();
if(scopeParameters.length==1)
{
System.out.println("it has only 1 parameter");
if((scopeParameters[0].getType().isPrimitive()==false) && (scopeParameters[0].getType()!=String.class) && scopeParameters[0].getType().equals(SessionScope.class)==false && scopeParameters[0].getType().equals(RequestScope.class)==false && scopeParameters[0].getType().equals(ApplicationScope.class)==false && scopeParameters[0].getType().equals(ApplicationDirectory.class)==false)
{
System.out.print("inside if");
BufferedReader br=request.getReader();
StringBuffer b=new StringBuffer();
String d;
while(true)
{
d=br.readLine();
if(d==null) break;
b.append(d);
}
String rawData=b.toString();
System.out.println(rawData);
String json=rawData;
System.out.println(json);
Gson gson=new Gson();
Object object=gson.fromJson(json,scopeParameters[0].getType());
Object arg=scopeParameters[0].getType().cast(object);
paramValues[0]=arg;
} 
}
else if(scopeParameters.length>1)
{
int cnt=0;
for(Parameter para:scopeParameters)
{
if(para.getType().equals(SessionScope.class) || para.getType().equals(RequestScope.class) || para.getType().equals(ApplicationScope.class) || para.getType().equals(ApplicationDirectory.class)) cnt++;
if(para.getType().isPrimitive()==false) cnt--;
}
if(cnt==scopeParameters.length-2) System.out.println("ok");
else System.out.println("not ok");
}
int jj=0;
for(Parameter parameter:scopeParameters)
{
if(parameter.getType().equals(SessionScope.class))
{
paramValues[jj]=new SessionScope(request.getSession());
}
else if(parameter.getType().equals(RequestScope.class))
{
paramValues[jj]=new RequestScope(request);
}
else if(parameter.getType().equals(ApplicationScope.class))
{
paramValues[jj]=new ApplicationScope(request.getServletContext());
}
else if(parameter.getType().equals(ApplicationDirectory.class))
{
File f=new File(request.getServletContext().getRealPath("/WEB-INF/classes"));
paramValues[jj]=new ApplicationDirectory(f);
}
}
Object o=request.getAttribute("xyz");
ArrayList<AutowiredProperties> al=(ArrayList<AutowiredProperties>)request.getAttribute("autowired");
if(o==null)
{
System.out.println("it can be in session or servletContext");
}
else
{
Class ac;
for(int i=0;i<al.size();i++)
{
ac=al.get(i).getClass();
if(ac.isAssignableFrom(o.getClass())) 
{
Object oo=al.get(i).getAutowiredPropertyObject();
oo=o;
}
}
 
}
PrintWriter pw=response.getWriter();
response.setContentType("application/json");
response.setCharacterEncoding("utf-8");
Object resultO=m.invoke(obj,paramValues);
Gson gson=new Gson();
String jsonString=gson.toJson(resultO);
System.out.println(jsonString);
pw.print(jsonString);
pw.flush();
if(forwardToPath!="") 
{
RequestDispatcher requestDispatcher=sc.getRequestDispatcher(forwardToPath);
requestDispatcher.forward(request,response);
}
}
else 
{
System.out.println("inside else");
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}
}catch(Exception e)
{
System.out.println(e);
}
}
}
