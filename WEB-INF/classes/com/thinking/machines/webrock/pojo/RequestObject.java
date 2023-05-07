package com.thinking.machines.webrock.pojo;
import java.lang.reflect.*;
public class RequestObject
{
private String requestKey;
private Field requestField;
public RequestObject()
{
this.requestKey="";
this.requestField=null;
}
public void setRequestKey(String requestKey)
{
this.requestKey=requestKey;
}
public String getRequestKey()
{
return this.requestKey;
}
public void setRequestField(Field requestField)
{
this.requestField=requestField;
}
public Field getRequestField()
{
return this.requestField;
}
}
