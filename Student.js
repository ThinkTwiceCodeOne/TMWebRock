class Student
{
constructor(name,rollNo)
{
this._name=name;
this.rollNo=rollNo;
}
set name(name)
{
this._name=name;
}
get name()
{
return this._name;
}
set rollNo(rollNo)
{
this._rollNo=rollNo;
}
get rollNo()
{
return this._rollNo;
}
}
class StudentService
{
add(s)
{
var jsonData={
name:s.name,
rollNo:s.rollNo
}
return new Promise((resolve,reject)=>{
$.ajax({
url:'studentService/add',
method:'POST',
data:JSON.stringify(jsonData),
dataType:'json',
contentType:'application/json',
mimeType:'application/json;charset=utf-8'
}).done((data)=>{
resolve(data);
})
.fail((error)=>{
reject(error);
});
});
}
update(s)
{
//let name=$('#updateModule #name').val();
//let rollNo=$('#updateModule #rollNo').val();
var jsonData={
name:s.name,
rollNo:s.rollNo
}
return new Promise((resolve,reject)=>{
$.ajax({
url:'studentService/update',
method:'POST',
data:JSON.stringify(jsonData),
dataType:'json',
contentType:'application/json',
mimeType:'application/json;charset=utf-8'
}).done((data)=>{
resolve(data);
})
.fail((error)=>{
reject(error);
});
});
}
delete(rollNo)
{
let rollNo=$('#deleteModule #rollNo').val();
return new Promise((resolve,reject)=>{
$.ajax({
url:'studentService/delete',
method:'GET',
data:{
rollNo:rollNo
}
}).done((data)=>{
resolve(data);
})
.fail((error)=>{
reject(error);
});
});
}
getByRollNo(rollNo)
{
//let rollNo=$('#getByRollNoModule #rollNo').val();
return new Promise((resolve,reject)=>{
$.ajax({
url:'studentService/getByRollNo',
method:'GET',
data:{
rollNo:rollNo
}
}).done((data)=>{
resolve(data);
})
.fail((error)=>{
reject(error);
});
});
}
getAll()
{
return new Promise((resolve,reject)=>{
$.ajax({
url:'studentService/getAll',
method:'GET'
}).done((data)=>{
resolve(data);
})
.fail((error)=>{
reject(error);
});
});
}
}
