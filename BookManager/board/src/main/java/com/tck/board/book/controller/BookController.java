package com.tck.board.book.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.tck.board.book.model.BookDTO;
import com.tck.board.book.service.BookService;
import com.tck.board.img.model.ImgDTO;
import com.tck.board.model.WrapperVO;

import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/book/*")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@RequestMapping("/list")
	public String getBookList(){
		
		return "/book/bookList";
	}
	
	@RequestMapping(value="/list.do",method=RequestMethod.POST,produces="application/text;charset=utf-8")
	@ResponseBody
	public String selectList(@ModelAttribute BookDTO bookDTO) throws Exception
	{
		List<BookDTO> book = bookService.getBookList();
		
		for(int i=0; i<book.size(); i++)
		{
			ImgDTO imgDTO = bookService.getImgList(book.get(i).getBook_code());
			System.out.println("book_code : "+book.get(i).getBook_code());
			System.out.println("이미지 정보 : "+imgDTO);
			book.get(i).setBook_img(imgDTO);
			String fileCallPath = URLEncoder.encode(imgDTO.getImg_path() + "/" + imgDTO.getImg_uuid() + "_" + imgDTO.getImg_name(), "UTF-8");
			book.get(i).setImg("<img class='bookImg' name='bookImg' src='/book/display?fileName=" + fileCallPath + "'>");	
		}
		
		
		System.out.println("책 목록 : "+book);
		Gson gson = new Gson();
		WrapperVO rntVO = new WrapperVO();
		rntVO.setAaData(book);
		String jsonString = gson.toJson(rntVO);
		
		
		System.out.println("JSON 데이터 : "+jsonString);
		
		return jsonString;
	}
	
	@RequestMapping(value="/bookReg",method=RequestMethod.GET,produces="application/text;charset=utf-8")
	public String bookRegPage()
	{
		return "/book/bookReg";
	}

	@PostMapping(value="/uploadImg",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ImgDTO>> insertBook(MultipartFile uploadFile){
		
		//이미지 파일 체크
		File checkFile = new File(uploadFile.getOriginalFilename());
		String type=null;
		
		try {
			type = Files.probeContentType(checkFile.toPath());
			System.out.println("MIME TYPE : "+type);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(!type.startsWith("image")){
			List<ImgDTO> list = null;
			
			return new ResponseEntity<List<ImgDTO>>(list,HttpStatus.BAD_REQUEST);
		}
		
		
		String uploadFolder = "C:\\upload";
		
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		String datePath = str.replace("-",File.separator);
		
		File uploadPath = new File(uploadFolder,datePath);
		
		if(uploadPath.exists() == false)
		{
			uploadPath.mkdirs();
		}
	
		
		List<ImgDTO> imgList = new ArrayList<ImgDTO>();
		
		ImgDTO imgDto = new ImgDTO();
		
		
		String uploadFileName = uploadFile.getOriginalFilename();
		
		imgDto.setImg_name(uploadFileName);
		imgDto.setImg_path(datePath);
		
		String uuid = UUID.randomUUID().toString();
		imgDto.setImg_uuid(uuid);
		
		uploadFileName = uuid+"_"+uploadFileName;
		
		File saveFile = new File(uploadPath, uploadFileName);
		
		try{
			uploadFile.transferTo(saveFile);
			
			File thumbnailFile = new File(uploadPath,"s_"+uploadFileName);
			
			BufferedImage bo_image = ImageIO.read(saveFile);
			
			double ratio = 3;
			
			int width = (int) (bo_image.getWidth() / ratio);
			int height = (int) (bo_image.getHeight() / ratio);
			
			Thumbnails.of(saveFile)
			.size(width, height)
			.toFile(thumbnailFile);
			
			BufferedImage bt_image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
			
			Graphics2D graphic = bt_image.createGraphics();
			
			graphic.drawImage(bo_image,0,0,300,500,null);
			
			ImageIO.write(bt_image, "jpg", thumbnailFile);
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
		imgList.add(imgDto);
		
		ResponseEntity<List<ImgDTO>> result = new ResponseEntity<List<ImgDTO>>(imgList, HttpStatus.OK);
		
		return result;
			
	}
	
	@RequestMapping(value="/bookReg.do", method = RequestMethod.POST,produces="application/text;charset=utf-8")
	@ResponseBody
	public void insertBook(BookDTO bookDTO, String img_name, String img_path, String img_uuid){
		System.out.println("데이터 삽입");
		ImgDTO imgDTO = new ImgDTO();
		imgDTO.setImg_name(img_name);
		imgDTO.setImg_path(img_path);
		imgDTO.setImg_uuid(img_uuid);
		
		bookDTO.setBook_img(imgDTO);
			
		bookService.insertBook(bookDTO);
		
		int book_code = bookService.getMaxBookCode();
		
		imgDTO.setBook_code(book_code);
		
		bookService.insertImg(imgDTO);
	}
	
	@RequestMapping(value="/detail",method=RequestMethod.GET,produces="application/text;charset=utf-8")
	public String detailBook(HttpServletRequest request,Model model) throws Exception
	{
		int book_code = Integer.parseInt(request.getParameter("book_code"));
		
		BookDTO bookDTO = bookService.getBookDetail(book_code);
		
		ImgDTO imgDTO = bookService.getImgList(book_code);
		
		String fileCallPath = URLEncoder.encode(imgDTO.getImg_path() + "/" + imgDTO.getImg_uuid() + "_" + imgDTO.getImg_name(), "UTF-8");
		
		model.addAttribute("bookDTO",bookDTO);
		model.addAttribute("img",fileCallPath);
		model.addAttribute("imgList",imgDTO);
	
		return "/book/bookDetail";
	}
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName)
	{
		System.out.println("getImage()....."+fileName);
		
		File file = new File("c:\\upload\\"+fileName);
		
		ResponseEntity<byte[]> result = null;
		
		try{
			
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName){
		
		File file = null;
		
		try{
			
			//썸네일 파일 삭제
			file = new File("c:\\upload\\"+URLDecoder.decode(fileName,"UTF-8"));
			
			file.delete();
			
			//원본 파일 삭제
			String originFileName = file.getAbsolutePath().replace("s_", "");
			
			file = new File(originFileName);
			
			file.delete();
			
		} catch(Exception e)
		{
			e.printStackTrace();
			
			return new ResponseEntity<String>("fail",HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	@PostMapping("/deleteBook")
	@ResponseBody
	public void deleteBook(int book_code)
	{
		bookService.deleteBoook(book_code);
	}
	
	@PostMapping("/updateBook")
	@ResponseBody
	public void updateBook(BookDTO bookDTO)
	{
		bookService.updateBook(bookDTO);
	}
	
	/*@PostMapping(value="/update_uploadImg",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ImgDTO>> updateBook(MultipartFile uploadFile){
		
		//이미지 파일 체크
		File checkFile = new File(uploadFile.getOriginalFilename());
		String type=null;
		
		try {
			type = Files.probeContentType(checkFile.toPath());
			System.out.println("MIME TYPE : "+type);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(!type.startsWith("image")){
			List<ImgDTO> list = null;
			
			return new ResponseEntity<List<ImgDTO>>(list,HttpStatus.BAD_REQUEST);
		}
		
		
		String uploadFolder = "C:\\upload\\update";
		
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		String datePath = str.replace("-",File.separator);
		
		File uploadPath = new File(uploadFolder,datePath);
		
		if(uploadPath.exists() == false)
		{
			uploadPath.mkdirs();
		}
	
		
		List<ImgDTO> imgList = new ArrayList<ImgDTO>();
		
		ImgDTO imgDto = new ImgDTO();
		
		
		String uploadFileName = uploadFile.getOriginalFilename();
		
		imgDto.setImg_name(uploadFileName);
		imgDto.setImg_path(datePath);
		
		String uuid = UUID.randomUUID().toString();
		imgDto.setImg_uuid(uuid);
		
		uploadFileName = uuid+"_"+uploadFileName;
		
		File saveFile = new File(uploadPath, uploadFileName);
		
		try{
			uploadFile.transferTo(saveFile);
			
			File thumbnailFile = new File(uploadPath,"s_"+uploadFileName);
			
			BufferedImage bo_image = ImageIO.read(saveFile);
			
			double ratio = 3;
			
			int width = (int) (bo_image.getWidth() / ratio);
			int height = (int) (bo_image.getHeight() / ratio);
			
			Thumbnails.of(saveFile)
			.size(width, height)
			.toFile(thumbnailFile);
			
			BufferedImage bt_image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
			
			Graphics2D graphic = bt_image.createGraphics();
			
			graphic.drawImage(bo_image,0,0,300,500,null);
			
			ImageIO.write(bt_image, "jpg", thumbnailFile);
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
		imgList.add(imgDto);
		
		ResponseEntity<List<ImgDTO>> result = new ResponseEntity<List<ImgDTO>>(imgList, HttpStatus.OK);
		
		return result;
			
	}*/
	
	/*@PostMapping("/deleteUpdateFile")
	public ResponseEntity<String> deleteUpdateFile(String fileName){
		
		File file = null;
		
		try{
			
			//썸네일 파일 삭제
			file = new File("c:\\upload\\update\\"+URLDecoder.decode(fileName,"UTF-8"));
			
			file.delete();
			
			//원본 파일 삭제
			String originFileName = file.getAbsolutePath().replace("s_", "");
			
			file = new File(originFileName);
			
			file.delete();
			
		} catch(Exception e)
		{
			e.printStackTrace();
			
			return new ResponseEntity<String>("fail",HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}*/
	
	@PostMapping("/updateImg")
	@ResponseBody
	public void updateImg(ImgDTO imgDTO){
		bookService.updateImg(imgDTO);
	}
	
}
