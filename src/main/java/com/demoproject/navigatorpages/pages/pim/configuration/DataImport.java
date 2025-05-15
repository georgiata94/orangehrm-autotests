package com.demoproject.navigatorpages.pages.pim.configuration;

import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataImport {

    private static final Logger logger = LogManager.getLogger(DataImport.class);

    public void uploadData(String filePath) {
        logger.info("Starting data upload for file: {}", filePath);
        try {

            ActionHelper.waitForVisibility(ButtonManager.get("pim.configuration.dataImport.upload"));
            ActionHelper.uploadFile(ButtonManager.get("pim.configuration.dataImport.fileInputField"), filePath);
            logger.info("Typed file name into input field: {}", filePath);

            ActionHelper.waitForVisibility(ButtonManager.get("pim.configuration.dataImport.upload"));
            logger.info("Upload button is visible.");

            ActionHelper.click(ButtonManager.get("pim.configuration.dataImport.upload"));
            logger.info("Clicked upload button.");

            ActionHelper.waitForVisibility(ButtonManager.get("pim.configuration.dataImport.confirmButton"));
            ActionHelper.click(ButtonManager.get("pim.configuration.dataImport.confirmButton"));
            logger.info("Upload successful.");

        } catch (Exception e) {
            logger.error("Error during data upload for file {}: {}", filePath, e.getMessage(), e);
            throw new RuntimeException("Failed to upload data file: " + filePath, e);
        }
    }

}
