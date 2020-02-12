package com.imooc.exception;

import com.imooc.common.utils.IMOOCJSONResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * Created by eru on 2020/2/12.
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public IMOOCJSONResult handle(MaxUploadSizeExceededException ex){
        return IMOOCJSONResult.errorMsg("文件大小不能超过500K, 请重新上传!");
    }
}
