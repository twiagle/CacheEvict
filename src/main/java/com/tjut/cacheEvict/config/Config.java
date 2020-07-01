package com.tjut.cacheEvict.config;

import java.io.*;
import java.util.Properties;

/**
 * @author tb
 * @date 6/29/20-3:57 PM
 */
public class Config {
    private Properties properties;
    private String trainDataFile;
    private String outputFolder;
    private int trainingInterval;

    public Config(String configFile){
        if(configFile == null || "".equals(configFile)){
            throw new IllegalArgumentException("config does not contain the key ");
        }


        this.properties = new Properties();
        try(BufferedInputStream fileInputStream = new BufferedInputStream(new FileInputStream(configFile))){
            properties.load(fileInputStream);
            trainDataFile = properties.getProperty("trainDataFile");
            outputFolder = properties.getProperty("outputFolder");
            trainingInterval = Integer.parseInt(properties.getProperty("trainingInterval"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTrainDataFile() {
        return trainDataFile;
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public int getTrainingInterval() {
        return trainingInterval;
    }
}
