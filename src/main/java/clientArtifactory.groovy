/**
 * Created by Dnaiel Keler.
 */

import org.apache.commons.io.IOUtils
import org.jfrog.artifactory.client.ArtifactoryClient
import org.jfrog.artifactory.client.model.File;

class clientArtifactory {

    static uploadFile_artifactory(String url, String user, String pass){
        try {
            File upAtrifact =  ArtifactoryClient
                    .create(url, user, pass)
                    .repository("ext-snapshot-local")
                    .upload("test/artifactory-java-client-services-0.17.jar",
                    (new FileInputStream("src/FileUpload/artifactory-java-client-services-0.17.jar")))
                    .doUpload()
            println('File successfully uploaded.')
        } catch (FileNotFoundException fnf){
            println('File not found. Check file path.')
        }
    }

    static downloadFile_artifactory(String url, String user, String pass){
        try {
            ByteArrayInputStream downAtrifact =  ArtifactoryClient
                    .create(url, user, pass)
                    .repository("ext-snapshot-local")
                    .download("test/artifactory-java-client-services-0.17.jar")
                    .doDownload()

            IOUtils.copy(downAtrifact, new FileOutputStream('src/FileDownload/artifactory-java-client-services-0.17.jar'))
            println('File successfully downloaded.')

        } catch (FileNotFoundException fnf){
            println('File not found. Check file path.')
        }
    }

    static void main (args) {
        uploadFile_artifactory("http://127.0.0.1:8081/artifactory", "admin", "password")
        downloadFile_artifactory("http://127.0.0.1:8081/artifactory", "admin", "password")
    }
}
