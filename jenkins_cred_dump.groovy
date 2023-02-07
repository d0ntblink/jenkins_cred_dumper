import jenkins.model.*
import groovy.json.JsonSlurper
import groovy.json.JsonBuilder


def creds = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
    com.cloudbees.plugins.credentials.Credentials.class, Jenkins.instance,
    null,
    null)

// seceret classes documentation
// https://javadoc.jenkins.io/plugin/ssh-credentials/com/cloudbees/jenkins/plugins/sshcredentials/impl/package-summary.html
// https://javadoc.jenkins.io/plugin/credentials/com/cloudbees/plugins/credentials/impl/package-summary.html
// https://javadoc.jenkins.io/plugin/plain-credentials/org/jenkinsci/plugins/plaincredentials/impl/package-summary.html
// https://javadoc.jenkins.io/plugin/aws-credentials/com/cloudbees/jenkins/plugins/awscredentials/AWSCredentialsImpl.html

for(c in creds){
    println(String.format("=======================================================\n%s",
    c.getClass().toString()))
    if (c.toString().contains("UsernamePasswordCredentialsImpl")){
        try {
            println(String.format("id=%s\n username=%s\n password=%s\n", 
                c.id, 
                // c.description, 
                c.username, 
                c.password))
        } catch (Exception e) {
            println(String.format("\n"))
        }
    }
    else if (c.toString().contains("BasicSSHUserPrivateKey")){
        try{
            println(String.format("id=%s\n username=%s\n privateKey=%s\n", 
                c.id,
                c.username,
                // c.description, 
                c.privateKey))
        } catch (Exception e) {
            println(String.format("\n"))
        }
    }
    else if (c.toString().contains("StringCredentialsImpl")){
        try{
            println(String.format("id=%s\n secret=%s\n", 
                c.id, 
                // c.description, 
                c.secret))
        } catch (Exception e) {
            println(String.format("\n"))
        }
    }
    else if (c.toString().contains("FileCredentialsImpl")){
        try{
            println(String.format("id=%s\n fileName=%s\n secretBytes=%s\n", 
                c.id, 
                // c.description, 
                c.fileName, 
                new String(com.cloudbees.plugins.credentials.SecretBytes.fromString(c.secretBytes.toString()).getPlainData())))
        } catch (Exception e) {
            println(String.format("\n"))
        }
    }
    else if (c.toString().contains("AWSCredentialsImpl")){
        try{
            println(String.format("id=%s\n access key=%s\n credentials=%s\n mfatoken=%s\n secret key=%s\n", 
                c.id,
                // c.description,
                c.accessKey,
                c.credentials,
                c.mfaToken,
                c.secretKey))
        } catch (Exception e) {
            println(String.format("\n"))
        }
    }
    else {
        println(String.format("Dont know what to do with id=%s yet", c.id))
        assert true;
    }
}
return this
