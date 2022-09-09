package br.com.canhette.pfood.util;

import lombok.Getter;

import java.io.File;

public enum FileType {

    PNG("image/png", "png"),
    JPG("image/jpeg", "jpg");

    @Getter
    String mineType;

    @Getter
    String extension;

    FileType(String miniType, String extension){
        this.mineType = miniType;
        this.extension = extension;
    }

    public boolean sameOf(String mineType){
        return this.mineType.equalsIgnoreCase(mineType);
    }

    public static FileType of(String mineType){
        for (FileType fileType : values()) {
            if (fileType.sameOf(mineType)) {
                return fileType;
            }
        }

        return null;
    }
}
