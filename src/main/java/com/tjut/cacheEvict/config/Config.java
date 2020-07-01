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
    private static Config config;

    private Config(){};
    //get args from main(args[0])
    public static Config getInstance(String configFile){
        if(configFile == null || "".equals(configFile)) {
            throw new IllegalArgumentException("config does not contain the key ");
        }

        if (config == null) {
            config = new Config();
            config.properties = new Properties();
            try(BufferedInputStream fileInputStream = new BufferedInputStream(new FileInputStream(configFile))){
                config.properties.load(fileInputStream);
                config.trainDataFile = config.properties.getProperty("trainDataFile");
                config.outputFolder = config.properties.getProperty("outputFolder");
                config.trainingInterval = Integer.parseInt(config.properties.getProperty("trainingInterval"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return config;

    }
    public static Config getInstance(){
        if(config == null){
            throw new IllegalArgumentException("Must Init first");
        }
        return config;
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
