package unknowncyberplugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;

public class Prolog {

  private Prolog() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static void handleSign() {

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
      cleanedOps.add(Helpers.hexToDecimal(dtype2ptr(op.toString())[1]));
    }
    return mnemonic.toLowerCase() + "(" + String.join(",", cleanedOps) + ")";
  }
}