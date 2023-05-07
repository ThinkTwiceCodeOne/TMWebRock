class StudentService {
getByRollNo(s)
{
var jsonData={
rollNo:rollNo

};
return new Promise((resolve,reject)=>{
$.ajax({
url:'studentService/getByRollNo',
method:'GET',
data:JSON.stringify(jsonData),
dataType:'json',
contentType:'application/json',
mimeType:'application/json:charset=utf-8'
}).done((data)=>{
resolve(data);
})
.fail((error)=>{
reject(error);
});
})
}
getAll(s)
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
add(s)
{
var jsonData={
name:s.name,
rollNo:s.rollNo

};
return new Promise((resolve,reject)=>{
$.ajax({
url:'studentService/add',
method:'POST',
data:JSON.stringify(jsonData),
dataType:'json',
contentType:'application/json',
mimeType:'application/json:charset=utf-8'
}).done((data)=>{
resolve(data);
})
.fail((error)=>{
reject(error);
});
})
}
update(s)
{
var jsonData={
name:s.name,
rollNo:s.rollNo

};
return new Promise((resolve,reject)=>{
$.ajax({
url:'studentService/update',
method:'POST',
data:JSON.stringify(jsonData),
dataType:'json',
contentType:'application/json',
mimeType:'application/json:charset=utf-8'
}).done((data)=>{
resolve(data);
})
.fail((error)=>{
reject(error);
});
})
}
delete(s)
{
var jsonData={
rollNo:rollNo

};
return new Promise((resolve,reject)=>{
$.ajax({
url:'studentService/delete',
method:'GET',
data:JSON.stringify(jsonData),
dataType:'json',
contentType:'application/json',
mimeType:'application/json:charset=utf-8'
}).done((data)=>{
resolve(data);
})
.fail((error)=>{
reject(error);
});
})
}
}
}
