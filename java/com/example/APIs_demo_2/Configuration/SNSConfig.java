package com.example.APIs_demo_2.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

@Configuration
public class SNSConfig {

	@Primary
	@Bean
	public AmazonSNSClient amazonSnsClient() {

		String accessKey = "AKIAW3UDRKI7S3TLZV7W";
		String secretAccessKey = "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";

		return (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(Regions.AP_SOUTH_1)
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretAccessKey)))
				.build();
	}

}
