package file;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
// Spring에서 제공되는 애들에 대한 건데.

@Controller
public class FileUploadController {
	@RequestMapping(value="/fileUpload_ok.do")
	public void fileUpload(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		//파일 받기
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req; // req로 받은 애를 형변환해서 mr에 담아준다.
		MultipartFile mf = mr.getFile("filename"); // mr에서 filename을 가지고 있는 애를 가져와서 mf에 담는다.
		String filename = mf.getOriginalFilename(); // mf에서 가져온 원본파일의 이름을 String형 filename에 담아준다.
		System.out.println("filename = " + filename); 
		
		// 받은 파일에 대한 확인.
		if(filename == null || filename.trim().equals("")) return;
		
		// 저장 경로 설정하기
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/files"); // 절대경로 지정해주는 것.
		
		// 서버에 파일 쓰기
		File file = new File(upPath, filename);
		mf.transferTo(file);
	}
}
