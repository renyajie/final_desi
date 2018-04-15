package com.ryj.yuyue.service;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ryj.yuyue.utils.ConstantLiteral;


/**
 * 保存瑜伽馆的上传图片
 * @author Thor
 *
 */
@Service
public class TransferService {

	private static final Logger logger = 
			LoggerFactory.getLogger(TransferService.class);
	
	/**
	 * 保存瑜伽馆图片
	 * @param placeId 场馆编号
	 * @param image 图片文件
	 * @return 前端请求图像路径
	 */
	public String addPlaceImage(Integer placeId, MultipartFile image) {
		String dicPath = ConstantLiteral.FILE_PLACE_IMAGE + File.separator + placeId;
		String fileName = image.getOriginalFilename();
		saveFile(dicPath, fileName, image);
		//实验室的图片由于需要映射直接显示，所以这里返回虚拟路径
		return ConstantLiteral.FILE_PLACE_IMAGE_FRONT_END + "/"
				+ placeId + "/" + fileName;
	}
	
	/**
	 * 保存文件至服务器
	 * @param dicPath 文件目录路径
	 * @param fileName 文件名
	 * @param file 保存文件
	 */
	public void saveFile(String dicPath, String fileName, MultipartFile file) {
		String fullPath = dicPath + File.separator + fileName;
		File filePath = new File(dicPath, fileName);
		// 判断路径是否存在
		if (!filePath.getParentFile().exists()) {
			filePath.getParentFile().mkdirs();
		}
		// 保存文件
		try {
			file.transferTo(new File(fullPath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("upload path: " + fullPath);
	}

}
