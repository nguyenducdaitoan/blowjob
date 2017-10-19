package vn.com.blowjob.bean;

import org.springframework.web.multipart.MultipartFile;

/**
 * FileForm Bean
 *
 * @version 1.0
 * @author Namnh30
 */
public class FileForm {

    private MultipartFile file;

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
