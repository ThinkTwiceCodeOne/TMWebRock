package com.thinking.machines.webrock;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
public class ApplicationScope 
{
public ServletContext sc;
public ApplicationScope(ServletContext sc)
{
this.sc=sc;
}
public void setAttribute(String name,Object object)
{
System.out.println("inside setAttribute method");
sc.setAttribute(name,object);
}
public Object getAttribute(String name)
{
return sc.getAttribute(name);
}
}
