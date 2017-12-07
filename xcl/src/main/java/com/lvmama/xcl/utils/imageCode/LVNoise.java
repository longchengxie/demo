package com.lvmama.xcl.utils.imageCode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.google.code.kaptcha.NoiseProducer;
import com.google.code.kaptcha.util.Configurable;
/**
 * 自定义验证码干扰线生成逻辑
 *
 * @author gairyoung
 *
 */
public class LVNoise extends Configurable implements NoiseProducer{

        /**
         * Draws a noise on the image. The noise curve depends on the factor values.
         * Noise won't be visible if all factors have the value > 1.0f
         *
         * @param image
         *            the image to add the noise to
         * @param factorOne
         * @param factorTwo
         * @param factorThree
         * @param factorFour
         */
        public void makeNoise(BufferedImage image, float factorOne, float factorTwo, float factorThree, float factorFour) {


                Random rand = new Random();
                int InterferenceLineNum = 1;//干扰线总条数（只有一条干扰线）

                //图片的宽和高的值
                int width = image.getWidth();
                int height = image.getHeight();

                Graphics2D graph = (Graphics2D) image.getGraphics();
                graph.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
                graph.setBackground(Color.white);//图片背景色(白色)

                //绘制干扰线
                for (int i = 0; i < InterferenceLineNum; i++) {
                        graph.setStroke(new BasicStroke(0.9f *1.0f));//设置干扰线的粗细
                        graph.setColor(Color.black);//设置线条颜色（黑色）
                        int x = rand.nextInt(width);
                        int y = rand.nextInt(height);
                        int x1 = rand.nextInt(100);
                        int y1 = rand.nextInt(30);
                        graph.drawLine(x, y, x1, y1);//绘制干扰线
                }

                //添加噪点
                float yawpRate = 0.1f;// 噪声率
                int area = (int) (yawpRate * width * height);
                for (int i = 0; i < area; i++) {
                        image.setRGB(rand.nextInt(width), rand.nextInt(height), 0);
                }



                graph.dispose();//释放资源
        }

}
