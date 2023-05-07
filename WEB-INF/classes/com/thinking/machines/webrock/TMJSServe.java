package com.thinking.machines.webrock;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
public class TMJSServe extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
System.out.println("inside this");
String fileName=request.getParameter("name");
String filePath=getServletContext().getRealPath("/WEB-INF/js/"+fileName+".js");
File file=new File(filePath);
FileInputStream fis=new FileInputStream(file);
OutputStream out=response.getOutputStream();
byte[] buffer=new byte[4096];
int bytesRead;
while((bytesRead=fis.read(buffer))!=-1)
{
out.write(buffer,0,bytesRead);
}
}catch(Exception e)
{
System.out.println(e);
}
}
}
