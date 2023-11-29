package api.location.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import api.location.entity.Voiture;
import api.location.repository.VoitureRepo;
import api.location.service.AgenceService;
import api.location.service.VoitureService;
import api.location.utils.Utils; 

@Service

public class VoitureServiceImpl implements VoitureService {

	@Autowired
	VoitureRepo repo;
	@Autowired
	AgenceService service;

	@Value("${ftp.server.adr}")
    String FTP_ADDRESS;
	@Value("${ftp.server.user}")
    String USER;
	@Value("${ftp.server.password}")
    String PASSWORD ;
	
	@Override
	public List<Voiture> all() { 
		return repo.findAll();
	}

	@Override
	public Voiture get(Long id) { 
		return repo.findById(id).get();
	}

	@Override
	public Voiture post(Voiture v) { 
		return repo.saveAndFlush(v);
	}

	@Override
	public Voiture put(Voiture v) { 
		return repo.saveAndFlush(v);
	}

	@Override
	public Voiture delete(Long id) { 
		Voiture v = get(id);
		repo.delete(v);
		return v;
	}

	@Override
	public List<Voiture> loadByAgence(long a) { 
		return repo.findByAgence(service.get(a));
	}
	@Override
	 public ResponseEntity<byte[]> download(long recid) {
	        String remoteFilePath = get(recid).getImgurl();//getRemoteFilePath(recid);

	        try {
	        	FTPClient ftpClient = new FTPClient();
	            ftpClient.connect(FTP_ADDRESS);
	            ftpClient.login(USER, PASSWORD);
	            ftpClient.enterLocalPassiveMode();

	            // Set binary transfer mode for binary files like images (PNG)
	            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

	            try (InputStream inputStream = ftpClient.retrieveFileStream(remoteFilePath)) {
	                if (inputStream == null) {
	                    System.out.println("Error retrieving the file!");
	                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	                }

	                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	                byte[] buffer = new byte[8192]; // Increase buffer size for better performance
	                int bytesRead;
	                while ((bytesRead = inputStream.read(buffer)) != -1) {
	                    outputStream.write(buffer, 0, bytesRead);
	                }
	                byte[] fileData = outputStream.toByteArray();
	                outputStream.close();

	                HttpHeaders headers = new HttpHeaders();
	                headers.setContentType(MediaType.IMAGE_PNG); // Set the appropriate content type for PNG images
	                headers.setContentDispositionFormData("attachment", remoteFilePath);
	                headers.setContentLength(fileData.length);
	                return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	 

	 @Override
	 public String upload(MultipartFile file, String recid) {
	     FTPClient con = new FTPClient();

	     try {
	         con.connect(FTP_ADDRESS, 21);

	         if (!FTPReply.isPositiveCompletion(con.getReplyCode())) {
	             return "Failed to connect to the FTP server.";
	         }

	         if (!con.login(USER, PASSWORD)) {
	             return "Failed to log in to the FTP server.";
	         }

	         con.enterLocalPassiveMode();
	         con.setFileType(FTP.BINARY_FILE_TYPE); // Set the file type to binary

	         String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
	         String remoteFileName = "voiture__" + recid + fileExtension;
	         if (!con.storeFile(remoteFileName, file.getInputStream())) {
	             return "Failed to upload the file.";
	         }
	         con.logout();
	         con.disconnect();

	         setImg(Long.valueOf(recid), remoteFileName);

	         return "File uploaded successfully.";
	     } catch (Exception e) {
	         e.printStackTrace();
	         return "An error occurred: " + e.getMessage();
	     }
	 }
	/* 	
		@Override
		public String upload(MultipartFile file, String recid) {
			System.err.println("voitures : " + recid); 
			
		    FTPClient con = null;
		    
		    try {
		    	System.err.println("----------------------------------------------------");
		        con = new FTPClient();
		        con.connect(FTP_ADDRESS, 21);//
		        System.out.println("reply code : " + con.getReplyString());
		        System.err.println("------------------------ server : "+ con.getDefaultPort()  +"----------------------------");
		        if (FTPReply.isPositiveCompletion(con.getReplyCode())) {
		            System.out.println("Operation success. Server reply code: " + con.getReplyCode());
		        }
		        boolean connect = con.login(USER, PASSWORD);
		        System.out.println("connected? : " + connect);
		        if (connect) {
		        	System.out.println("connected succefully");
		            con.enterLocalPassiveMode(); // important!
		            //con.setFileType();
		            //String[] t = file.getOriginalFilename().toString().split(".");
		            String t = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
		            System.err.println(t + " : length "+ file.getOriginalFilename() + " : " + t);
		            //con.changeWorkingDirectory;
		            //boolean created = con.makeDirectory("./" +Utils.getFullDate() + "/");
		            //con.changeWorkingDirectory("./" +Utils.getFullDate() );
		            //boolean createdd = con.makeDirectory("./" +Utils.getFullDay() + "/");
		            //con.changeWorkingDirectory("./" +Utils.getFullDay() );
		            //System.out.println("create  directory to : ./" +Utils.getFullDate() + "/"+Utils.getFullDay() + " : ");
		            //if(created)
		            //	System.out.println("create  directory to : ./" +Utils.getFullDate() + "/"+Utils.getFullDay() + " : " + created);
		            //else
		            //	System.out.println("change directory to : ./" +Utils.getFullDate() + "/"+Utils.getFullDay() + " : " + created);
		            		
		            boolean result = con.storeFile("voiture__"+recid + t, file.getInputStream());
		            //System.err.println(con.listDirectories("./2023_08"));
		            //con.appendFile("reclamation__"+recid+"_2" + t, file.getInputStream());
		            if (result) {
		            	//HttpServletResponse response = null;
		                System.out.println("You successfully uploaded " + result + "   "+ file.getOriginalFilename() + "!");
		                con.logout();
			            con.disconnect();
			            setImg(Long.valueOf(recid), "voiture__"+recid + t );
		                return "You successfully uploaded";
		            } else {
		            	System.out.println("Could not upload " + file.getOriginalFilename() + "!");
		            	con.logout();
		            	con.disconnect();
		            	return "Could not upload";
		            }
		            
	            	
		        }else {
		        	System.out.println("failed to connect");
		        	return "failed to connect";
		        }
		    } catch (Exception e) {
		    	System.err.println("----------------------------------------------------\n");
		    	//e.printStackTrace();
		        System.err.println(e.getCause());
		        return e.getMessage();
		    }
		}
*/
		private void setImg(Long id, String s) {
			System.err.println("changing name to : " + s);
			Voiture v = get(id);
			v.setImgurl(s);
			put(v);
		}
		
}
