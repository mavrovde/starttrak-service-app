package com.starttrak.social;

import java.util.Scanner;

import org.junit.Test;
import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;

public class XingClientTest {

    public static void main(String[] args) {
        String apiKey = "200498315bfcba1df1dc";
        String apiSecret = "c9d77b643643bd940c475ce08deba22c55dd3fa6";
        String callUrl = "https://api.xing.com/v1/users/me";

        // Interact via Commandline
        Scanner input = new Scanner(System.in);

        System.out.println("=== Example to use the XING API ===");

        // Entering the API-key
//        System.out.println("Please enter your API-key:");
//        System.out.print(">> ");
//        apiKey = input.nextLine();

        // Entering the API-secret
//        System.out.println("Please enter your API-secret:");
//        System.out.print(">> ");
//        apiSecret = input.nextLine();

        // Entering the API-call you would like to perform
//        System.out.println("Now you need to set up the call you'd like to perform.");
//        System.out.println("In case you don't enter something the \"users me\"-call would be performed.");
//        System.out.println("In case you want to perform a different call please enter the related call URL:");
//        System.out.print(">>");
//        callUrl = input.nextLine();
//        if (callUrl.isEmpty()) {
//            callUrl = "https://api.xing.com/v1/users/me";
//        }

        // Initializing OAuth - Service
        OAuthService service = new ServiceBuilder()
                .provider(XingApi.class)
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .build();

        // Obtain the Request Token
        System.out.println("Fetching the Request Token...");
        Token requestToken = service.getRequestToken();
        System.out.println("Got the Request Token!");
        System.out.println();

        System.out.println("Now go and authorize this example call here:");
        System.out.println(service.getAuthorizationUrl(requestToken));
        System.out.println("And paste the verifier here");
        System.out.print(">> ");
        Verifier verifier = new Verifier(input.nextLine());
        System.out.println();

        // Trade the Request Token and Verifier for the Access Token
        System.out.println("Trading the Request Token for an Access Token...");
        Token accessToken = service.getAccessToken(requestToken, verifier);
        System.out.println("Got the Access Token!");
        System.out.println("(if your curious it looks like this: " + accessToken + " )");
        System.out.println();

        // Now let's go and ask for a protected resource!
        System.out.println("Now we're going to access a protected resource...");
        OAuthRequest request = new OAuthRequest(Verb.GET, callUrl);
        service.signRequest(accessToken, request);
        Response response = request.send();
        System.out.println("Got it! Lets see what we found...");
        System.out.println();
        System.out.println(response.getBody());
    }

    @Test
    public void dummyTest() {

    }
}
