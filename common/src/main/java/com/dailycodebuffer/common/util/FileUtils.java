package com.dailycodebuffer.common.util;

import com.dailycodebuffer.common.common.CommonConstants;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static final int BUFSIZE = 2 << 15;
    protected static final Log log = LogFactory.getLog(FileUtils.class);

    public static String upload(HttpServletRequest request, String destFolder,
                                MultipartFile uplFile) throws IOException, ServletException {
        String fileName = normalizeFilename(uplFile.getOriginalFilename());
        File pathToSave = buildDestinationFile(request, destFolder, fileName);
        // Save to file
        uplFile.transferTo(pathToSave);
        return pathToSave.getName();
    }

    public static File buildDestinationFile(HttpServletRequest request,
                                            String destFolder, String fileName) {
        ServletContext context = request.getSession().getServletContext();
        String commonDirpath = context.getRealPath(destFolder);
        File baseFile = new File(commonDirpath);
        if (!baseFile.exists()) {
            baseFile.mkdir();
        }

        String newName = "";
        // Parse the request
        // Get just file name of upload file

        newName = normalizeFilename(fileName);
        // Get name withoout exension , get extension
        String nameWithoutExt = normalizeFilename(CommonUtils
                .getNameWithoutExtension(fileName));
        String ext = CommonUtils.getExtension(fileName);

        // *******************PATH to SAVE FILE***********************
        File pathToSave = new File(commonDirpath, fileName);

        int counter = 1;
        // Check if existed, generating new file name
        while (pathToSave.exists()) {
            // New filename = name(counter).ext
            StringBuffer buffer = new StringBuffer();
            buffer.append(nameWithoutExt).append("(").append(counter).append(")").append(".").append(ext);
            newName = buffer.toString();

            // Create new file to receive uploaded file
            pathToSave = new File(commonDirpath, newName);

            counter++;
        }
        return pathToSave;
    }

    private static String normalizeFilename(String fileName) {
        String res = fileName.replaceAll(" ", "_");
        return res;
    }

    public static void remove(String filename) {
        File file = new File(filename);
        file.delete();
    }

    public static void downLoadCsvFile(HttpServletResponse response,
                                       String filePath, String downloadFileName) throws IOException {
        ServletOutputStream out = null;
        try {
            FileInputStream in = new FileInputStream(filePath);
            out = response.getOutputStream();

            response.setContentType(String.format("application/octet-stream; name=%s", downloadFileName));
            response.setHeader("Content-Disposition", String.format("attachment; filename=%s", downloadFileName));
            response.setContentLength(in.available());

            byte[] buf = new byte[BUFSIZE];
            int count = 0;
            while ((count = in.read(buf)) != -1) {
                out.write(buf, 0, count);
            }
            in.close();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

//    public static boolean validateCSVFormatFile(MultipartFile file) {
//        if (file != null && file.getSize() > 0
//                && file.getOriginalFilename().contains(Constants.IMPORT_FILE_EXTENSION)) {
//            return true;
//        }
//        return false;
//    }

//    public static boolean validateCSVFileSize(MultipartFile file) {
//        int maxsize = Integer.parseInt(Config.getInstance().getProperty("upload.csv.size", String.valueOf(Constants.IMPORT_FILE_MAX_SIZE)));
//        if (file != null && file.getSize() <= maxsize) {
//            return true;
//        }
//        return false;
//    }

    public static void deleteDir(String dir) {
        try {
            File files = new File(dir);
            if (files.isDirectory()) {
                org.apache.commons.io.FileUtils.deleteDirectory(files);
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    public static boolean isBinaryFile(String uri) {
        return uri.endsWith(".pdf") || uri.toLowerCase().endsWith(".doc") || uri.toLowerCase().endsWith(".docx") || uri.toLowerCase().endsWith(".xls")
                || uri.toLowerCase().endsWith(".xlsx") || uri.toLowerCase().endsWith(".ppt") || uri.toLowerCase().endsWith(".pptx")
                || uri.toLowerCase().endsWith(".mp3") || uri.toLowerCase().endsWith(".mp4");
    }

//    public static void extractZipFileToTempFolder(String pathToFolder, MultipartFile fileUpload) throws IOException {
//        File tempFolder = new File(pathToFolder + File.separator + fileUpload.getOriginalFilename());
//        if (!tempFolder.exists()) {
//            tempFolder.mkdirs();
//        }
//
//        fileUpload.transferTo(tempFolder);
//        ZipUtil.unzip(tempFolder.getAbsolutePath());
//    }

    public static void writeMultipartFileToTempDir(String pathToFolder, MultipartFile fileUpload) throws IOException {
        File tempFolder = new File(pathToFolder + File.separator + fileUpload.getOriginalFilename());
        if (!tempFolder.exists()) {
            tempFolder.mkdirs();
        }

        fileUpload.transferTo(tempFolder);
    }

    public static boolean checkExistFile(String pathToFile) {
        File file = new File(pathToFile);
        return file.exists();
    }

    public static String validateImageExt(String filename) throws Exception {
        int lastIndex = filename.lastIndexOf(".");
        if (lastIndex == -1)
            throw new IllegalStateException("error.format.unknown");

        String extension = filename.substring(filename.lastIndexOf("."));
        if (!".png".equalsIgnoreCase(extension) &&
                !".jpg".equalsIgnoreCase(extension) &&
                !".jpeg".equalsIgnoreCase(extension))
            throw new IllegalStateException("error.format.image.invalid");

        return "IMAGE";
    }

    public static String getMime(String filename) {
        int lastIndex = filename.lastIndexOf(".");
        if (lastIndex == -1)
            return null;

        String extension = filename.substring(filename.lastIndexOf("."));
        if (".png".equalsIgnoreCase(extension))
            return "image/png";
        else if (".jpg".equalsIgnoreCase(extension) || ".jpeg".equalsIgnoreCase(extension))
            return "image/jpeg";

        return null;
    }

    public static String getImageName(String imageNamePrefix) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
        String prefix = df.format(new Timestamp(System.currentTimeMillis()));
        String imageName = imageNamePrefix.replaceAll(" ", "_").toLowerCase() + prefix + ".jpg";

        return imageName;
    }

    public static byte[] getBase64ImageFromJCRWithScaled(byte[] data, Integer imgW, Integer imgH){
        byte[] d = null;
        if(data != null && data.length > 0) {
            byte[] newData = scale(data, imgW, imgH);
            if (newData != null && newData.length > 0) {
                d = newData;
            }
        }
        return d;
    }

    public static File getFirstFolder(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                return file;
            }
        }
        return null;
    }

    public static byte[] scale(byte[] fileData, int width, int height) {
        ByteArrayInputStream in = new ByteArrayInputStream(fileData);
        try {
            BufferedImage img = ImageIO.read(in);
            int original_width = img.getWidth();
            int original_height = img.getHeight();
            int bound_width = width;
            int bound_height = height;
            int new_width = original_width;
            int new_height = original_height;
            // first check if we need to scale width
            if (original_width > bound_width) {
                //scale width to fit
                new_width = bound_width;
                //scale height to maintain aspect ratio
                new_height = (new_width * original_height) / original_width;
            }

            // then check if we need to scale even with the new height
            if (new_height > bound_height) {
                //scale height to fit instead
                new_height = bound_height;
                //scale width to maintain aspect ratio
                new_width = (new_height * original_width) / original_height;
            }

            Image scaledImage = img.getScaledInstance(new_width, new_height, Image.SCALE_SMOOTH);
            BufferedImage imageBuff = new BufferedImage(new_width, new_height, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            ImageIO.write(imageBuff, "jpg", buffer);

            return buffer.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    private File extractToZipFile(MultipartFile fileUpload, HttpServletRequest request) throws IOException {
        long kilobyte = 1024;
        long megabyte = kilobyte * 1024;

        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HHmmss");
        String date = sdf.format(System.currentTimeMillis());
        String folder = "import_stockout_outlet_" + date;
        String jbossTempFolderPath = System.getProperty("jboss.server.temp.dir");
        File file = new File(jbossTempFolderPath + File.separator + folder);
        if (!file.exists()) {
            file.mkdirs();
        }
        File stockinFolder = new File(file.getPath() + File.separator + "stockout");
        if (!stockinFolder.exists()) {
            stockinFolder.mkdirs();
        }
        String tempFolder = stockinFolder.getPath() + File.separator + request.getSession().getId();
        FileUtils.writeMultipartFileToTempDir(tempFolder, fileUpload);

        long bytes = Files.size(Paths.get(tempFolder + File.separator + fileUpload.getOriginalFilename()));
        if (bytes / megabyte > CommonConstants.MAX_FILE_UPLOAD_SIZE_MB) {
            FileDeleteStrategy.FORCE.delete(file);
            return null;
        } else {
            String fileName = fileUpload.getOriginalFilename();
            String importFilePath = tempFolder + File.separator + fileName;
            File excelFile = new File(importFilePath);
            if (excelFile.exists()) {
                return excelFile;
            } else {
                FileDeleteStrategy.FORCE.delete(file);
            }
        }
        return null;
    }
}
