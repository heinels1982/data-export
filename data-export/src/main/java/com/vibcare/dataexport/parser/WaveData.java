package com.vibcare.dataexport.parser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;


public class WaveData
{
  private byte[] arrFile;
  private byte[] audioBytes;
  private int[] audioData;
  private ByteArrayInputStream bis;
  private AudioInputStream audioInputStream;
  private AudioFormat format;
  private double durationSec;
  private double durationMSec;

  public WaveData()
  {
  }

  public int[] extractAmplitudeFromFile(File wavFile) throws IOException
  {

      // create file input stream
      FileInputStream fis = new FileInputStream(wavFile);
      // create bytearray from file
      arrFile = new byte[(int) wavFile.length()];
      fis.read(arrFile);

    return extractAmplitudeFromFileByteArray(arrFile);
  }

  public int[] extractAmplitudeFromFileByteArray(byte[] arrFile)
  {
    bis = new ByteArrayInputStream(arrFile);
    return extractAmplitudeFromFileByteArrayInputStream(bis);
  }

  /**
   * for extracting amplitude array the format we are using :16bit, 22khz, 1
   * channel, littleEndian,
   *
   * @return PCM audioData
   *
   * @throws Exception
   */
  public int[] extractAmplitudeFromFileByteArrayInputStream(ByteArrayInputStream bis)
  {
    try
    {
      audioInputStream = AudioSystem.getAudioInputStream(bis);
    }
    catch (UnsupportedAudioFileException e)
    {
      System.out.println("unsupported file type, during extract amplitude");
      e.printStackTrace();
    }
    catch (IOException e)
    {
      System.out.println("IOException during extracting amplitude");
      e.printStackTrace();
    }

    return extractAmplitudeDataFromAudioInputStream(audioInputStream);
  }

  public int[] extractAmplitudeDataFromAudioInputStream(AudioInputStream audioInputStream)
  {
    format = audioInputStream.getFormat();
    audioBytes = new byte[(int) (audioInputStream.getFrameLength() * format.getFrameSize())];
    // calculate durations
    durationMSec = (long) ((audioInputStream.getFrameLength() * 1000) / audioInputStream.getFormat().getFrameRate());
    durationSec = durationMSec / 1000.0;

    try
    {
      audioInputStream.read(audioBytes);
    }
    catch (IOException e)
    {
      System.out.println("IOException during reading audioBytes");
      e.printStackTrace();
    }
    return extractAmplitudeDataFromAmplitudeByteArray(format, audioBytes);
  }

  public int[] extractAmplitudeDataFromAmplitudeByteArray(AudioFormat format, byte[] audioBytes)
  {
    // convert
    // TODO: calculate duration here
    audioData = null;
    if (format.getSampleSizeInBits() == 16)
    {
      int nlengthInSamples = audioBytes.length / 2;
      audioData = new int[nlengthInSamples];
      if (format.isBigEndian())
      {
        for (int i = 0; i < nlengthInSamples; i++)
        {
          /* First byte is MSB (high order) */
          int MSB = audioBytes[2 * i];
          /* Second byte is LSB (low order) */
          int LSB = audioBytes[2 * i + 1];
          audioData[i] = MSB << 8 | (255 & LSB);
        }
      }
      else
      {
        for (int i = 0; i < nlengthInSamples; i++)
        {
          /* First byte is LSB (low order) */
          int LSB = audioBytes[2 * i];
          /* Second byte is MSB (high order) */
          int MSB = audioBytes[2 * i + 1];
          audioData[i] = MSB << 8 | (255 & LSB);
        }
      }
    }
    else if (format.getSampleSizeInBits() == 8)
    {
      int nlengthInSamples = audioBytes.length;
      audioData = new int[nlengthInSamples];
      if (format.getEncoding().toString().startsWith("PCM_SIGN"))
      {
        // PCM_SIGNED
        for (int i = 0; i < audioBytes.length; i++)
        {
          audioData[i] = audioBytes[i];
        }
      }
      else
      {
        // PCM_UNSIGNED
        for (int i = 0; i < audioBytes.length; i++)
        {
          audioData[i] = audioBytes[i] - 128;
        }
      }
    }// end of if..else
    // audioData.length);
    return audioData;
  }
}
