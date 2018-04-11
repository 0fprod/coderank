package com.atos.coderank.components;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class UtilsComponent {

	private static final Log LOG = LogFactory.getLog(UtilsComponent.class);
	
	/**
	 * Reads a File with the given path and returns it as Byte[]
	 * 
	 * @param path
	 * @return
	 */
	public Byte[] loadImage(String path) {
		File file = new File(path);
		byte[] img_b;

		try {
			img_b = Files.readAllBytes(file.toPath());
			Byte[] img = new Byte[img_b.length];

			int i = 0;
			for (byte b : img_b)
				img[i++] = b;

			return img;
		} catch (IOException e) {
			LOG.warn("Error al leer la imagen");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Converts Byte[] to byte[]
	 * 
	 * @param bytes
	 * @return
	 */
	public byte[] parseByteArray2byteArray(Byte[] bytes) {
		byte[] b = new byte[bytes.length];

		int j = 0;
		for (Byte bb : bytes)
			b[j++] = bb.byteValue();

		return b;
	}
}
