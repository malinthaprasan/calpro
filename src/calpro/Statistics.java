/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calpro;

import java.util.Arrays;

public class Statistics {

    private static float[] data;
    private static int size;

    public static void setData(float[] d){
         data=d;
         size=d.length;
    }
    
    public static float getMean(float d[]) {
        setData(d);
        
        float sum = 0.0f;
        for (float a : data) {
            sum += a;
        }
        return sum / size;
    }

    public static float getVariance(float d[]) {
        setData(d);
        
        float mean = getMean(data);
        float temp = 0;
        for (float a : data) {
            temp += (mean - a) * (mean - a);
        }
        return temp / (size-1); // sample variance
    }

    public static float getStdDev(float[] d) {
        setData(d);
        
        return (float)Math.sqrt(getVariance(d));
    }

    public static float median(float[] d) {
        setData(d);
        
        float[] b = new float[data.length];
        System.arraycopy(data, 0, b, 0, b.length);
        Arrays.sort(b);

        if (data.length % 2 == 0) {
            return (b[(b.length / 2) - 1] + b[b.length / 2]) / 2.0f;
        } else {
            return b[b.length / 2];
        }
    }
    
    public static float getMax(float[] d){
        setData(d);
        
        float max=d[0];
        for (float a : data) {
            max=Math.max(a,max);
        }
        
        return max;
        
    }
}
