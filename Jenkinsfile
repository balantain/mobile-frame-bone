def SUREFIRE_REPORT_PATH = 'target/surefire-reports/TEST-TestSuite.xml'
def LOGIN_TEST_PROJECT   = 'Login_test_job'
def SETTINGS_PAGE_PROJECT    = 'Settings_job'

pipeline {
    agent {
        label 'win10'
    }
    parameters {
        booleanParam(defaultValue: true, description: 'run login test', name: 'LoginParameter')
        booleanParam(defaultValue: true, description: 'run settings tests', name: 'SettingsParameter')
    }

    stages {
        stage('Login test'){
            when{
                expression {
                    params.LoginParameter
                }
            }
            steps{
                build job: 'Login_test_job', parameters:[string(name:'test.suite',value:'Testng-login'),
                string(name: 'browser', value: 'chrome')],
                propagate:false
//                 sh "cp ../${LOGIN_TEST_PROJECT}/${SUREFIRE_REPORT_PATH} ./login_test_result.xml"
            }
//             post{
//                 always{
//                     step([$class: 'XUnitPublisher', testTimeMargin: '3000', thresholdMode: 2,
//                         thresholds: [
//                         [$class: 'FailedThreshold', unstableThreshold: '80']],
//                         tools: [
//                         [$class: 'JUnitType', deleteOutputFiles: false, failIfNotNew: false, pattern: 'login_test_result.xml', skipNoTestFiles: true, stopProcessingIfError: false]]
//                         ])
//                 }
//             }
        }

        stage("Settings tests"){
            when {
                expression{
                    params.SettingsParameter
                }
            }
            steps{
                build job: 'Settings_job', parameters:[
                string(name:'test.suite',value:'Testng-settings-smoke'),
                string(name: 'browser', value: 'chrome')],
                propagate:false
//                 sh "cp ../${API_PROJECT}/${SUREFIRE_REPORT_PATH} ./settings_result.xml"
            }
//             post{
//                 always{
//                     step([$class: 'XUnitPublisher', testTimeMargin: '3000', thresholdMode: 2,
//                         thresholds: [
//                         [$class: 'FailedThreshold', unstableThreshold: '80']],
//                         tools: [
//                         [$class: 'JUnitType', deleteOutputFiles: false, failIfNotNew: false, pattern: 'settings_result.xml', skipNoTestFiles: true, stopProcessingIfError: false]]
//                         ])
//                     }
//                 }
        }
    }
}
