package com.atos.coderank.components;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
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
	 * @see <a href=
	 *      "http://en.wikipedia.org/wiki/ISO_8601#Combined_date_and_time_representations">Combined
	 *      Date and Time Representations</a>
	 */
	public static final String ISO_8601_24H_FULL_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

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
			LOG.error("Error al leer la imagen - " + e.getMessage());
		}
		return new byte[0];
	}

	/**
	 * Parses an array of strings to csv
	 * 
	 * @param arr
	 * @return
	 */
	public String stringArrayToCsv(String[] arr) {
		StringBuilder csv = new StringBuilder();

		for (int i = 0; i < arr.length; i++)
			if (i == arr.length - 1)
				csv.append(arr[i]);
			else
				csv.append(arr[i] + ",");

		return csv.toString();
	}

	/**
	 * Parses an list of strings to csv
	 * 
	 * @param list
	 * @return
	 */
	public String stringListToCsv(List<String> list) {
		StringBuilder csv = new StringBuilder();

		for (int i = 0; i < list.size(); i++)
			if (i == list.size() - 1)
				csv.append(list.get(i));
			else
				csv.append(list.get(i) + ",");

		return csv.toString();
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
				keys.add(key.replace(",", ""));

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
		LOG.info("Parsing date -> " + date);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
		Date d = null;
		try {
			d = df.parse(date);
		} catch (ParseException e) {
			LOG.error("Cannot parse date " + e.getMessage());
		}

		return d;
	}
}
