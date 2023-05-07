package com.thinking.machines.webrock;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
public class SessionScope
{
public HttpSession hs;
public SessionScope(HttpSession hs)
{
this.hs=hs;
}
public void setSession(String name,Object object)
{
hs.setAttribute(name,object);
}
public Object getSession(String name)
{
return hs.getAttribute(name);
}
}
