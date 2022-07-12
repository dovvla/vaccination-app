package com.timrobot.vaccapp.utility;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import com.google.zxing.common.HybridBinarizer;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class QRcodeUtils {
    public static String convertURLToQR(String url){
        int imageSize = 200;
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE,
                    imageSize, imageSize);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(matrix, "png", bos);

            // return QrInfo
            return java.util.Base64.getEncoder().encodeToString(bos.toByteArray());
        }catch (Exception e){
            return "";
        }
    }

    public static String readQRCode(String base64Image) {
        String encodedContent = null;
        try {
            byte[] imageBytes = org.apache.jena.ext.xerces.impl.dv.util.Base64.decode(base64Image);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);
            BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
            encodedContent = readQRCode(bufferedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedContent;
    }

    public static String readQRCode(BufferedImage bufferedImage) {
        String encodedContent = null;
        try {
            BufferedImageLuminanceSource bufferedImageLuminanceSource = new BufferedImageLuminanceSource(bufferedImage);
            HybridBinarizer hybridBinarizer = new HybridBinarizer(bufferedImageLuminanceSource);
            BinaryBitmap binaryBitmap = new BinaryBitmap(hybridBinarizer);
            MultiFormatReader multiFormatReader = new MultiFormatReader();

            Result result = multiFormatReader.decode(binaryBitmap);
            encodedContent = result.getText();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return encodedContent;
    }
}
