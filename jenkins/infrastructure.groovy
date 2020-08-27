JOB_NAME = 'Infrastructure'
DISPLAY_NM = 'IAC Project'
CJK_GIT_URL = 'https://github.com/KevinChris7/Terraform-AWS-2Tier-WebApplication.git'
BRANCH_NAME = 'master'
TERRAFORM_VERSION = '0.13'
BUCKET_NAME = 'my-tf-bucket'

pipelineJob(JOB_NAME){
    displayName(DISPLAY_NM)
    logRotator{
        daysToKeep(30)
        numToKeep(10)
    }
    parameters {
        stringParameter('CJK_GIT_URL',CJK_GIT_URL)
        stringParameter('BRANCH', BRANCH_NAME)
        stringParameter('TERRAFORM_VERSION', TERRAFORM_VERSION)
        chocieParameter('OPERATION', ['apply','destroy'])
        stringParameter('BUCKET', BUCKET_NAME)
    }
    definition{
        cpsScm{
            scm{
                git{
                    remote{
                        credentials(CREDENTIALS_ID)
                        url(JOB_REPO)
                    }
                    branch("*/master")
                }
            scriptPath('jenkins-pipelines/infrastructure-build/Jenkinsfile')
            lightweight()
            }
        }
    }
    properties{
        rebuild{
            autoRebuild(false)
            rebuildDisabled(false)
        }
    }

}