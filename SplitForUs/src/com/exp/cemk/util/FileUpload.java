package com.exp.cemk.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.exp.cemk.constants.LoginConstants;

import dao.mapper.ImageUtil;

public class FileUpload {
	private static final Logger logger = Logger.getLogger(FileUpload.class);
	public static String uploadFile(HttpServletRequest request, String userId) {
		// start of read
		String saveFile = null;
		String serverUploadDirectory = null;
		logger.info("Util-->FileUpload-->uploadFile");
		try {
			String contentType = request.getContentType();
			if ((contentType != null)
					&& (contentType.indexOf("multipart/form-data") >= 0)) {
				DataInputStream in = new DataInputStream(
						request.getInputStream());
				int formDataLength = request.getContentLength();
				byte dataBytes[] = new byte[formDataLength];
				int byteRead = 0;
				int totalBytesRead = 0;
				while (totalBytesRead < formDataLength) {
					byteRead = in.read(dataBytes, totalBytesRead,
							formDataLength);
					totalBytesRead += byteRead;
				}
				String file = new String(dataBytes);
				saveFile = file.substring(file.indexOf("filename=\"") + 10);
				saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
				saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,
						saveFile.indexOf("\""));
				int lastIndex = contentType.lastIndexOf("=");
				String boundary = contentType.substring(lastIndex + 1,
						contentType.length());
				int pos;
				pos = file.indexOf("filename=\"");
				pos = file.indexOf("\n", pos) + 1;
				pos = file.indexOf("\n", pos) + 1;
				pos = file.indexOf("\n", pos) + 1;
				int boundaryLocation = file.indexOf(boundary, pos) - 4;
				int startPos = ((file.substring(0, pos)).getBytes()).length;
				int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;

				serverUploadDirectory = LoginConstants.diretoryName
						+ System.getProperty("file.separator") + userId;
				// Create multiple directories
				File directory = new File(serverUploadDirectory);
				System.out
						.println(" Directories Exists: " + directory.exists());

				if (!directory.exists()) {
					directory.mkdirs();
					logger.info("Directories: " + serverUploadDirectory
							+ " created");
				}

				saveFile = serverUploadDirectory
						+ System.getProperty("file.separator") + userId
						+ ".png";

				File ff = new File(saveFile);
				FileOutputStream fileOut = new FileOutputStream(ff);
				fileOut.write(dataBytes, startPos, (endPos - startPos));
				fileOut.flush();
				fileOut.close();

			}
			// //end of write
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());

		}
		return saveFile;
	}

	public static boolean copyfile(String srFile, String dtFile, String fileName) {
		logger.info("Util-->FileUpload-->copyfile");
		try {
			File source = null;
			URL defaultImage = getResource(srFile);
			try {
				source = new File(defaultImage.toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			File destination = new File(dtFile);
			File saveFileName = new File(fileName);
			if (!destination.exists()) {
				if (destination.mkdirs()) {
					saveFileName.createNewFile();
					logger.info("Directories & file: " + saveFileName
							+ " created");
				}
			}
			InputStream in = new FileInputStream(source);

			// For Append the file.
			// OutputStream out = new FileOutputStream(f2,true);

			// For Overwrite the file.
			OutputStream out = new FileOutputStream(fileName);

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
			logger.info("File copied.");
			return true;
		} catch (FileNotFoundException ex) {
			System.out
					.println(ex.getMessage() + " in the specified directory.");
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static URL getResource(String resource) {
		logger.info("Util-->FileUpload--> getResource.");
		URL url;

		// Try with the Thread Context Loader.
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		if (classLoader != null) {
			url = classLoader.getResource(resource);
			if (url != null) {
				return url;
			}
		}

		// Let's now try with the classloader that loaded this class.
		classLoader = javassist.Loader.class.getClassLoader();
		if (classLoader != null) {
			url = classLoader.getResource(resource);
			if (url != null) {
				return url;
			}
		}

		// Last ditch attempt. Get the resource from the classpath.
		return ClassLoader.getSystemResource(resource);
	}

	public static String uploadFileFirst(HttpServletRequest request,
			String userId) {
		logger.info("Util-->FileUpload--> getResource.");
		String serverUploadDirectory = LoginConstants.diretoryName
				+ System.getProperty("file.separator") + userId;
		String saveFile = serverUploadDirectory
				+ System.getProperty("file.separator") + userId + ".png";
		if (FileUpload.copyfile(LoginConstants.defaultUserImagePath,
				serverUploadDirectory, saveFile))
			return saveFile;
		else
			return null;
	}

	public static File getDefaultUserImage(String userId) {
		logger.info("Util-->FileUpload--> getDefaultUserImage.");
		File source = null;
		URL defaultImage = getResource(LoginConstants.defaultUserImagePath);
		try {
			source = new File(defaultImage.toURI());
			BufferedImage originalImage = ImageIO.read(source);
			int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
					: originalImage.getType();
			BufferedImage resizeImage = CommonUtil.resizeImage(originalImage,
					type);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(resizeImage, "png", os);
			source=ImageUtil.getFileFromBytes(os.toByteArray(),userId);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return source;

	}
	public static InputStream getDefaultUserImageInputStream() {
		logger.info("Util-->FileUpload--> getDefaultUserImageInputStream.");
		File source = null;
		InputStream is = null;
		URL defaultImage = getResource(LoginConstants.defaultUserImagePath);
		try {
			source = new File(defaultImage.toURI());
			BufferedImage originalImage = ImageIO.read(source);
			int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
					: originalImage.getType();
			BufferedImage resizeImage = CommonUtil.resizeImage(originalImage,
					type);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(resizeImage, "png", os);
			is = new ByteArrayInputStream(os.toByteArray());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return is;

	}
	public static File getUserImage(HttpServletRequest request) {
		logger.info("Util-->FileUpload--> getUserImage.");
		// start of read
		String saveFile = null;
		File userImageFile = null;
		try {
			String contentType = request.getContentType();
			if ((contentType != null)
					&& (contentType.indexOf("multipart/form-data") >= 0)) {
				DataInputStream in = new DataInputStream(
						request.getInputStream());
				int formDataLength = request.getContentLength();
				byte dataBytes[] = new byte[formDataLength];
				int byteRead = 0;
				int totalBytesRead = 0;
				while (totalBytesRead < formDataLength) {
					byteRead = in.read(dataBytes, totalBytesRead,
							formDataLength);
					totalBytesRead += byteRead;
				}
				String file = new String(dataBytes);
				saveFile = file.substring(file.indexOf("filename=\"") + 10);
				saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
				saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,
						saveFile.indexOf("\""));
				int lastIndex = contentType.lastIndexOf("=");
				String boundary = contentType.substring(lastIndex + 1,
						contentType.length());
				int pos;
				pos = file.indexOf("filename=\"");
				pos = file.indexOf("\n", pos) + 1;
				pos = file.indexOf("\n", pos) + 1;
				pos = file.indexOf("\n", pos) + 1;
				int boundaryLocation = file.indexOf(boundary, pos) - 4;
				int startPos = ((file.substring(0, pos)).getBytes()).length;
				int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;

				userImageFile = new File(saveFile);
				FileOutputStream fileOut = new FileOutputStream(userImageFile);
				fileOut.write(dataBytes, startPos, (endPos - startPos));
				fileOut.flush();
				fileOut.close();

			}
			// //end of write
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());

		}
		return userImageFile;
	}
	public static InputStream getUserImageInputStream(HttpServletRequest request) {
		logger.info("Util-->FileUpload--> getUserImageInputStream.");
		// start of read
		InputStream is = null;
		try {
			String contentType = request.getContentType();
			if ((contentType != null)
					&& (contentType.indexOf("multipart/form-data") >= 0)) {
				DataInputStream in = new DataInputStream(
						request.getInputStream());
				int formDataLength = request.getContentLength();
				byte dataBytes[] = new byte[formDataLength];
				int byteRead = 0;
				int totalBytesRead = 0;
				while (totalBytesRead < formDataLength) {
					byteRead = in.read(dataBytes, totalBytesRead,
							formDataLength);
					totalBytesRead += byteRead;
				}
				 is = new ByteArrayInputStream(dataBytes);

			}
			// //end of write
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());

		}
		return is;
	}
}
