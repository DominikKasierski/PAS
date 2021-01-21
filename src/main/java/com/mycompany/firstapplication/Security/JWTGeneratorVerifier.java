package com.mycompany.firstapplication.Security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import javax.security.enterprise.identitystore.CredentialValidationResult;
import java.util.Date;

public class JWTGeneratorVerifier {

    private static final String SECRET = "09SEVheeEsOTYLDZAJylVmlHb4XadBtgABGKZB5wmKVexgWU-006mSwGPlkWUCN0d75bfYtpsqecmsIGVtoCrPcN1h7MEAmH5HlisCPGuAlaBQRJtrMNM5uZRWaTRoXEhDchSqcAtl0hk_Fsb3VjlziIobgtbMs0DC8xctSW0eUqJ8W7hPyMllosTeb085sL26nmmWpQRC9ImYedx9FxYQFdr4XsNiU3l8Y5yMXVeFq6NqDL5BTcG2ximw2uHtHoIqtxYcggE6S2yKfeGQW7BMLsaBY6flym11zzezgeOO8NC2yJlZbvA2aHdLw7v-Dz-6TLzKjbCE5r8oWUFXoraA";
    private static final long JWT_EXPIRE_TIMEOUT = 5 * 60 * 1000;

    public static String generateJWTString(CredentialValidationResult result) {
        try {
            final JWSSigner signer = new MACSigner(SECRET);

            final JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(result.getCallerPrincipal().getName())
                    .claim("role", result.getCallerGroups())
                    .expirationTime(new Date(new Date().getTime() + JWT_EXPIRE_TIMEOUT))
                    .build();

            final SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);
            return signedJWT.serialize();
        } catch (JOSEException e) {
            e.printStackTrace();
            return "JWT error";
        }
    }
}
