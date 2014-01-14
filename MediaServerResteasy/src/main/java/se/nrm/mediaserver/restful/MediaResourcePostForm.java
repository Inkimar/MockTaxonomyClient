/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.mediaserver.restful;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import se.nrm.mediaserver.media3.domain.Attachment;
import se.nrm.mediaserver.media3.domain.Determination;
import se.nrm.mediaserver.media3.domain.Image;
import se.nrm.mediaserver.media3.domain.Media;
import se.nrm.mediaserver.media3.domain.Sound;
import se.nrm.mediaserver.media3.domain.Tag;
import se.nrm.mediaserver.media3.domain.Video;
import se.nrm.mediaserver.service.MediaService;
import se.nrm.mediaserver.util.FileSystemWriter;
import se.nrm.mediaserver.util.JNDIFetchRemote;
import se.nrm.mediaserver.util.MimeParser;
import se.nrm.mediaserver.util.PathHelper;
import se.nrm.mediaserver.util.Writeable;

/**
 *
 * @author ingimar
 */
@Path("/media")
@Consumes({"application/xml", "application/json"})
@Produces({"application/xml", "application/json"})
public class MediaResourcePostForm {

    final MediaService bean = JNDIFetchRemote.outreach();

    /**
     * Upload file and metadata via a form / see index.jsp Saves file to
     * filesystem, metadata to database. dependency upon resteasy - a restful
     * impl.
     *
     * @param form
     * @return UUID
     */
    @POST
    @Path("/upload-file")
    @Consumes("multipart/form-data")
    @Produces("text/plain")
    public Response createNewFile(@MultipartForm FileUploadForm form) {

        String uuIdFilename = getUUID();

        String uploadedFileLocation = absolutePathToFile(uuIdFilename);
        writeToFile(form, uploadedFileLocation);

        File fileHandle = new File(uploadedFileLocation);
        String mimeType = "unkown";
        try {
            mimeType = MimeParser.getMimeFromFileContentAndExtension(fileHandle, uuIdFilename);

        } catch (IOException ioEx) {
            Logger.getLogger(MediaResourceFetchMetaData.class.getName()).log(Level.SEVERE, null, ioEx);
        }

        // beroende vilken Media det är ...
        Media media = new Image();
        media.setUuid(uuIdFilename);
        media.setFilename(form.getFileName());
        media.setMimetype(mimeType);
        media.setOwner(form.getOwner());
        media.setVisibility(form.getAccess());

        // testing
        Tag tag1 = new Tag("vy", "vänster", media);
        Tag tag2 = new Tag("helper", "nisse", media);
        media.addTag(tag1);
        media.addTag(tag2);

        String mockUUID = UUID.randomUUID().toString();
        Determination d = new Determination("taxon", mockUUID,"mock-system", "http", media);
        media.addDetermination(d);
        writeToDatabase(media);

        String responseOutput = uuIdFilename;

        return Response.status(200).entity(responseOutput).build();
    }

    @POST
    @Path("/upload-video")
    @Consumes("multipart/form-data")
    @Produces("text/plain")
    public Response createNewVideoFile(@MultipartForm FileUploadForm form) {

        String uuIdFilename = getUUID();

        String uploadedFileLocation = absolutePathToFile(uuIdFilename);
        writeToFile(form, uploadedFileLocation);

        File fileHandle = new File(uploadedFileLocation);
        String mimeType = "unkown";
        try {
            mimeType = MimeParser.getMimeFromFileContentAndExtension(fileHandle, uuIdFilename);

        } catch (IOException ioEx) {
            Logger.getLogger(MediaResourceFetchMetaData.class.getName()).log(Level.SEVERE, null, ioEx);
        }

        // beroende vilken Media det är ...
        Video video = new Video();
        video.setUuid(uuIdFilename);
        video.setFilename(form.getFileName());
        video.setMimetype(mimeType);
        video.setOwner(form.getOwner());
        video.setVisibility(form.getAccess());

        // 
        video.setStartTime(15);
        video.setEndTime(25);

        // testing
        Tag tag1 = new Tag("genus", "hona", video);
        video.addTag(tag1);

        Determination d = new Determination("taxon", "ext-123", "mock-system", "http", video);
        video.addDetermination(d);
        writeToDatabase(video);

        String responseOutput = "File uploaded/saved to : " + uploadedFileLocation;

        return Response.status(200).entity(responseOutput).build();
    }

