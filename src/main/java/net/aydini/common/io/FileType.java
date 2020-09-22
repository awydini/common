package net.aydini.common.io;

/**
 * 
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 *
 *         11:06:47 AM
 */
public enum FileType {

    JPG(".jpg"),PNG(".png"),GIF(".gif"),TXT(".txt"),CSV(".csv"),PDF(".pdf");
    

    private final String extension;
    FileType(String extension)
    {
        this.extension=extension;
    }
    public String getExtension()
    {
        return extension;
    }
}
