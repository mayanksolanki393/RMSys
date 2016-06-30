package cyb.rms.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController()
@RequestMapping("/upload")
public class FileUploadController {
	@Value("${upload.location}")
	private static String location = "D:\\rms";
	
	private static Logger LOG = Logger.getLogger(FileUploadController.class);
	
	@RequestMapping(value = "/file", method = RequestMethod.POST,consumes = {"multipart/form-data"},produces="plain/text")
	public @ResponseBody String uploadFile(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				String fileLocation = location+File.separator+file.getOriginalFilename();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fileLocation)));
				stream.write(bytes);
				stream.close();
				return "You successfully uploaded " + location + " into " + fileLocation;
			} catch (Exception e) {
				return "You failed to upload " + file.getName()+ " => " + e.getMessage();
			}
		} else {
			return "You failed to upload because the file was empty.";
		}
	}
}
