package unknowncyberplugin;

import ghidra.program.model.address.Address;
import ghidra.program.model.listing.Program;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.DatatypeConverter;

import com.unknowncyber.magic.model.FilePipeline;

import ghidra.util.Msg;

public class Helpers {

  private Helpers() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  /**
   * Hashes a given file according to the specifid algorithm.
   * 
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
   * Converts an unformatted hex value into a signed decimal via 2's complement.
   *   Can only handle hex values of length 31 or less, otherwise returns the original value.
   * 
   * Leverages Java's handling of num-to-num casting to do the calculation.  This is
   *   apparently intentional and expected behavior.
   */
  public static String twosComplement(String hex) {
    if (hex.length() < 4) {
      return Byte.toString(Short.valueOf(hex, 16).byteValue());
    } else if (hex.length() < 8) {
      return Short.toString(Integer.valueOf(hex, 16).shortValue());
    } else if (hex.length() < 16) {
      return Integer.toString(Long.valueOf(hex, 16).intValue());
    } else if (hex.length() < 32) {
      long bigint = new BigInteger(hex, 16).longValue();
      return Long.toString(bigint);
    } else {
      return hex;
    }
  }

  /**
   * Converts a hex value to a decimal directly.  Alternative to two's
   *   complement to troubleshoot certain specific overflow issues.
   */
  public static String convertToDecimal(String hex) {
    return Long.toString(Long.parseLong(hex, 16));
  }

  /** 
   * This function iterates from the end of a string address, accepting all
   *   hex-acceptable values.  If no illegal characters are detected, the
   *   original string is returned.  If a non-hex value is reached, the hex
   *   string from that point forward (without the illegal character) will
   *   be returned.
   * It also has an escape hatch when it detects that a string is either wholly
   *   hex characters, or matches 0x... hex format.
   * There appear to be a handful of cases where an address is not
   *   formatted as either a hex value.  Currently known exceptions include:
   *   - ram:012abc
   */
  public static String cleanAddress(String address) {
    address = address.toLowerCase();

    // "No cleaning needed" escape hatch
    if (address.matches("^[0-9a-f]+$")) {
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
        // Edge case escape hatch.  If the last element of a string is not hex-legal,
        //   return the original address as a fallback.
        if (i == address.length()) {
          return address;
        }
        // Else, return the trailing hex-legal value within the string
        return address.substring(i + 1);
      }
    }

    // Fallback return, necessary for compilation but should never be needed
    return address;
  }

  /**
   * Recursively searches a String value for formatted hexadecimal matches and converts them to decimal values
   * Expected hex formats include 0x... and ...h 
   * Unformatted hex values are returned directly without attempting to recurse
   */
  public static String hexToDecimal(String input, boolean useTwos) {
    Matcher xMatcher = Pattern.compile("0x[0-9a-fA-F]+").matcher(input);
    Matcher hMatcher = Pattern.compile("[0-9a-fA-F]+h").matcher(input);
    Matcher matcher = Pattern.compile("^[0-9a-fA-F]+$").matcher(input);

    if (xMatcher.find()) {
      // Extract the first hex value, removing the 0x prefix
      String hex = xMatcher.group().substring(2);

      String result;
      if (useTwos) {
        result = xMatcher.replaceFirst(twosComplement(hex));
      } else {
        result = xMatcher.replaceFirst(convertToDecimal(hex));
      }
      

      if (xMatcher.find() || hMatcher.find()) {
        // If there are more matches, recurse for next potential match
        return hexToDecimal(result, useTwos);
      }

      // Else return
      return result;

    } else if (hMatcher.find()) {
      // Extract the first hex value, removing the h suffix
      String hex = hMatcher.group().substring(0, hMatcher.group().length() - 1);

      String result;
      if (useTwos) {
        result = hMatcher.replaceFirst(twosComplement(hex));
      } else {
        result = hMatcher.replaceFirst(convertToDecimal(hex));
      }

      if (hMatcher.find()) {
        // If there are more matches, recurse for next potential match
        return hexToDecimal(result, useTwos);
      }

      // Else return
      return result;

    } else if (matcher.find()) {
      // If the string is just a full hex value with no formatting, convert and return immediately
      if (useTwos) {
        return twosComplement(matcher.group());
      } else {
        return convertToDecimal(matcher.group());
      }
    }

    // If no match is found, return original input
    return input;
  }

  /**
   * Convenience function to cut down on long lines and function calling spam
   */
  public static String formatEA(Address input) {
    return hexToDecimal(cleanAddress(input.toString()), false);
  }

  /**
   * Determines whether a program is 32 or 64 bit by way of its pointer sizes.
   *   Returns either "32" or "64" for each, or returns null for failure to determine.
   * 
   * Note:  This is a dummy function right now, mostly put in place to document how
   *   to get architecture.  This is subject to change as needed, or absorption into
   *   the main body of code.
   */
  public static int getArchitecture(Program program) {
    int pointer = program.getLanguage().getDefaultSpace().getPointerSize();
    if (pointer == 4) return 32;
    if (pointer == 8) return 64;
    return -1;
  }

  /**
   * Maps the FilePipeline response objects to strings.
   * 
   * @param pipeline
   * @return
   */
  public static Map<String, String> parsePipelines(FilePipeline pipeline){
    Map<String, String> pipelines = new LinkedHashMap<>();

    pipelines.put("dashboard_campaign", pipeline.getDashboardCampaign());
    pipelines.put("Label Inference", pipeline.getDashboardReport());
    pipelines.put("Ioc Extraction", pipeline.getIocHandler());
    pipelines.put("Yara Generation", pipeline.getProcHashSignatures());
    pipelines.put("Similarity Matching", pipeline.getSimilarityComputation());
    pipelines.put("Genomic Juicing", pipeline.getSrlJuice());
    pipelines.put("AV Scan Report", pipeline.getSrlScanners());
    pipelines.put("Unpacking", pipeline.getSrlUnpacker());
    pipelines.put("variant_hash_signatures", pipeline.getVariantHashSignatures());
    pipelines.put("Filetype Discovery", pipeline.getWebRequestHandler());

    Iterator<Entry<String, String>> iter = pipelines.entrySet().iterator();
    while(iter.hasNext()) {
      Entry<String, String> entry = iter.next();
      if(entry.getValue() == null){
        iter.remove();
      }
    }

    return pipelines;
  }

  /**
   * Convenience function to wrap Address.subtract(Address) and clarify
   * what's being done when this is called
   */
  public static long getRelativeAddress(Address address, Address base) {
    return address.subtract(base);
  }
}