    @POST
    @Path("/upload-sound")
    @Consumes("multipart/form-data")
    @Produces("text/plain")
    public Response createNewSoundFile(@MultipartForm FileUploadForm form) {

        String uuIdFilename = getUUID();

        String uploadedFileLocation = absolutePathToFile(uuIdFilename);
        writeToFile(form, uploadedFileLocation);

        File fileHandle = new File(uploadedFileLocation);
        String mimeType = "unkown";
        try {
            mimeType = MimeParser.getMimeFromFileContentAndExtension(fileHandle, uuIdFilename);

        } catch (IOException ioEx) {
            Logger.getLogger(MediaResourceFetchMetaData.class.getName()).log(Level.SEVERE, null, ioEx);
        }

        // beroende vilken Media det är ...
        Sound sound = new Sound();
        sound.setUuid(uuIdFilename);
        sound.setFilename(form.getFileName());
        sound.setMimetype(mimeType);
        sound.setOwner(form.getOwner());
        sound.setVisibility(form.getAccess());
        sound.setStartTime(10);
        sound.setEndTime(35);

        // testing
        Tag tag1 = new Tag("genus", "hona", sound);
        sound.addTag(tag1);

        Determination d = new Determination("taxon", "ext-123", "mock-system", "http", sound);
        sound.addDetermination(d);
        writeToDatabase(sound);

        String responseOutput = "File uploaded/saved to : " + uploadedFileLocation;

        return Response.status(200).entity(responseOutput).build();
    }
    @POST
    @Path("/upload-attach")
    @Consumes("multipart/form-data")
    @Produces("text/plain")
    public Response createNewAttachmentFile(@MultipartForm FileUploadForm form) {

        String uuIdFilename = getUUID();

        String uploadedFileLocation = absolutePathToFile(uuIdFilename);
        writeToFile(form, uploadedFileLocation);

        File fileHandle = new File(uploadedFileLocation);
        String mimeType = "unkown";
        try {
            mimeType = MimeParser.getMimeFromFileContentAndExtension(fileHandle, uuIdFilename);

        } catch (IOException ioEx) {
            Logger.getLogger(MediaResourceFetchMetaData.class.getName()).log(Level.SEVERE, null, ioEx);
        }

        // beroende vilken Media det är ...
        Attachment attachment = new Attachment();
        attachment.setUuid(uuIdFilename);
        attachment.setFilename(form.getFileName());
        attachment.setMimetype(mimeType);
        attachment.setOwner(form.getOwner());
        attachment.setVisibility(form.getAccess());
        

        // testing
        Tag tag1 = new Tag("genus", "hona", attachment);
        attachment.addTag(tag1);

        Determination d = new Determination("taxon", "ext-123", "mock-system", "http", attachment);
        attachment.addDetermination(d);
        writeToDatabase(attachment);

        String responseOutput = "File uploaded/saved to : " + uploadedFileLocation;

        return Response.status(200).entity(responseOutput).build();
    }

    private String absolutePathToFile(String uuid) {
        return PathHelper.getDynamicUUUIDFile(uuid);
    }

    private void writeToFile(FileUploadForm form, String location) {

        Writeable writer = new FileSystemWriter();
        writer.writeBytesTo(form.getFileData(), location);
    }

    private void writeToDatabase(Media media) {
        String serverDate = bean.getServerDate();
        bean.save(media);
    }

    private String getUUID() {
        final String uuIdFilename = UUID.randomUUID().toString();
        return uuIdFilename;
    }
}
