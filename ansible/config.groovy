JOB_NAME = 'Ansible Setup'
DISPLAY_NM = 'Configuration Project'
CJK_GIT_URL = 'https://github.com/KevinChris7/terraform-ansible-aws.git'
JOB_REPO = 'https://github.com/KevinChris7/trial-project.git'
BRANCH_NAME = 'master'
CREDENTIALS_ID = 'github'

pipelineJob(JOB_NAME){
    displayName(DISPLAY_NM)
    logRotator{
        daysToKeep(30)
        numToKeep(10)
    }
    parameters{
        stringParam('CJK_GIT_URL',CJK_GIT_URL)
        stringParam('BRANCH_NAME', BRANCH_NAME)
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
            scriptPath('ansible/Jenkinsfile')
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