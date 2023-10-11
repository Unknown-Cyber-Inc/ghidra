package unknowncyberplugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.DatatypeConverter;

public class helpers {
  /**
   * Note:  There are a handful of use cases where this will fail:
   * - The original file is no longer on the host's system
   *   - This might also fail if the original file has been moved from where
   *     it was first imported from; I have not tested for this edge case
   * - The file was imported from an archive through the "Batch" option
   */
  public static String hashFile(File file, String algo) throws IOException, NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance(algo);
    FileInputStream fis = new FileInputStream(file);
    byte[] buffer = new byte[1024];
    int bytesRead;

    while ((bytesRead = fis.read(buffer)) != -1) {
      digest.update(buffer, 0, bytesRead);
    }

    fis.close();

    return DatatypeConverter.printHexBinary(digest.digest());
  }

  /** 
   * There appear to be a handful of cases where an address is not
   *   formatted as either a hex value.  Currently known exceptions include:
   *   - ram:012abc
   * This function iterates from the end of a string address, accepting all
   *   hex-acceptable values.  If the start of the string is reached, the
   *   original string is returned.  If a non-hex value is reached, the hex
   *   string from that point forward (without the illegal character) will
   *   be returned.
   * It also has an escape hatch when it detects that a string is either wholly
   *   hex characters, or matches 0x... hex format
   */
  public static String cleanAddress(String address) {
    address = address.toLowerCase();

    // "No cleaning needed" escape hatch
    if (address.matches("[0-9a-f]+") || address.matches("0x[0-9a-f]+")) {
      return address;
    }

    ArrayList<Character> allowedChars = new ArrayList<Character>();
    allowedChars.add('0');
    allowedChars.add('1');
    allowedChars.add('2');
    allowedChars.add('3');
    allowedChars.add('4');
    allowedChars.add('5');
    allowedChars.add('6');
    allowedChars.add('7');
    allowedChars.add('8');
    allowedChars.add('9');
    allowedChars.add('a');
    allowedChars.add('b');
    allowedChars.add('c');
    allowedChars.add('d');
    allowedChars.add('e');
    allowedChars.add('f');

    for (int i = address.length() - 1; i >= 0; i--) {
      if (!allowedChars.contains(address.charAt(i))) {
        return address.substring(i + 1);
      }
    }

    // Fallback return, necessary for compilation but should never be needed
    return address;
  }

  /**
   * Recursively searches a String value for hexadecimal matches and converts them to decimal values
   *   - Expected hex format: 0x...
   */
  public static String hexToDecimal(String input) {
    Matcher matcher = Pattern.compile("0x[0-9a-fA-F]+").matcher(input);
    if (matcher.find()) {
      // Extract the first hex value, removing the 0x prefix
      String hex = matcher.group().substring(2);

      String decimal = Integer.toString(Integer.parseInt(hex, 16));

      String result = matcher.replaceFirst(decimal);

      // Recurse for next potential match
      return hexToDecimal(result);
    }

    // If no match is found, return original input
    return input;
  }
}
