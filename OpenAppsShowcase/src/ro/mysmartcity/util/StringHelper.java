package ro.mysmartcity.util;

public class StringHelper {

	public static boolean isValid(final String value) {
		return value != null && !value.trim().equals("");
	}
}
