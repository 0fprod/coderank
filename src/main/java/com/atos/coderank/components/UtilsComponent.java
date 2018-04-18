package com.atos.coderank.components;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

	/**
	 * Parses an array of strings to csv
	 * 
	 * @param arr
	 * @return
	 */
	public String StringArrayToCsv(String[] arr) {
		String csv = "";

		for (int i = 0; i < arr.length; i++)
			if (i == arr.length - 1)
				csv += arr[i];
			else
				csv += arr[i] + ",";

		return csv;
	}

	/**
	 * Parses an list of strings to csv
	 * 
	 * @param list
	 * @return
	 */
	public String StringListToCsv(List<String> list) {
		String csv = "";

		for (int i = 0; i < list.size(); i++)
			if (i == list.size() - 1)
				csv += list.get(i);
			else
				csv += list.get(i) + ",";

		return csv;
	}

	/**
	 * Returns all the metrics keys from given domain
	 * 
	 * @param list
	 * @return
	 */
	public List<String> getAllMetricKeys(Map<String, List<String>> domain) {
		List<String> keys = new ArrayList<>();

		for (Map.Entry<String, List<String>> entry : domain.entrySet())
			for (String key : entry.getValue())
				keys.add(key);

		return keys;
	}

	/**
	 * Parses rating 1-5 to char A-E
	 * 
	 * @param number
	 * @return
	 */
	public Character parseRatingToChar(String number) {
		switch (number) {
		case "1.0":
			return 'A';
		case "2.0":
			return 'B';
		case "3.0":
			return 'C';
		case "4.0":
			return 'D';
		case "5.0":
			return 'E';
		default:
			return '-';
		}
	}

	/**
	 * String format yyyy-MM-ddThh:mm:ss+0000
	 * 
	 * @param date
	 * @return
	 */
	public Date parseStringToDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddThh:mm:ss");
		Date d = null;
		try {
			d = sdf.parse(date);
			LOG.info(d.toString());
		} catch (ParseException e) {
			LOG.error("Error parsing date " + e.getMessage());			
		}
		return d;
	}
}
