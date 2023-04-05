package com.FutureClassroom.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Description:
 * Param:
 * return:
 * Author: KIKI
 * Date: 2023-04-03
 */
public interface FileService {
    String upload(MultipartFile file);
}
