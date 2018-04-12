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
	 * Reads a File with the given path and returns it as byte[]
	 * 
	 * @param path
	 * @return
	 */
	public byte[] loadImage(String path) {
		File file = new File(path);

		try {
			return Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			LOG.warn("Error al leer la imagen");
			e.printStackTrace();
		}
		return null;
	}

}
