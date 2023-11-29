package unknowncyberplugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;

public class Prolog {

  private Prolog() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static String[] dtype2ptr(String input) {
    /**
     * Converts a pointer-containing operand string into an array.
     * Element 0 of the array is the "clean" operand string without the pointer.
     * Element 1 of the array is the "clean" operand containing the converted pointer.
     * If a non-pointer-containing operand string is passed in, this will
     * safely return an array where both elements are the original operand string.
     */
    Map<String, String> map = new HashMap<>();
    map.put("byte ptr", "bptr");
    map.put("word ptr", "wptr");
    map.put("dword ptr", "dptr");
    map.put("qword ptr", "qptr");
    map.put("tword ptr", "tbptr");
    map.put("16-byte ptr", "b16ptr");
    map.put("xmmword ptr", "b16ptr");
    map.put("ymmword ptr", "b32ptr");
    map.put("64-byte ptr", "b64ptr");

    String operand = input.toLowerCase();
    for (Map.Entry<String, String> entry : map.entrySet()) {
      if (operand.contains(entry.getKey())) {

        // The clean operand is just the address location without the pointer, with square brackets converted to parentheses
        //   dword ptr [ESP + 0x8] -> (esp + 0x8)
        String cleanOperand = "(" + operand.substring(operand.indexOf("[") + 1, operand.indexOf("]")) + ")";

        // The full clean operand is the clean operand with the translated pointer prefixed directly to it
        //  dword ptr [ESP + 0x8] -> dptr(esp + 0x8)
        String fullCleanOperand = entry.getValue() + cleanOperand;

        return new String[]{cleanOperand, fullCleanOperand};
      }
    }
    // Fall-through case
    return new String[]{operand, operand};
  }

  // TODO: This is dummy patch code to handle oddball issues that arise with menmonics
  public static String cleanMnemonic(String input) {
    String output;

    // Specifically, this is to cleanse leading underscores that prolog can't handle,
    // now on mnemonics as well as operands
    output = input.replaceAll("^_+", "").trim();
    
    return output;
  }

  // TODO: This is dummy patch code to handle oddball issues that arise in prolog
  public static String dummyPatch(String input, String mnemonic) {
    String cleanedOp;
    
    // Specifically, this is to cleanse # symbols from operands, as this is apparently
    // something that prolog cannot handle
    cleanedOp = input.replaceAll("#+", "").trim();

    // Specifically, this is to condense mutliple negative symbols into a single one
    cleanedOp = cleanedOp.replaceAll("--+", "-").trim();

    // Specifically, this is to cleanse leading/trailing colons when Ghidra spits
    // out an operand as such.
    cleanedOp = cleanedOp.replaceAll("^:+", "").trim();
    cleanedOp = cleanedOp.replaceAll(":+$", "").trim();

    // Specifically, this is to cleanse leading underscores that prolog can't handle
    cleanedOp = cleanedOp.replaceAll("^_+", "").trim();

    // Specifically, this is to cleanse trailing exclamations that prolog can't handle
    cleanedOp = cleanedOp.replaceAll("!+$", "").trim();
    // This sub-fix handles conjoined operands
    cleanedOp = cleanedOp.replaceAll("!+,", ",").trim();

    // Specifically, this is to cleanse trailing + signs that prolog can't handle
    cleanedOp = cleanedOp.replaceAll("[+]+$", "").trim();

    // Specifically, this is to cleanse weird leading commas
    cleanedOp = cleanedOp.replaceAll("^,+", "").trim();

    // Specifically, this is to fix a weird issue in which an operand appears such
    // as "lbl 8" where the space is a problem.  Actual IDA code visible through the
    // site has it as lbl#8, but that doesn't parse so who knows what's going on.
    // This only checks for "[a-z] [0-9]" because there are legitimate cases where
    // other configurations are permissible, it seems.
    cleanedOp = cleanedOp.replaceAll("([a-z]) +([0-9])", "$1$2").trim();

    // Specifically, this cleans up some weird trailing .l (lowercase L) on operands
    // and a similar .w
    cleanedOp = cleanedOp.replaceAll("([)])[.][lw](,|$)", "$1$2").trim();

    // Specifically, this is to guesstimate pointers in architectures that Ghidra is
    // unable to discern pointers for, which also produces prolog-incompatible code
    // I.e. what should be xptr(r1, + 20) gets misread as 20(r1).
    // Pointer type can be partially inferred based on the mnemonic instruction
    if (cleanedOp.matches("^-?[0-9]+[(]-?[a-z0-9]+[)]$")) {
      String[] rebuild = cleanedOp.split("[(]");
      cleanedOp = ("(" + rebuild[0] + " + " + rebuild[1]).trim();
      if (mnemonic.toLowerCase().contains("b")) {
        cleanedOp = "bptr" + cleanedOp;
      } else if (mnemonic.toLowerCase().contains("w")) {
        cleanedOp = "dptr" + cleanedOp;
      } else {
        // Fallback, not guaranteed to work, but better than being broken
        cleanedOp = "dptr" + cleanedOp;
      }
    }

    // Due to multiple patches interacting with starting/ending characters,
    // it is necessarily to recursively patch until no changes can be made.
    if (cleanedOp == input) {
      return cleanedOp;
    } else {
      return dummyPatch(cleanedOp, mnemonic);
    }
  }

  /**
   * Converts a mnemonic assembly instruction and an array of operands into a prolog atom or function.
   * 
   * If there are no operators in the assembly function, the mnemonic is returned.
   * 
   * If there are operands, a function is returned containing a term per operand.
   * 
   * Calls dtype2ptr to clean pointer operands.
   * 
   * UNHANDLED CASES:
   *   - Does not perform any special mnemonic conversions; only lowercases mnemonics
   *   - Does not handle colon notation, i.e. fs:ebx, or any other non-standard notation
   */
  public static String formatInstruction(String mnemonic, JSONArray operands) {
    if (operands.size() == 0) {
      return mnemonic.toLowerCase();
    }
    ArrayList<String> cleanedOps = new ArrayList<String>();

    for (Object op : operands) {
      // Convert any pointers present via dtype2ptr(), take the full cleaned operand,
      //   and recursively convert all 0x- and h-formatted hex values via hexToDecimal().
      //   If the operand is somehow just an unformatted hex, that will also be converted to decimal.
      String cleanedOp = Helpers.hexToDecimal(dtype2ptr(op.toString())[1], true);

      // Add cleaned operand to list
      cleanedOps.add(dummyPatch(cleanedOp, mnemonic));
    }
    // TODO: This is dummy patch code to handle oddball issues that arise in prolog.
    // Specifially, this is done in the absence of time and the ability to create
    // a fully-functional anthing-to-prolog parser at the drop of a hat.
    String cleanString = "(" + String.join(",", cleanedOps) + ")";
    cleanString = cleanString.replaceAll("--+", "-");

    return mnemonic.toLowerCase() + cleanString;
  }
}