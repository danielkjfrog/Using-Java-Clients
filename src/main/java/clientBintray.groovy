/**
 * Created by Daniel Keler.
 */


import com.jfrog.bintray.client.impl.BintrayClient
import org.apache.commons.io.IOUtils
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient

class clientBintray {

    static uploadFile_bintray(){
        Map connectionProperties = [
                username:'danielk',
                apikey:'ba7f6407fe4b3bd3dfdb3c98ecf28065d97cae6d',
                email:'danielk@jfrog.com',
                url:'https://api.bintray.net',
                downloadUrl:''
        ]

        try {
            def upload =  BintrayClient.create (
                    connectionProperties.url as String,
                    connectionProperties.username as String,
                    connectionProperties.apikey as String )
                    .subject('danielk')
                    .repository('generic')
                    .pkg('mypack')
                    .version('1.0')
                    .upload(
                        "myfile/artifactory-java-client-services-0.17.jar",
                    new FileInputStream("src/FileUpload/artifactory-java-client-services-0.17.jar"
                    ))

            println('File successfully uploaded.')

        } catch (FileNotFoundException fnf){
            println('File not found. Check file path.')
        }
    }

    static download_bintray (){
        HttpGet req = new HttpGet('https://dl.bintray.net/danielk/generic/package-list')
        HttpClient client = new DefaultHttpClient()
        HttpResponse resp = client.execute(req)
        InputStream file = resp.getEntity().getContent()
        IOUtils.copy(file, new FileOutputStream('src/FileDownload/package-list'))

        println('File successfully downloaded.')
    }

    static void main (args) {
        uploadFile_bintray()
        download_bintray()
    }
}
