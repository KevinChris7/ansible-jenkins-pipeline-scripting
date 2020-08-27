JOB_NAME = 'Infrastructure'
DISPLAY_NM = 'IAC Project'
CJK_GIT_URL = 'https://github.com/KevinChris7/Terraform-AWS-2Tier-WebApplication.git'
JOB_REPO = 'https://github.com/KevinChris7/trial-project.git'
BRANCH_NAME = 'master'
TERRAFORM_VERSION = '0.13'
BUCKET_NAME = 'my-tf-bucket'
CREDENTIALS_ID = 'KevinChris7'

pipelineJob(JOB_NAME){
    displayName(DISPLAY_NM)
    logRotator{
        daysToKeep(30)
        numToKeep(10)
    }
    parameters{
        stringParam('CJK_GIT_URL',CJK_GIT_URL)
        stringParam('BRANCH_NAME', BRANCH_NAME)
        stringParam('TERRAFORM_VERSION', TERRAFORM_VERSION)
        choiceParam('OPERATION', ['apply','destroy'])
        stringParam('BUCKET', BUCKET_NAME)
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
            scriptPath('trial-project/jenkins/Jenkinsfile')
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