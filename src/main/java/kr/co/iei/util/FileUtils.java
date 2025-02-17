package kr.co.iei.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FileUtils {

	public String upload(String savepath, MultipartFile file) {
		//원본 파일명 추출 -> ice2.png
		String filename = file.getOriginalFilename();
		//ice1		.png로 분리
		//원본 파일명에서 시작부터 제일 뒤에 있는 . 앞까지를 문자열로 가져옴 -> ice2
		String onlyFilename = filename.substring(0, filename.lastIndexOf("."));
		//원본 파일명에서 제일 뒤에 있는 .부터 끝까지를 문자열로 가져옴 -> .png
		String extention = filename.substring(filename.lastIndexOf("."));
		//실제로 업로드할 파일명을 저장한 변수
		String filepath = null;
		//파일명이 중복되면 증가 시키면서 뒤에 붙일 변수
		int count = 0;
		//파일명이 중복되지 않을 때까지 반복해서 수행
		while(true) {
			if(count == 0) {
				filepath = onlyFilename+extention;
			}else {
				filepath = onlyFilename+"_"+count+extention;
			}
			//위에서 만든 파일명이 사용중인지 체크
			File checkFile = new File(savepath+filepath);
			//해당 파일명으로 업로드 폴더에 파일이름이 존재하지 않으면 반복문 종료
			if(!checkFile.exists()) {
				break;
			}
			count++;
		}
		//파일명 중복체크 끝 -> 내가 업로드할 파일명이 결정 -> 업로드
		//중복체크가 끝난 파일명으로 업로드
		File uploadFile = new File(savepath+filepath);
		try {
			//파일 업로드
			file.transferTo(uploadFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filepath;
	}

	public void downloadFile(String savepath, String fileName, String filePath, HttpServletResponse response) {
		String downFile = savepath+filePath;
		try {
			//첨부파일을 현재 서버 프로그램으로 읽어오기 위한 주스트림 생성
			FileInputStream fis = new FileInputStream(downFile);
			//속도개선을 위한 보조스트림 생성
			BufferedInputStream bis = new BufferedInputStream(fis);
			//읽어온 파일을 사용자에게 내보낼 주스트림 생성 -> response 객체 내부에 존재
			ServletOutputStream sos = response.getOutputStream();	
			//속도개선을 위한 보조스트림 생성
			BufferedOutputStream bos = new BufferedOutputStream(sos);
			//다운로드 할 파일이름 처리(사용자가 받을 파일이름)
			String resFilename = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
			//파일 다운로드를 위한 HTTP Header 설정(응답형식/파일이름 지정)
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachement;fileName="+resFilename);
			//파일을 읽어서 클라이언트에게 전송
			while(true) {
				int read = bis.read();
				if(read != -1) {				
					bos.write(read);
				}else {
					break;
				}
			}
			bos.close();
			bis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}