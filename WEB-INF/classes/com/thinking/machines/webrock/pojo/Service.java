package com.thinking.machines.webrock.pojo;
import java.lang.reflect.*;
import java.io.*;
import java.util.*;
public class Service
{
private Class serviceClass;
private String path;
private Method service;
private boolean get;
private boolean post;
private String forwardTo;
private boolean runOnStartup;
private int priority;
private boolean injectSessionScope;
private boolean injectApplicationScope;
private boolean injectApplicationDirectory;
private boolean injectRequestScope;
public ArrayList<AutowiredProperties> arrayList;
private HashMap<String,Class> requestParameterMap;
private ArrayList<RequestObject> requestObjectList;
private boolean isRequestParameter;
private boolean isInjectRequestParameter;
private boolean isScopeParameter;
private boolean isSecuredAccess;
private String securedClass;
private String securedMethod;
public Service()
{
this.serviceClass=null;
this.path="";
this.service=null;
this.get=false;
this.post=false;
this.forwardTo="";
this.runOnStartup=false;
this.priority=0;
this.injectSessionScope=false;
this.injectApplicationScope=false;
this.injectApplicationDirectory=false;
this.injectRequestScope=false;
this.arrayList=null;
this.requestParameterMap=null;
this.isRequestParameter=false;
this.isInjectRequestParameter=false;
this.requestObjectList=null;
this.isScopeParameter=false;
this.isSecuredAccess=false;
this.securedClass="";
this.securedMethod="";
}
public void setServiceClass(Class serviceClass)
{
this.serviceClass=serviceClass;
}
public Class getServiceClass()
{
return this.serviceClass;
}
public void setPath(String path)
{
this.path=path;
}
public String getPath()
{
return this.path;
}
public void setService(Method service)
{
this.service=service;
}
public Method getService()
{
return this.service;
}
public void setGet(boolean get)
{
this.get=get;
}
public boolean getGet()
{
return this.get;
}
public void setPost(boolean post)
{
this.post=post;
}
public boolean getPost()
{
return this.post;
}
public void setForwardTo(String forwardTo)
{
this.forwardTo=forwardTo;
}
public String getForwardTo()
{
return this.forwardTo;
}
public void setRunOnStartup(boolean runOnStartup)
{
this.runOnStartup=runOnStartup;
}
public boolean getRunOnStartup()
{
return this.runOnStartup;
}
public void setPriority(int priority)
{
this.priority=priority;
}
public int getPriority()
{
return this.priority;
}
public void setInjectSessionScope(boolean injectSessionScope)
{
this.injectSessionScope=injectSessionScope;
}
public boolean getInjectSessionScope()
{
return this.injectSessionScope;
}
public void setInjectApplicationScope(boolean injectApplicationScope)
{
this.injectApplicationScope=injectApplicationScope;
}
public boolean getInjectApplicationScope()
{
return this.injectApplicationScope;
}
public void setInjectRequestScope(boolean injectRequestScope)
{
this.injectRequestScope=injectRequestScope;
}
public boolean getInjectRequestScope()
{
return this.injectRequestScope;
}
public void setInjectApplicationDirectory(boolean injectApplicationDirectory)
{
this.injectApplicationDirectory=injectApplicationDirectory;
}
public boolean getInjectApplicationDirectory()
{
return this.injectApplicationDirectory;
}
public void setArrayList(ArrayList<AutowiredProperties> al)
{
this.arrayList=al;
}
public ArrayList<AutowiredProperties> getArrayList()
{
return this.arrayList;
}
public void setRequestParameterMap(HashMap<String,Class> requestParameterMap)
{
this.requestParameterMap=requestParameterMap;
}
public HashMap<String,Class> getRequestParameterMap()
{
return this.requestParameterMap;
}
public void setIsRequestParameter(boolean isRequestParameter)
{
this.isRequestParameter=isRequestParameter;
}
public boolean getIsRequestParameter()
{
return this.isRequestParameter;
}
public void setIsInjectRequestParameter(boolean isInjectRequestParameter)
{
this.isInjectRequestParameter=isInjectRequestParameter;
}
public boolean getIsInjectRequestParameter()
{
return this.isInjectRequestParameter;
}
public void setRequestObjectList(ArrayList<RequestObject> requestObjectList)
{
this.requestObjectList=requestObjectList;
}
public ArrayList<RequestObject> getRequestObjectList()
{
return requestObjectList;
}
public void setIsScopeParameter(boolean isScopeParameter)
{
this.isScopeParameter=isScopeParameter;
}
public boolean getIsScopeParameter()
{
return this.isScopeParameter;
}
public void setIsSecuredAccess(boolean isSecuredAccess)
{
this.isSecuredAccess=isSecuredAccess;
}
public boolean getIsSecuredAccess()
{
return this.isSecuredAccess;
}
public void setSecuredClass(String securedClass)
{
this.securedClass=securedClass;
}
public String getSecuredClass()
{
return this.securedClass;
}
public void setSecuredMethod(String securedMethod)
{
this.securedMethod=securedMethod;
}
public String getSecuredMethod()
{
return this.securedMethod;
}
}
