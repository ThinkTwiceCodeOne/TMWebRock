package com.thinking.machines.webrock;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
public class RequestScope
{
public HttpServletRequest hsr;
public RequestScope(HttpServletRequest hsr)
{
this.hsr=hsr;
}
public void setAttribute(String name,Object object)
{
hsr.setAttribute(name,object);
}
public Object getAttribute(String name)
{
return hsr.getAttribute(name);
}
}
