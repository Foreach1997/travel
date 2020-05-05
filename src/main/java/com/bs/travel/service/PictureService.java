package com.bs.travel.service;


import com.bs.travel.common.PictureResult;
import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
	public PictureResult uploadFile(MultipartFile uploadFile) throws Exception;
}
