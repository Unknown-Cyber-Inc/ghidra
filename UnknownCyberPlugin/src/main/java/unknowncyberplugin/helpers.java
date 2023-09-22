package unknowncyberplugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class helpers {

  // Note:  There are a handful of use cases where this will fail:
  // - The original file is no longer on the host's system
  //    - This might also fail if the original file has been moved from where
  //      it was first imported from; I have not tested for this edge case
  // - The file was imported from an archive through the "Batch" option
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
}
