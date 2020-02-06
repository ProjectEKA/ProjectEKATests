package Tests.MobileAppTests.Utils;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * This utility extracts files and directories of a standard zip file to
 * a destination directory.
 *
 * @author www.codejava.net
 */
public class UnzipUtility {

    public static File  unpackArchive(URL url, File targetDir) throws IOException {
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        InputStream in = new BufferedInputStream(url.openStream(), 1024);
        // make sure we get the actual file
        File zip = File.createTempFile("arc", ".zip", targetDir);
        OutputStream out = new BufferedOutputStream(new FileOutputStream(zip));
        copyInputStream(in, out);
        out.close();
        return unpackArchive(zip, targetDir);
    }

    /**
     * Unpack a zip file
     *
     * @param theFile
     * @param targetDir
     * @return the file
     * @throws IOException
     */
    private static File unpackArchive(File theFile, File targetDir) throws IOException {
        if (!theFile.exists()) {
            throw new IOException(theFile.getAbsolutePath() + " does not exist");
        }
        if (!buildDirectory(targetDir)) {
            throw new IOException("Could not create directory: " + targetDir);
        }
        ZipFile zipFile = new ZipFile(theFile);
        for (Enumeration entries = zipFile.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            File file = new File(targetDir, File.separator + entry.getName());
            if (!buildDirectory(file.getParentFile())) {
                throw new IOException("Could not create directory: " + file.getParentFile());
            }
            if (!entry.isDirectory()) {
                copyInputStream(zipFile.getInputStream(entry), new BufferedOutputStream(new FileOutputStream(file)));
            } else {
                if (!buildDirectory(file)) {
                    throw new IOException("Could not create directory: " + file);
                }
            }
        }
        zipFile.close();
        return theFile;
    }

    private static void copyInputStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        while (len >= 0) {
            out.write(buffer, 0, len);
            len = in.read(buffer);
        }
        in.close();
        out.close();
    }

    private static boolean buildDirectory(File file) {
        return file.exists() || file.mkdirs();
    }
}