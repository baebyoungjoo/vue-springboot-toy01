package com.toy.board.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;

@RestController
@RequestMapping("/captcha")
/* 로컬 개발 주석 해제 */
@CrossOrigin(origins = "http://localhost:7732")
@Api(description = "naver captcha")
public class captchaController {

    private Logger logger = LoggerFactory.getLogger(captchaController.class);
    private String clientId = "HvrzgP1qGuN9I4mSLisg";
    private String clientSecret = "ZsbUrnJxla";
    /* 로컬 개발 시 사용 */
    private String captchaImagePath = System.getProperty("user.dir") + "/frontend/static/images/captchaImage";
    /* vue build 시 사용 */
    private String captchaImagePath2 = System.getProperty("user.dir") + "/src/main/resources/static/static/images/captchaImage";

    private static HttpURLConnection connectionSetting(String apiURL, String clientId, String clientSecret) throws Exception {
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-Naver-Client-Id", clientId);
        con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
        return con;
    }
    private static StringBuffer connectionStreamSetting(String apiURL, String clientId, String clientSecret) throws Exception {
        HttpURLConnection con = connectionSetting(apiURL, clientId, clientSecret);
        int responseCode = con.getResponseCode();
        BufferedReader br;
        if (responseCode == 200) {
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        return finResponseSetting(br);
    }
    private static StringBuffer finResponseSetting(BufferedReader br) throws Exception {
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();
        return response;
    }

    @RequestMapping(value = "/key", method = RequestMethod.GET)
    @ApiOperation(value = "captcha key 발급")
    public String captchaGetKey() throws Exception {
        String code = "0"; // key 발급 시 0, captcha 이미지 비교 시 1
        String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;
        return connectionStreamSetting(apiURL, clientId, clientSecret).toString();
    }

    @RequestMapping(value = "/image/{key}", method = RequestMethod.GET)
    @ApiOperation(value = "captch image 수신")
    public String captchaGetImage(@PathVariable String key) throws Exception {
        String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;

        HttpURLConnection con = connectionSetting(apiURL, clientId, clientSecret);
        int responseCode = con.getResponseCode();
        BufferedReader br;
        if (responseCode == 200) { // 정상 호출
            InputStream is = con.getInputStream();
            int read = 0;
            byte[] bytes = new byte[1024];
            // 랜덤한 이름으로 파일 생성
            String captchaImgName = Long.valueOf(new Date().getTime()).toString();
            File f = new File(captchaImagePath);
            if (!f.exists()) {
                f.mkdir();
            }
            f = new File(captchaImagePath + "/" + captchaImgName + ".jpg");
            f.createNewFile();
            OutputStream outputStream = new FileOutputStream(f);
            while ((read =is.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            is.close();
            return captchaImgName;
        } else { // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            finResponseSetting(br);
            logger.info("{}", finResponseSetting(br).toString());
            return "";
        }
    }

    @RequestMapping(value = "/check/{key}/{value}", method = RequestMethod.GET)
    @ApiOperation(value = "captch 입력값 비교")
    public String captchaValidCheck(@PathVariable String key, @PathVariable String value) throws Exception {
        String code = "1"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
        String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code +"&key="+ key + "&value="+ value;
        return connectionStreamSetting(apiURL, clientId, clientSecret).toString();
    }
}
