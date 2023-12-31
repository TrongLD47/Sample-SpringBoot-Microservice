package com.dailycodebuffer.common.util;

import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageUtils {
    /**
     * This method takes in an image as a byte array (currently supports GIF, JPG, PNG and possibly other formats) and
     * resizes it to have a width no greater than the pMaxWidth parameter in pixels. It converts the image to a standard
     * quality JPG and returns the byte array of that JPG image.
     *
     * @param pImageData
     *                the image data.
     * @param pMaxWidth
     *                the max width in pixels, 0 means do not scale.
     * @return the resized JPG image.
     * @throws IOException
     *                 if the iamge could not be manipulated correctly.
     */
    public static byte[] resizeImageAsJPG(byte[] pImageData, int pMaxWidth, int pMaxHeight, int fit) throws IOException {
        // Create an ImageIcon from the image data
        ImageIcon imageIcon = new ImageIcon(pImageData);
        int width = imageIcon.getIconWidth();
        int height = imageIcon.getIconHeight();
        // If the image is larger than the max width, we need to resize it
        if(fit == 2 && pMaxWidth > 0){
            //double ratio = (double) pMaxHeight / imageIcon.getIconHeight();
            double ratio = (double) pMaxWidth / imageIcon.getIconWidth();
            width = (int) (imageIcon.getIconWidth() * ratio);
            height = (int) (imageIcon.getIconHeight() * ratio);
            if(pMaxWidth > 0 && width > pMaxWidth) {
                width = pMaxWidth;
            }
        }else if (fit == 0 && pMaxHeight > 0) {
            // Determine the shrink ratio
            double ratio = (double) pMaxHeight / imageIcon.getIconHeight();
            width = (int) (imageIcon.getIconWidth() * ratio);
            if(pMaxWidth > 0 && width > pMaxWidth) {
                width = pMaxWidth;
            }
            height = pMaxHeight;
        }else if (fit == 1 && pMaxWidth > 0 && pMaxHeight > 0) {
            height = pMaxHeight;
            width = pMaxWidth;
        } else if (fit == 3 && pMaxHeight > 0){
            double ratio = (double) pMaxHeight / imageIcon.getIconHeight();
            width = (int) (imageIcon.getIconWidth() * ratio);
            height = (int) (imageIcon.getIconHeight() * ratio);
            if(pMaxHeight > 0 && height > pMaxHeight) {
                height = pMaxHeight;
            }
        }
        // Create a new empty image buffer to "draw" the resized image into
        BufferedImage bufferedResizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Create a Graphics object to do the "drawing"
        Graphics2D g2d = bufferedResizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        // Draw the resized image
        g2d.drawImage(imageIcon.getImage(), 0, 0, width, height, null);
        g2d.dispose();
        // Now our buffered image is ready
        // Encode it as a JPEG
        ByteArrayOutputStream encoderOutputStream = new ByteArrayOutputStream();
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(encoderOutputStream);
//		encoder.encode(bufferedResizedImage);
//		byte[] resizedImageByteArray = encoderOutputStream.toByteArray();
        ImageIO.write(bufferedResizedImage, "jpg", encoderOutputStream);
        return encoderOutputStream.toByteArray();
    }


    public static byte[] resizeImageAsJPGWithWaterMark(byte[] pImageData, byte[] waterImageData, int pMaxWidth, int pMaxHeight, int fit) throws IOException {
        // Create an ImageIcon from the image data
        ImageIcon imageIcon = new ImageIcon(pImageData);
        ImageIcon waterIcon = new ImageIcon(waterImageData);
        int width = imageIcon.getIconWidth();
        int height = imageIcon.getIconHeight();
        // If the image is larger than the max width, we need to resize it
        if (fit == 2 && pMaxWidth > 0) {
            //double ratio = (double) pMaxHeight / imageIcon.getIconHeight();
            double ratio = (double) pMaxWidth / imageIcon.getIconWidth();
            width = (int) (imageIcon.getIconWidth() * ratio);
            height = (int) (imageIcon.getIconHeight() * ratio);
            if (pMaxWidth > 0 && width > pMaxWidth) {
                width = pMaxWidth;
            }
        } else if (fit == 0 && pMaxHeight > 0) {
            // Determine the shrink ratio
            double ratio = (double) pMaxHeight / imageIcon.getIconHeight();
            width = (int) (imageIcon.getIconWidth() * ratio);
            if (pMaxWidth > 0 && width > pMaxWidth) {
                width = pMaxWidth;
            }
            height = pMaxHeight;
        } else if (fit == 1 && pMaxWidth > 0 && pMaxHeight > 0) {
            height = pMaxHeight;
            width = pMaxWidth;
        } else if (fit == 3 && pMaxHeight > 0) {
            double ratio = (double) pMaxHeight / imageIcon.getIconHeight();
            width = (int) (imageIcon.getIconWidth() * ratio);
            height = (int) (imageIcon.getIconHeight() * ratio);
            if (pMaxHeight > 0 && height > pMaxHeight) {
                height = pMaxHeight;
            }
        }
        // Create a new empty image buffer to "draw" the resized image into
        BufferedImage bufferedResizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Create a Graphics object to do the "drawing"
        Graphics2D g2d = bufferedResizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        // Draw the resized image
        g2d.drawImage(imageIcon.getImage(), 0, 0, width, height, null);
        g2d.drawImage(waterIcon.getImage(), 0, 0, width, height, null);
        g2d.dispose();
        // Now our buffered image is ready
        // Encode it as a JPEG
        ByteArrayOutputStream encoderOutputStream = new ByteArrayOutputStream();
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(encoderOutputStream);
//		encoder.encode(bufferedResizedImage);
//		byte[] resizedImageByteArray = encoderOutputStream.toByteArray();
        ImageIO.write(bufferedResizedImage, "jpg", encoderOutputStream);
        return encoderOutputStream.toByteArray();
    }


    public static byte[] cropImage(byte[] pImageData, int x, int y, int width, int height) throws IOException {
        InputStream in = new ByteArrayInputStream(pImageData);
        BufferedImage bImageFromConvert = ImageIO.read(in);
        BufferedImage bufferedCroppedImage = bImageFromConvert.getSubimage(x, y, width, height);
        ByteArrayOutputStream encoderOutputStream = new ByteArrayOutputStream();
//        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(encoderOutputStream);
//        encoder.encode(bufferedCroppedImage);
        ImageIO.write(bufferedCroppedImage, "jpg", encoderOutputStream);
        return encoderOutputStream.toByteArray();
    }

    private static Image scaleImage(String flagUrl, int scaleX, int scaleY) throws MalformedURLException {
        ImageIcon icon = new ImageIcon(new URL(flagUrl));
        return icon.getImage().getScaledInstance(1300, 800, Image.SCALE_DEFAULT);
    }

    public static boolean isValidImagePath(String image, String imagePath) {
        if (StringUtils.isNotBlank(image)) {
            File imageFile = new File(imagePath + File.separator + image);
            return imageFile.exists();
        }
        return Boolean.FALSE;
    }

    /**
     * Get uploaded type
     * @param type
     * @return
     */
    public static String getType(String type) {
        if (StringUtils.isNotEmpty(type)) {
            if (type.contains("image"))
                return "IMAGE";
            else if (type.contains("video"))
                return "VIDEO";
        }
        return "";
    }
}
