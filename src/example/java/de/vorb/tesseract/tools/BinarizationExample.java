package de.vorb.tesseract.tools;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import de.vorb.tesseract.tools.preprocessing.binarization.Sauvola;

public class BinarizationExample {
    public static void main(String[] args) throws IOException {
        final BufferedImage img = new Sauvola(8).binarize(
                ImageIO.read(new File("src/example/resources/text.png")));

        final JFrame frame = new JFrame("Binarized");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JLabel(new ImageIcon(img)),
                BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
