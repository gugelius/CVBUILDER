package com.example.testproject.entity;

public class UserFile extends AbstractEntity{
    private byte[] fileBytes;
    private String fileExtension;

    // Конструктор
    public UserFile(byte[] fileBytes, String fileExtension) {
        this.fileBytes = fileBytes;
        this.fileExtension = fileExtension;
    }

    // Геттеры
    public byte[] getFileBytes() {
        return fileBytes;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}
