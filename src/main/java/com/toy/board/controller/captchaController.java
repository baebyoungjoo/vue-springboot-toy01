package com.toy.board.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

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
    private String clientId = "HvrzgP1qGuN9I4mSLisg";
    private String clientSecret = "ZsbUrnJxla";

    @RequestMapping(value = "/key", method = RequestMethod.GET)
    @ApiOperation(value = "captcha key 발급")
    public String captchaGetKey() throws Exception {
        String code = "0";
        String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-Naver-Client-Id", clientId);
        con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
        int responseCode = con.getResponseCode();
        BufferedReader br;
        if (responseCode == 200) {
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();

        return response.toString();
    }

    @RequestMapping(value = "/image/{key}", method = RequestMethod.GET)
    @ApiOperation(value = "captch image 수신")
    public void captchaGetImage(@PathVariable String key) throws Exception {
        System.out.println(key);

//        String key = "QFvcD3BMSVEz69mb";
        String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-Naver-Client-Id", clientId);
        con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
        int responseCode = con.getResponseCode();
        BufferedReader br;
        if(responseCode==200) { // 정상 호출
            InputStream is = con.getInputStream();
            int read = 0;
            byte[] bytes = new byte[1024];
            // 랜덤한 이름으로 파일 생성
            String captchaImgName = Long.valueOf(new Date().getTime()).toString();
            File f = new File(captchaImgName + ".jpg");
            f.createNewFile();
            OutputStream outputStream = new FileOutputStream(f);
            while ((read =is.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            is.close();
        } else {  // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
        }
    }

    @RequestMapping(value = "/check/{key}/{value}", method = RequestMethod.GET)
    @ApiOperation(value = "captch 입력값 비교")
    public String captchaValidCheck(@PathVariable String key, @PathVariable String value) throws Exception {

        String code = "1"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
//        String key = "QFvcD3BMSVEz69mb"; // 캡차 키 발급시 받은 키값
//        String value = "8193"; // 사용자가 입력한 캡차 이미지 글자값
        String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code +"&key="+ key + "&value="+ value;

        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-Naver-Client-Id", clientId);
        con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
        int responseCode = con.getResponseCode();
        BufferedReader br;
        if(responseCode==200) { // 정상 호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {  // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();
        return response.toString();
    }
}
