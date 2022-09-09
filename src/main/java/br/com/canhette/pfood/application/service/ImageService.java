package br.com.canhette.pfood.application.service;

import br.com.canhette.pfood.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@Service
public class ImageService {

    @Value("${canhette.files.image}")
    private String logotiposDir;

    public void uploadLogoTipo(MultipartFile multipartFile, String fileName){
        try {
            IOUtils.copy(multipartFile.getInputStream(), fileName, logotiposDir);
        } catch (IOException e) {
            throw new AplicationServiceException(e);
        }
    }
}
