package com.imooc.controller.center;

import com.imooc.common.utils.CookieUtils;
import com.imooc.common.utils.DateUtil;
import com.imooc.common.utils.IMOOCJSONResult;
import com.imooc.common.utils.JsonUtils;
import com.imooc.controller.BaseController;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.center.CenterUserBO;
import com.imooc.resource.FileUpload;
import com.imooc.service.center.CenterUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eru on 2020/2/8.
 */
@Api(value = "用户中心-用户相关", tags = {"用户中心-用户相关api"})
@RequestMapping("userInfo")
@RestController
public class CenterUserController extends BaseController {

    @Autowired
    private CenterUserService centerUserService;
    @Autowired
    private FileUpload fileUpload;

    @ApiOperation(value = "更新用户信息", notes = "更新用户信息", httpMethod = "POST")
    @PostMapping("/update")
    public IMOOCJSONResult update(@RequestParam @ApiParam(name = "userId", value = "用户id")String userId,
                                  @RequestBody @Valid CenterUserBO centerUserBO,
                                  BindingResult result,
                                  HttpServletRequest request, HttpServletResponse response){

        if (result.hasErrors()){
            Map<String, String> map = getErrors(result);
            return IMOOCJSONResult.errorMap(map);
        }

        Users user = centerUserService.updateUserInfo(userId, centerUserBO);

        user = setNullProperty(user);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);

        // TODO 后续要改，增加令牌token，会整合进redis，分布式会话

        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "上传头像", notes = "上传头像", httpMethod = "POST")
    @PostMapping("/uploadFace")
    public IMOOCJSONResult uploadFace(@RequestParam @ApiParam(name = "userId", value = "用户id", required = true)String userId,
                                      @ApiParam(name = "file", value = "用户头像", required = true) MultipartFile file,
                                      HttpServletRequest request, HttpServletResponse response){
        String fileSpace = fileUpload.getImageUserFaceLocation();
        String uploadPathPrefix = File.separator + userId;

        if (file != null){
            FileOutputStream fos = null;
            try {
                String oriFile = file.getOriginalFilename();
                if (StringUtils.isNotBlank(oriFile)){
                    String[] fileNames = oriFile.split("\\.");
                    String suffix = fileNames[fileNames.length - 1];
                    if (!"jpg".equalsIgnoreCase(suffix)
                            && !"png".equalsIgnoreCase(suffix)
                            && !"jpeg".equalsIgnoreCase(suffix)){
                        return IMOOCJSONResult.errorMsg("图片格式不正确");
                    }
                    String newFileName = "face-" + userId + "." + suffix;

                    String finalFacePath = fileSpace + uploadPathPrefix + File.separator + newFileName;

                    File outFile = new File(finalFacePath);
                    if (outFile.getParentFile() != null){
                        outFile.mkdirs();
                    }

                    fos = new FileOutputStream(outFile);
                    InputStream is = file.getInputStream();
                    IOUtils.copy(is, fos);
                }
            }catch(IOException ex){
                ex.printStackTrace();
            }finally {
                if (fos != null){
                    try {
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }else {
            return IMOOCJSONResult.errorMsg("文件不能为空");
        }

        String imageServerUrl = fileUpload.getImageUserFaceLocation();

        String finalUserFaceUrl = imageServerUrl + uploadPathPrefix
                + "?t=" + DateUtil.getCurrentDateString(DateUtil.DATE_PATTERN);

        Users userRes = centerUserService.updateUserFace(userId, finalUserFaceUrl);
        setNullProperty(userRes);
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userRes), true);

        // TODO 后续要改，增加令牌token，会整合进redis，分布式会话

        return IMOOCJSONResult.ok();
    }

    public Map<String, String> getErrors(BindingResult result){
        Map<String, String> map = new HashMap<>();
        List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError error: fieldErrors){
            String field = error.getField();
            String msg = error.getDefaultMessage();
            map.put(field, msg);
        }
        return map;
    }

    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }
}
