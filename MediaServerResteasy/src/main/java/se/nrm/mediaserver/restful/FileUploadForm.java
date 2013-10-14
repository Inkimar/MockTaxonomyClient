package se.nrm.mediaserver.restful;

/**
 *
 * @author ingimar
 */
import javax.ws.rs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class FileUploadForm {

    
    public FileUploadForm() {
    }

    private byte[] fileData;

    private String fileName;

    private String owner;

    private String access;

    public String getFileName() {
        return fileName;
    }

    @FormParam("fileName")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileData() {
        return fileData;
    }

    @FormParam("selectedFile")
    @PartType("application/octet-stream")
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
     @FormParam("owner")
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return the access
     */
    public String getAccess() {
        return access;
    }

    /**
     * @param access the access to set
     */
     @FormParam("access")
    public void setAccess(String access) {
        this.access = access;
    }
}