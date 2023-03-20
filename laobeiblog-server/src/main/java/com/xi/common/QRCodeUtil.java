package com.xi.common;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.nimbusds.jose.util.Base64;
import jakarta.servlet.ServletOutputStream;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;

@Slf4j
@UtilityClass
public class QRCodeUtil {

    /**
     * 默认宽度
     */
    private static final Integer WIDTH = 200;
    /**
     * 默认高度
     */
    private static final Integer HEIGHT = 200;

    /**
     * LOGO 默认宽度
     */
    private static final Integer LOGO_WIDTH = 25;
    /**
     * LOGO 默认高度
     */
    private static final Integer LOGO_HEIGHT = 25;

    /**
     * 图片格式
     */
    private static final String IMAGE_FORMAT = "png";
    private static final String CHARSET = "utf-8";
    /**
     * 原生转码前面没有 data:image/png;base64 这些字段，返回给前端是无法被解析
     */
    private static final String BASE64_IMAGE = "data:image/png;base64,%s";

    /**
     * 生成二维码，使用默认尺寸
     *
     * @param content 内容
     * @return
     */
    public String getBase64QRCode(String content) {
        return getBase64Image(content, WIDTH, HEIGHT, null, null, null);
    }

    /**
     * 生成二维码，使用默认尺寸二维码，插入默认尺寸logo
     *
     * @param content 内容
     * @param logoUrl logo地址
     * @return
     */
    public String getBase64QRCode(String content, String logoUrl) {
        return getBase64Image(content, WIDTH, HEIGHT, logoUrl, LOGO_WIDTH, LOGO_HEIGHT);
    }

    /**
     * 生成二维码
     *
     * @param content    内容
     * @param width      二维码宽度
     * @param height     二维码高度
     * @param logoUrl    logo 在线地址
     * @param logoWidth  logo 宽度
     * @param logoHeight logo 高度
     * @return
     */
    public String getBase64QRCode(String content, Integer width, Integer height, String logoUrl, Integer logoWidth, Integer logoHeight) {
        return getBase64Image(content, width, height, logoUrl, logoWidth, logoHeight);
    }

    private String getBase64Image(String content, Integer width, Integer height, String logoUrl, Integer logoWidth, Integer logoHeight) {
        //获取一个字节数组输出流
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //创建一个二维码BufferedImage
        BufferedImage bufferedImage = crateQRCode(content, width, height, logoUrl, logoWidth, logoHeight);
        try {
            //将二维码以指定格式转换成字节数组写入到os
            ImageIO.write(bufferedImage, IMAGE_FORMAT, os);
        } catch (IOException e) {
            log.error("[生成二维码，错误{}]", e);
        }
        // 转换成base64编码并加上指定前缀
        return String.format(BASE64_IMAGE, Base64.encode(os.toByteArray()));
    }


    /**
     * 生成二维码
     *
     * @param content    内容
     * @param width      二维码宽度
     * @param height     二维码高度
     * @param logoUrl    logo 在线地址
     * @param logoWidth  logo 宽度
     * @param logoHeight logo 高度
     * @return
     */
    private BufferedImage crateQRCode(String content, Integer width, Integer height, String logoUrl, Integer logoWidth, Integer logoHeight) {
        if (StringUtils.isNotBlank(content)) {
            ServletOutputStream stream = null;
            HashMap<EncodeHintType, Comparable> hints = new HashMap<>(4);
            // 指定字符编码为utf-8
            hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
            // 指定二维码的纠错等级为中级
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            // 设置图片的边距
            hints.put(EncodeHintType.MARGIN, 2);
            try {
                QRCodeWriter writer = new QRCodeWriter();
                //比特矩阵
                BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
                BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                //绘制二维码
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        //bitMatrix.get返回true表示黑色，后面的是16进制
                        bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                    }
                }
                if (StringUtils.isNotBlank(logoUrl)) {
                    //插入logo
                    insertLogo(bufferedImage, width, height, logoUrl, logoWidth, logoHeight);
                }
                return bufferedImage;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {//资源的关闭
                if (stream != null) {
                    try {
                        stream.flush();
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * 二维码插入logo
     *
     * @param source     二维码
     * @param width      二维码宽度
     * @param height     二维码高度
     * @param logoUrl    logo 在线地址
     * @param logoWidth  logo 宽度
     * @param logoHeight logo 高度
     * @throws Exception
     */
    private void insertLogo(BufferedImage source, Integer width, Integer height, String logoUrl, Integer logoWidth, Integer logoHeight) throws Exception {
        // logo 源可为 File/InputStream/URL
        Image src = ImageIO.read(new URL(logoUrl));
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        //计算logo的放置位置
        int x = (width - logoWidth) / 2;
        int y = (height - logoHeight) / 2;

        //绘制logo
        graph.drawImage(src, x, y, logoWidth, logoHeight, null);

        //创建一个圆型矩形
        Shape shape = new RoundRectangle2D.Float(x, y, logoWidth, logoHeight, 6, 6);

        //设置画笔大小
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }


    /**
     * 获取二维码
     *
     * @param content 内容
     * @param output  输出流
     * @throws IOException
     */
    public void getQRCode(String content, OutputStream output) throws IOException {
        BufferedImage image = crateQRCode(content, WIDTH, HEIGHT, null, null, null);
        ImageIO.write(image, IMAGE_FORMAT, output);
    }

    /**
     * 获取二维码
     *
     * @param content 内容
     * @param logoUrl logo资源
     * @param output  输出流
     * @throws Exception
     */
    public void getQRCode(String content, String logoUrl, OutputStream output) throws Exception {
        BufferedImage image = crateQRCode(content, WIDTH, HEIGHT, logoUrl, LOGO_WIDTH, LOGO_HEIGHT);
        ImageIO.write(image, IMAGE_FORMAT, output);
    }

}
