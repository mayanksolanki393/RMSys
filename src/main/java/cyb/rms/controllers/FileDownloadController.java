package cyb.rms.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/download/file")
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class FileDownloadController {
	@Value("${upload.location}")
	private static String location = "D:\\rms";

	private static Logger LOG = Logger.getLogger(FileDownloadController.class);
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IAppFileService appFileService;

	@RequestMapping(method = RequestMethod.GET,path="/{fileId}")
	public void downloadFile(@PathVariable("fileId") int fileId,Authentication authentication,HttpServletResponse response) throws DaoException, IOException {

		AppFile appFile = appFileService.findAppFileById(fileId);
		User requester = userService.findUsersByUsername(authentication.getName());
		if(appFile.getProject().getUsers().contains(requester)){
			File file = new File(location+File.separator+appFile.getProject().getTitle()+File.separator+appFile.getId()+"_"+appFile.getFilename());
			if(!file.exists()){
				throw new FileNotFoundException();
			}
			else{
				FileInputStream inputStream = new FileInputStream(file);
				response.setHeader("Content-Disposition", "attachement;filename= \""+appFile.getFilename()+"\"");
				IOUtils.copy( inputStream , response.getOutputStream());
		}
		}
		else{
			throw new AccessDeniedException("You dont have enough access for this file");
		}
	}
}
