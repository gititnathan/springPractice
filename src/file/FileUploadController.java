package file;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
// Spring���� �����Ǵ� �ֵ鿡 ���� �ǵ�.

@Controller
public class FileUploadController {
	@RequestMapping(value="/fileUpload_ok.do")
	public void fileUpload(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		//���� �ޱ�
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req; // req�� ���� �ָ� ����ȯ�ؼ� mr�� ����ش�.
		MultipartFile mf = mr.getFile("filename"); // mr���� filename�� ������ �ִ� �ָ� �����ͼ� mf�� ��´�.
		String filename = mf.getOriginalFilename(); // mf���� ������ ���������� �̸��� String�� filename�� ����ش�.
		System.out.println("filename = " + filename); 
		
		// ���� ���Ͽ� ���� Ȯ��.
		if(filename == null || filename.trim().equals("")) return;
		
		// ���� ��� �����ϱ�
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/files"); // ������ �������ִ� ��.
		
		// ������ ���� ����
		File file = new File(upPath, filename);
		mf.transferTo(file);
	}
}
