package it.pkg;

public class BPMUtils {
	public static String utf8truncate(String input, int length) {
		
		if(null!=input) {
		
		  StringBuffer result = new StringBuffer(length);
		  int resultlen = 0;
		  for (int i = 0; i < input.length(); i++) {
		    char c = input.charAt(i);
		    int charlen = 0;
		    if (c <= 0x7f) {
		      charlen = 1;
		    } else if (c <= 0x7ff) {
		      charlen = 2;
		    } else if (c <= 0xd7ff) {
		      charlen = 3;
		    } else if (c <= 0xdbff) {
		      charlen = 4;
		    } else if (c <= 0xdfff) {
		      charlen = 0;
		    } else if (c <= 0xffff) {
		      charlen = 3;
		    }
		    if (resultlen + charlen > length) {
		      break;
		    }
		    result.append(c);
		    resultlen += charlen;
		  }
		  return result.toString();
		}
	return "";
}}
