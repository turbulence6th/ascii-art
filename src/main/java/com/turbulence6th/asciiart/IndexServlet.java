package com.turbulence6th.asciiart;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Optional;

import static com.turbulence6th.asciiart.Constants.*;

@WebServlet("/image")
@MultipartConfig
public class IndexServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int scale = Optional.ofNullable(request.getParameter(SCALE)).filter(IndexServlet::notEmptyString).map(Integer::parseInt).orElse(DEFAULT_SCALE);
        int fontSize = Optional.ofNullable(request.getParameter(FONT_SIZE)).filter(IndexServlet::notEmptyString).map(Integer::parseInt).orElse(DEFAULT_FONT_SIZE);
        double brightness = Optional.ofNullable(request.getParameter(DARKNESS)).filter(IndexServlet::notEmptyString).map(Double::parseDouble).orElse(DEFAULT_DARKNESS);

        char black = Optional.ofNullable(request.getParameter(BLACK)).filter(IndexServlet::notEmptyString).map(IndexServlet::strToChar).orElse(BLACK_DEFAULT);
        char blue = Optional.ofNullable(request.getParameter(BLUE)).filter(IndexServlet::notEmptyString).map(IndexServlet::strToChar).orElse(BLUE_DEFAULT);
        char green = Optional.ofNullable(request.getParameter(GREEN)).filter(IndexServlet::notEmptyString).map(IndexServlet::strToChar).orElse(GREEN_DEFAULT);
        char cyan = Optional.ofNullable(request.getParameter(CYAN)).filter(IndexServlet::notEmptyString).map(IndexServlet::strToChar).orElse(CYAN_DEFAULT);
        char red = Optional.ofNullable(request.getParameter(RED)).filter(IndexServlet::notEmptyString).map(IndexServlet::strToChar).orElse(RED_DEFAULT);
        char magenta = Optional.ofNullable(request.getParameter(MAGENTA)).filter(IndexServlet::notEmptyString).map(IndexServlet::strToChar).orElse(MAGENTA_DEFAULT);
        char yellow = Optional.ofNullable(request.getParameter(YELLOW)).filter(IndexServlet::notEmptyString).map(IndexServlet::strToChar).orElse(YELLOW_DEFAULT);
        char white = Optional.ofNullable(request.getParameter(WHITE)).filter(IndexServlet::notEmptyString).map(IndexServlet::strToChar).orElse(WHITE_DEFAULT);

        Part file = request.getPart(FILE);

        BufferedImage oldImage = ImageIO.read(file.getInputStream());

        BufferedImage image = null;
        BufferedImage output = null;
        while (output != null) {
            image = new BufferedImage(oldImage.getWidth() / scale, oldImage.getHeight() / scale, BufferedImage.TYPE_INT_RGB);

            Graphics g = image.createGraphics();
            g.drawImage(oldImage, 0, 0, oldImage.getWidth() / scale, oldImage.getHeight() / scale, null);
            g.dispose();

            try {
                output = new BufferedImage(image.getWidth() * fontSize, image.getHeight() * fontSize, BufferedImage.TYPE_INT_RGB);
            } catch (OutOfMemoryError e) {
                scale++;
            }
        }

        Graphics graphics = output.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, image.getWidth() * fontSize, image.getHeight() * fontSize);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(null, Font.PLAIN, fontSize));

        for (int x = 0; x < image.getHeight(); x++) {
            for (int y = 0; y < image.getWidth(); y++) {
                Color color = new Color(image.getRGB(y, x));
                int redColor = color.getRed();
                int greenColor = color.getGreen();
                int blueColor = color.getBlue();

                char c = 0;

                if (checkBrightness(redColor, brightness) && checkBrightness(greenColor, brightness) && checkBrightness(blueColor, brightness)) {
                    c = black;
                } else if (checkBrightness(redColor, brightness) && checkBrightness(greenColor, brightness) && !checkBrightness(blueColor, brightness)) {
                    c = blue;
                } else if (checkBrightness(redColor, brightness) && !checkBrightness(greenColor, brightness) && checkBrightness(blueColor, brightness)) {
                    c = green;
                } else if (checkBrightness(redColor, brightness) && !checkBrightness(greenColor, brightness) && !checkBrightness(blueColor, brightness)) {
                    c = cyan;
                } else if (!checkBrightness(redColor, brightness) && checkBrightness(greenColor, brightness) && checkBrightness(blueColor, brightness)) {
                    c = red;
                } else if (!checkBrightness(redColor, brightness) && checkBrightness(greenColor, brightness) && !checkBrightness(blueColor, brightness)) {
                    c = magenta;
                } else if (!checkBrightness(redColor, brightness) && !checkBrightness(greenColor, brightness) && checkBrightness(blueColor, brightness)) {
                    c = yellow;
                } else if (!checkBrightness(redColor, brightness) && !checkBrightness(greenColor, brightness) && !checkBrightness(blueColor, brightness)) {
                    c = white;
                }

                graphics.drawString(String.valueOf(c), y * fontSize, x * fontSize);
            }
        }

        response.setHeader("Content-disposition",String.format("attachment; filename=%s", OUTPUT_FILE_NAME));

        ImageIO.write(output, OUTPUT_FORMAT, response.getOutputStream());
    }

    private static boolean checkBrightness(int color, double brightness) {
        return color < 255 * brightness;
    }

    private static boolean notEmptyString(String str) {
        return !str.isEmpty();
    }

    private static char strToChar(String str) {
        return str.charAt(0);
    }

}
