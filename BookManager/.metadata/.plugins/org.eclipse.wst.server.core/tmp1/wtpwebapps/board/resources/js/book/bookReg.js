$("input[type='file']").on("change",function(e){
	
	//이미지 존재시 삭제
	if($(".imgDeleteBtn").length > 0){
		deleteFile();
	}
	
	let formData = new FormData();
	let fileInput = $('input[name="uploadFile"]');
	let fileList = fileInput[0].files;
	let fileObj  = fileList[0];
	
	formData.append("uploadFile",fileObj);
	
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

function regData()
{
	var select = document.getElementById("book_category");
	var option = select.options[select.selectedIndex];
	var today = new Date();
	var book_reg = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
	
	var book_title = $("#book_title").val();
	var book_category = option.value;
	var book_author = $("#book_author").val();
	var book_quantity = $("#book_quantity").val();
	var book_price = $("#book_price").val();
	var book_content = $("#book_content").val();
	var img_name = $("#img_name").val();
	var img_path = $("#img_path").val();
	var img_uuid = $("#img_uuid").val();
	
	var book_img = new Array();

	$.ajax({
		url: '/book/bookReg.do',
		type : 'POST',
    	dataType : 'text',
    	contentType : "application/x-www-form-urlencoded; charset=UTF-8",
    	data :{"book_title":book_title, 
    			"book_category":book_category, 
    			"book_author":book_author, 
    			"book_reg":book_reg,
    			"book_quantity":book_quantity,
    			"book_price":book_price,
    			"book_content":book_content,
    			"img_name":img_name,
    			"img_path":img_path,
    			"img_uuid":img_uuid
    	},
    	success: function(){
    		alert("책 등록 성공");
    		location.href="list";
    	}
	});
	
}

//이미지 출력
function showUploadImage(uploadResultArr){
	
	//전달받은 데이터 검증
	if(!uploadResultArr || uploadResultArr.length == 0){return}
	
	let uploadResult = $("#uploadResult");
	
	let obj = uploadResultArr[0];
	
	let str = "";
	
	let fileCallPath = encodeURIComponent(obj.img_path + "/s_" + obj.img_uuid + "_" + obj.img_name);
	
	
	str += "<div id ='result_card'>";
	str +="<img src='/book/display?fileName="+fileCallPath+"'>";
	str +="<div class='imgDeleteBtn' data-file='"+fileCallPath+"'>x</div>";
	str +="<input type='hidden' id='img_name' value='"+obj.img_name+"'>";
	str +="<input type='hidden' id='img_uuid' value='"+obj.img_uuid+"'>";
	str +="<input type='hidden' id='img_path' value='"+obj.img_path+"'>"
	str +="</div>";
	
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

$("#uploadResult").on("click",".imgDeleteBtn", function(e){
	
	deleteFile();
	
});

