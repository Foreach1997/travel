package com.bs.travel.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.bs.travel.common.PictureResult;
import com.bs.travel.service.PictureService;
import com.bs.travel.util.FtpUtil;
import com.bs.travel.util.IDUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class PictureServiceImpl implements PictureService {

	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	@Value("${FILI_UPLOAD_PATH}")
	private String FILI_UPLOAD_PATH;
	@Value("${FTP_SERVER_IP}")
	private String FTP_SERVER_IP;
	@Value("${FTP_SERVER_PORT}")
	private Integer FTP_SERVER_PORT;
	@Value("${FTP_SERVER_USERNAME}")
	private String FTP_SERVER_USERNAME;
	@Value("${FTP_SERVER_PASSWORD}")
	private String FTP_SERVER_PASSWORD;

	@Override
	public PictureResult uploadFile(MultipartFile uploadFile) throws Exception {

		// 上传文件功能实现
		String path = savePicture(uploadFile);
		// 回显
		PictureResult result = new PictureResult(0,path);
		return result;
	}

	private String savePicture(MultipartFile uploadFile) {
		String path = "D:/apache-tomcat-8.5.50-file/webapps/file";
		String src = "";
		if (!uploadFile.isEmpty()) {
			String srcName =   UUID.randomUUID()+uploadFile.getOriginalFilename();
			File newPath = new File(path, srcName);
			if (!uploadFile.isEmpty()){
				try {
					InputStream in = uploadFile.getInputStream();
					FileOutputStream out = new FileOutputStream(newPath);
					byte buffer[] = new byte[1024];
					int len = 0;
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			src = "http://localhost:8081/file/"+srcName;
		}
		return src;
	}

//	private String savePicture(MultipartFile uploadFile) {
//		String result = null;
//		try {
//			// 上传文件功能实现
//			// 判断文件是否为空
//			if (uploadFile.isEmpty())
//				return null;
//			// 上传文件以日期为单位分开存放，可以提高图片的查询速度
//			String filePath = "/" + new SimpleDateFormat("yyyy").format(new Date()) + "/"
//					+ new SimpleDateFormat("MM").format(new Date()) + "/"
//					+ new SimpleDateFormat("dd").format(new Date());
//
//			// 取原始文件名
//			String originalFilename = uploadFile.getOriginalFilename();
//			// 新文件名
//			String newFileName = IDUtils.genImageName() + originalFilename.substring(originalFilename.lastIndexOf("."));
//			// 转存文件，上传到ftp服务器
//			FtpUtil.uploadFile(FTP_SERVER_IP, FTP_SERVER_PORT, FTP_SERVER_USERNAME, FTP_SERVER_PASSWORD,
//					FILI_UPLOAD_PATH, filePath, newFileName, uploadFile.getInputStream());
//			result = filePath + "/" + newFileName;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return result;
//	}

}
