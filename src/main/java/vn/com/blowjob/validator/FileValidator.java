package vn.com.blowjob.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vn.com.blowjob.bean.FileForm;
import vn.com.blowjob.common.BuscuConst;

/**
* FileValidator Class
*
* @version 1.0
* @author NamNH30
*/
public class FileValidator implements Validator {

    /**
     * Method: validate
     *
     * @param Object obj
     * @param Errors errors
     * @return boolean
     */
    public boolean supports(Class<?> paramClass) {
        return FileForm.class.equals(paramClass);
    }

    /**
     * Method: validate
     *
     * @param Object obj
     * @param Errors errors
     */
    public void validate(Object obj, Errors errors) {
        FileForm file = (FileForm) obj;
        if (file.getFile().getSize() == 0) {
            errors.rejectValue(BuscuConst.FILE, BuscuConst.VALID_DOT_FILE);
        }
    }
}
