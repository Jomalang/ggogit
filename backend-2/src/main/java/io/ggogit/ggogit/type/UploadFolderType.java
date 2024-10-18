package io.ggogit.ggogit.type;

public enum UploadFolderType {
    TMP("tmp"),
    TREE("tree"),
    MEMBER("member"),
    MEMOIR("memoir"),
    LEAF("leaf");

    private final String value;

    UploadFolderType(String value) {
        this.value = value;
    }

    public String getFolderName() {
        return value;
    }
}