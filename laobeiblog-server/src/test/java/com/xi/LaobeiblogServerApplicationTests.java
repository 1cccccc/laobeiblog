package com.xi;

import com.xi.entity.FileEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.util.Date;

@SpringBootTest
class LaobeiblogServerApplicationTests {

    @Test
    void contextLoads() throws FileNotFoundException {
        FileEntity fileEntity = new FileEntity();
        FileEntity fileEntity2 = new FileEntity();
        Date date = new Date();

        fileEntity.setFileId(1).setFileMd5("aaa").setUploadTime(date);
        fileEntity2.setFileId(1).setFileMd5("aaa").setUploadTime(date);
        System.out.println(fileEntity==fileEntity2);
    }

}
