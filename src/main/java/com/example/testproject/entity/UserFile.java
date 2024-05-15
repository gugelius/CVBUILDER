package com.example.testproject.entity;

public class UserFile extends AbstractEntity{
    private byte[] fileBytes;
    private String fileExtension;
    public UserFile(byte[] fileBytes, String fileExtension) {
        this.fileBytes = fileBytes;
        this.fileExtension = fileExtension;
    }
    public byte[] getFileBytes() {
        return fileBytes;
    }
    public String getFileExtension() {
        return fileExtension;
    }
}
