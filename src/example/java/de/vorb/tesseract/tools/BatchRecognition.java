package de.vorb.tesseract.tools;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import de.vorb.tesseract.tools.preprocessing.Batch;
import de.vorb.tesseract.tools.preprocessing.binarization.Sauvola;

public class BatchRecognition {
    public static void main(String[] args) throws InterruptedException,
            IOException {
        final File from = new File(
                "E:/Masterarbeit/Ressourcen/DE-20__32_AM_49000_L869_G927-1/otsu");
        final File to = new File(
                "E:/Masterarbeit/Ressourcen/DE-20__32_AM_49000_L869_G927-1/otsu/deu-frak/txt");
        Files.createDirectories(to.toPath());

        // Sauvola binarization
        Batch.process(new File("E:/Masterarbeit/Ressourcen/GoldStandard"),
                new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        return f.isFile() && f.getName().endsWith(".png");
                    }
                }, new Batch() {
                    final Sauvola bin = new Sauvola(15);

                    @Override
                    public Runnable getTask(final File src) {
                        return new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    ImageIO.write(bin.binarize(ImageIO.read(src)),
                                            "PNG",
                                            new File(to, src.getName()));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                    }
                }, 1, TimeUnit.DAYS);
    }
}
