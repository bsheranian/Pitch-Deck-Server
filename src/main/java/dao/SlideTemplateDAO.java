package dao;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.*;

public class SlideTemplateDAO {

    public InputStream getMasterTemplates() {



        String bucketName = "pitchdeck-templates";
        String key = "Project 2 - Datalog Parser.pptx";

        ClientConfiguration clientConfig = new ClientConfiguration();

        clientConfig.setSocketTimeout(600000);
        clientConfig.setConnectionTimeout(60000);
        clientConfig.setMaxErrorRetry(2);

        AmazonS3 s3 = AmazonS3ClientBuilder
                .standard()
                .withClientConfiguration(clientConfig)
                .withRegion(Regions.US_WEST_2)
                .build();

        // Get an object and print its contents.
        System.out.println("Downloading an object");

       // File localFile = new File("tmp/master_deck.pptx");

        // s3Client.getObject(new GetObjectRequest(bucketName, key), localFile);


        S3Object object = s3.getObject(new GetObjectRequest(bucketName, key));


        InputStream in = object.getObjectContent();
//        byte[] buf = new byte[1024];
//        int count;
//
//        OutputStream out = new FileOutputStream(localFile);
//        while( (count = in.read(buf)) != -1)
//        {
//            if( Thread.interrupted() )
//            {
//                throw new InterruptedException();
//            }
//            out.write(buf, 0, count);
//        }
//        out.close();
//        in.close();
//
//        System.out.println("After s3.getObject");

        return in;
    }


}
