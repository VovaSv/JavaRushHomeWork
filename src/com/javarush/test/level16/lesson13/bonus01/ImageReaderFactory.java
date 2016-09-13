package com.javarush.test.level16.lesson13.bonus01;

import com.javarush.test.level16.lesson13.bonus01.common.*;

/**
 * Created by vladimis on 9/5/2016.
 */
public class ImageReaderFactory
{
    public static ImageReader getReader(ImageTypes jpg)
    {
        if(ImageTypes.BMP.equals(jpg))
            return new BmpReader();
        else if(ImageTypes.JPG.equals(jpg))
            return new JpgReader();
        else if(ImageTypes.PNG.equals(jpg))
            return new PngReader();
       else throw new IllegalArgumentException("Неизвестный тип картинки");

    }
}
