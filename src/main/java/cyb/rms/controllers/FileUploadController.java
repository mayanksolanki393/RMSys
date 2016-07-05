package cyb.rms.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cyb.rms.daos.IAppFileDao;
import cyb.rms.entities.AppFile;
import cyb.rms.entities.Requirement;
import cyb.rms.entities.User;
import cyb.rms.enums.RmsEnums.FileStatus;
import cyb.rms.enums.RmsEnums.FileType;
import cyb.rms.exceptions.DaoException;
import cyb.rms.services.IAppFileService;
import cyb.rms.services.IRequirementService;
import cyb.rms.services.IUserService;

@RestController()
@RequestMapping("/upload/file")
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class FileUploadController {
	@Value("${upload.location}")
	private static String location = "D:\\rms";

	private static Logger LOG = Logger.getLogger(FileUploadController.class);

	@Autowired
	IRequirementService reqService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IAppFileService appFileService;

	@RequestMapping(value = "/requirement", method = RequestMethod.POST, consumes = { "multipart/form-data" }, produces = "plain/text")
	public @ResponseBody ResponseEntity<String> uploadFile(
			@RequestParam("file") MultipartFile file,
			@RequestParam("reqId") int reqId,
			Authentication authentication) throws DaoException, IOException {

		Requirement requirement = reqService.findRequirementById(reqId);
		User user = userService.findUsersByUsername(authentication.getName());
				
		AppFile appFile = new AppFile(file.getOriginalFilename(), new Date(), FileStatus.ACTIVE, user, FileType.REQUIREMENT_FILE,requirement.getProject());
		appFileService.addAppFile(appFile);
		LOG.info("File id : "+appFile.getId());
		File uploadDirectory = new File(location + File.separator+ requirement.getProject().getTitle());
		
		if(!uploadDirectory.exists()){
			uploadDirectory.mkdir();
		}
		
		File uploadFile = new File(uploadDirectory.getAbsolutePath()+ File.separator + appFile.getId()+"_"+file.getOriginalFilename());
		byte[] bytes = file.getBytes();
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
		stream.write(bytes);
		stream.close();

		requirement.getFiles().add(appFile);
		reqService.updateRequirement(requirement);
		return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
	}
}
