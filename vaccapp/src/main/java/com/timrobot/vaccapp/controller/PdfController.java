package com.timrobot.vaccapp.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {
    @GetMapping(value="printing/",produces= MediaType.APPLICATION_PDF_VALUE)
    public  @ResponseBody byte[]  print(@RequestParam("file") String file, HttpServletResponse response) {

        try {
            FileInputStream fis= new FileInputStream("src/main/resources/static/documents/" +file);
            byte[] targetArray = new byte[fis.available()];
            fis.read(targetArray);
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"file\".pdf");
            response.flushBuffer();
            return targetArray;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
