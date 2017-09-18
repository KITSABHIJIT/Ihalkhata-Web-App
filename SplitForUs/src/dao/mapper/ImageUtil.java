package dao.mapper;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import test.Main;

public class ImageUtil {
	public static File getFileFromBytes(byte[] byteArray, String userId)
			throws IOException {
		File file = File.createTempFile(userId + ".png", ".tmp");
		BufferedOutputStream bos = null;
		try {
			// create an object of FileOutputStream
			FileOutputStream fos = new FileOutputStream(file);
			// create an object of BufferedOutputStream
			bos = new BufferedOutputStream(fos);
			bos.write(byteArray);
			// System.out.println("File written");

		} catch (FileNotFoundException fnfe) {
			System.out.println("Specified file not found" + fnfe);
		} catch (IOException ioe) {
			System.out.println("Error while writing file" + ioe);
		} finally {
			if (bos != null) {
				try {

					// flush the BufferedOutputStream
					bos.flush();
					// close the BufferedOutputStream
					bos.close();
				} catch (Exception e) {
				}
			}
		}
		return file;

	}

	public static InputStream getInputStreamFromBytes(byte[] byteArray)
			throws IOException {
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(byteArray);

		} catch (Exception ioe) {
			System.out.println("Error while writing file" + ioe);
		}
		return is;

	}

	public static byte[] getBytesFromFilegetBytesFromFile(File file)
			throws IOException {
		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		// You cannot create an array using a long type.
		// It needs to be an int type.
		// Before converting to an int type, check
		// to ensure that file is not larger than Integer.MAX_VALUE.
		if (length > Integer.MAX_VALUE) {
			// File is too large
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}

	public static void main(String[] args) throws SQLException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		JdbcTemplate jt1 = new JdbcTemplate(Main.builtDatasource(dataSource));
		File image = null;
		try {
			image = new File("C:/Users/Abhijit/USER_DATA/abhijit/abhijit.png");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileInputStream fis;

		try {
			// Blob blob = jt1.getDataSource().getConnection().createBlob();
			// blob.setBytes(1, FileToByteArray.getBytesFromFile(image));

			PreparedStatement pstmt = null;

			fis = new FileInputStream(image);

			pstmt = jt1
					.getDataSource()
					.getConnection()
					.prepareStatement(
							"update USER_RECORD set image=?");

			pstmt.setBinaryStream(1, (InputStream) fis, (int) (image.length()));

			int i = pstmt.executeUpdate();

			if (i > 0) {
				System.out.println("Uploaded successfully !");
			} else {
				System.out.println("unsucessfull to upload image.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
