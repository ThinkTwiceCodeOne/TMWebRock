package com.thinking.machines.webrock;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
public class ApplicationDirectory extends HttpServlet
{
private File file;
public ApplicationDirectory(File file)
{
this.file=file;
}
public File getDirectory(String name)
{
return new File(getServletContext().getRealPath(name));
}
}
