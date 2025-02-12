var book_code;
var book_title;
var book_author;
var book_cnt;
var price;
var book_content;
var book_category;

$(document).ready(function(){
	book_category = $("#selectedCategory").val();
	
	$("#book_category").val(book_category).prop("selected",true);
	
});

let regex = new RegExp("(. *?)\.(jpg|png|PNG)$");
let maxSize = 1048576;
function fileCheck(fileName, fileSize){
	
	if(fileSize >= maxSize){
		alert("파일 사이즈 초과");
		return false;
	}
	
	if(!regex.test(fileName)){
		alert("해당 종류의 파일은 업로드할 수 없습니다.");
		return false;
	}
	
	return true;
}


function DeleteBook()
{
	book_code = $("#book_code").val();
	book_title = $("#book_title").val();
	
	$.ajax({
		url:  '/book/deleteBook',
		data: {"book_code" : book_code},
		type: "POST",
		success : function(){
			alert(book_title+"이(가) 삭제되었습니다.");
			location.href="list";
		}
	});

}


function EditMode()
{
	//수정부분 활성화
	$('#book_title').attr('readonly',false);
	$('#book_author').attr('readonly',false);
	$('#book_quantity').attr('readonly',false);
	$('#book_price').attr('readonly',false);
	$('#book_content').attr('readonly',false);
	$('#book_category').attr('disabled',false);
	
	//하단의 버튼 변경
	$(".footerBtn").detach();
	
	$('.BtnDiv').append("<input class='footerBtn' type='button' value='돌아가기' onclick='rePage()'/>")
	$('.BtnDiv').append("<input class='footerBtn' type='button' value='수정하기' onclick='update()'/>")
	
	//이미지 삭제와 파일 선택 버튼 추가
	
	$('.imgArea').append("<input type='file' id='updateFile' name='uploadFile' onchange='fileUpload(this)'>")
}


function update()
{
	book_code = $("#book_code").val();
	book_title = $("#book_title").val();
	book_author = $("#book_author").val();
	book_category = $("#book_category").val();
	book_quantity = $("#book_quantity").val();
	book_price = $("#book_price").val();
	book_content = $("#book_content").val();
	book_img = $("#updateFile").val();
	img_name = $("#img_name").val();
	img_uuid = $("#img_uuid").val();
	img_path = $("#img_path").val();
	
	console.log("book_img : "+book_img);
		
	if(book_img != "")
	{
		console.log("img_name : "+img_name);
		console.log("img_uuid : "+img_uuid);
		console.log("img_path : "+img_path);
		
		$.ajax({
			url: "/book/updateImg",
			type: "POST",
			data: {
				"book_code":book_code,
				"img_name":img_name,
				"img_uuid":img_uuid,
				"img_path":img_path
			},
			success: function()
			{
				console.log("이미지 업데이트 성공");
			}
		});
	}
	$.ajax({
		url: "/book/updateBook",
		type: "POST",
		data: {
			"book_code":book_code,
			"book_title":book_title,
			"book_author":book_author,
			"book_category":book_category,
			"book_quantity":book_quantity,
			"book_price":book_price,
			"book_content":book_content
		},
		success: function()
		{
			alert("수정 성공");
			location.href="list";
		}
	});
	
}

function rePage()
{
	location.reload(true);
}

function deleteFile()
{
	let targetFile = $(".imgDeleteBtn").data("file");
	
	let targetDiv = $("#imgArea");
	
	$.ajax({
		url:'/book/deleteFile',
		data : {fileName : targetFile},
		dataType : 'text',
		type : 'POST',
		success : function(result){
			console.log(result);
			
			targetDiv.remove();
			$("input[type='file']").val("");
		},
		error : function(result){
			console.log(result);
			
			alert("파일을 삭제하지 못하였습니다.");
		}
	});
}

function fileUpload(fis)
{
	const img = document.getElementById('detailBookImg');
	if(img != null)
	{
		img.remove();
	}
	
	
	var str = fis.value;
	
	//이미지 존재시 삭제
	if($(".imgDeleteBtn").length > 0){
		deleteFile();
	}
	
	let formData = new FormData();
	let fileList = fis.files;
	let fileObj  = fileList[0];
	
	formData.append("uploadFile",fileObj);
	
	console.log("수정 이미지 정보 : "+formData);
	
	for (let key of formData.keys()) {
		console.log(key, ":", formData.get(key));
	}
	
	$.ajax({
		url: '/book/uploadImg',
    	processData : false,
    	contentType : false,
    	data : formData,
    	type : 'POST',
    	dataType : 'json',
    	success : function(result){
    		console.log(result);
    		showUploadImage(result);
    	},
    	error : function(result){
    		alert("이미지 파일이 아닙니다.");
    	}
	});
		
	if(!fileCheck(fileObj.name, fileObj.size)){
		return false;
	}
}

//이미지 출력
function showUploadImage(uploadResultArr){
	
	//전달받은 데이터 검증
	if(!uploadResultArr || uploadResultArr.length == 0){return}
	
	let uploadResult = $(".imgArea");
	
	let obj = uploadResultArr[0];
	
	let str = "";
	
	let fileCallPath = encodeURIComponent(obj.img_path + "/s_" + obj.img_uuid + "_" + obj.img_name);
	
	str += "<div id ='result_card'>";
	str +="<img src='/book/display?fileName="+fileCallPath+"'>";
	str +="<div class='imgDeleteBtn' onclick='deleteImg()' data-file='"+fileCallPath+"'>x</div>";
	str +="<input type='hidden' id='img_name' value='"+obj.img_name+"'>";
	str +="<input type='hidden' id='img_uuid' value='"+obj.img_uuid+"'>";
	str +="<input type='hidden' id='img_path' value='"+obj.img_path+"'>"
	str +="</div>";
	
	
	console.log("이미지 관련 데이터  : "+str);
	uploadResult.append(str);
}

function deleteFile()
{
	let targetFile = $(".imgDeleteBtn").data("file");
	
	let targetDiv = $("#result_card");
	
	$.ajax({
		url:'/book/deleteFile',
		data : {fileName : targetFile},
		dataType : 'text',
		type : 'POST',
		success : function(result){
			console.log(result);
			targetDiv.remove();
			$("input[type='file']").val("");
		},
		error : function(result){
			console.log(result);
			alert("파일을 삭제하지 못하였습니다.");
		}
	});
}

function deleteImg()
{
	alert("이미지 삭제");
	deleteFile();
}
	
	

